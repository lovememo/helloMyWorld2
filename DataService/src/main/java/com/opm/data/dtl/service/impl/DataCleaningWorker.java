package com.opm.data.dtl.service.impl;

import ch.qos.logback.classic.Logger;
import com.opm.common.enumdict.CommonStatus;
import com.opm.common.enumdict.DtlStatus;
import com.opm.data.common.FileUtil;
import com.opm.data.dtl.dao.impl.DtlDataDao;
import com.opm.data.dtl.file.FileProcessingUtil;
import com.opm.data.dtl.file.parser.IParser;
import com.opm.data.dtl.file.parser.action.ICleaningAction;
import com.opm.data.dtl.file.parser.action.impl.DefaultCleaningAction;
import com.opm.data.dtl.file.parser.factory.ParserFactory;
import com.opm.data.dtl.model.DtlControl;
import com.opm.data.dtl.model.DtlData;
import com.opm.data.dtl.model.DtlFile;
import com.opm.data.dtl.model.DtlItem;
import com.opm.data.dtl.service.IDataProcessService;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by kfzx-jinjf on 2016/12/27.
 */
public class DataCleaningWorker {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DataCleaningWorker.class);
    private HttpServletRequest request;
    private IDataProcessService dataProcessService;
    private HashMap<MultipartFile, DtlControl> fileCtrlInfoMap = null;
    private String dtlItemId;
    private DtlDataDao dtlDataDao;
    private String serialNo;

    public DataCleaningWorker(HttpServletRequest request, IDataProcessService dataProcessService, String dtlItemId) {
        this.request = request;
        this.dataProcessService = dataProcessService;
        this.dtlItemId = dtlItemId;
        this.dtlDataDao = dataProcessService.getDtlDataDao();
    }

    public DataCleaningWorker(String serialNo, IDataProcessService dataProcessService) {
        this.serialNo = serialNo;
        this.dataProcessService = dataProcessService;
        this.dtlDataDao = dataProcessService.getDtlDataDao();
    }

    /**
     * 解析并清理工作
     * @param fileName
     * @param serialNo
     * @param is
     * @return
     * @throws Exception
     */
    private int[] parseAndCleaningWork(String fileName, String serialNo, InputStream is) throws Exception{
        //获取数据清理项定义信息
        DtlItem dtlItem = this.getDtlItem(serialNo);
        //step1 CSV文件数据解析、清理、并持久化
        LOGGER.info("step1 文件数据解析、清理, serialNo为：" + serialNo);
        this.deleteDtlDataInfo(serialNo);//先删除原有的serialNo对应的数据
        ParserFactory pf = new ParserFactory();
        String fileExt = FileUtil.getFileExtension(fileName);
        int fileFormat = ParserFactory.SEPARATOR_FILE;
        IParser parser = pf.getParser(fileExt, 1500, fileFormat);
        if(null == parser) {
            throw new Exception("解析器创建失败！fileExt:" + fileExt + ", fileFormat:" + fileFormat);
        }
        ICleaningAction action = new DefaultCleaningAction(serialNo, this.dtlDataDao);
        return parser.parse(is, action);
    }

    /**
     * 登记文件控制信息
     * @return
     * @throws Exception
     */
    private List<DtlControl> getDataCtrlInfoMap() throws Exception {
        List<DtlControl> dtlControlList = new ArrayList();
        this.fileCtrlInfoMap = new HashMap<MultipartFile, DtlControl>();
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (resolver.isMultipart(this.request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> it = multiRequest.getFileNames();
            while (it.hasNext()) {
                //登记文件控制信息
                DtlControl dtlControl = this.dataProcessService.getCtrlInfo(this.dtlItemId);
                dtlControlList.add(dtlControl);
                MultipartFile file = multiRequest.getFile(it.next());
                this.fileCtrlInfoMap.put(file, dtlControl);
            }
        }
        return dtlControlList;
    }

    /**
     * 数据清理
     * @return
     * @throws Exception
     */
    private void saveSingleFile(MultipartFile file, String fileName, DtlControl dtlControl) throws Exception {
        InputStream is = file.getInputStream();
        try {
            String serialNo = dtlControl.getSerialNo();
            //step3 文件归档
            LOGGER.info("step1 保存文件至数据库");
            FileProcessingUtil fpu = FileProcessingUtil.getInstance();
            fpu.saveFileAsBlob(file.getInputStream(), fileName, serialNo, this.dtlDataDao);

            //step4更新文件处理控制表状态
            LOGGER.info("step2更新文件处理控制表状态");
            dtlControl.setDtlStatus(DtlStatus.SAVED.getKey());
            dtlControl.setReturnTime(new Date());
            this.dtlDataDao.updateDtlInfo(dtlControl);
        }
        finally {
            if(null != is ) {
                is.close();
            }
        }
    }

    /**
     * 准备工作
     * @return
     * @throws Exception
     */
    public List<DtlControl> beforeWork() throws Exception {
        return this.getDataCtrlInfoMap();
    }



    /**
     * 异步清理工作
     * @throws Exception
     */
    public int[] asynWork() throws Exception {
        if(null == this.serialNo) {
            return new int[]{0, 0};
        }
        DtlControl fci = new DtlControl();
        fci.setSerialNo(serialNo);
        DtlFile dtlFile = this.dtlDataDao.readFile(fci);
        InputStream is = new ByteArrayInputStream(dtlFile.getFileContent());

        int[] retData = this.parseAndCleaningWork(dtlFile.getFileName(), this.serialNo, is);

        //step2更新文件处理控制表状态
        LOGGER.info("step2更新文件处理控制表状态");
        fci.setDtlStatus(DtlStatus.SUCCEEDED.getKey());
        fci.setReturnTime(new Date());
        this.dtlDataDao.updateDtlInfo(fci);
        return retData;
    }

    /**
     *  处理上传的文件，并生成文件号
     * @param file
     * @param fileName
     * @param dtlControl
     * @return
     * @throws Exception
     */
    private DtlFile syncProcessSingleFile(MultipartFile file, String fileName, DtlControl dtlControl) throws Exception {
        InputStream is = file.getInputStream();
        DtlFile dtlFile = null;
        try {
            String serialNo = dtlControl.getSerialNo();
            //解析并清理
            int[] retData = this.parseAndCleaningWork(fileName, serialNo, is);

            //step2 文件归档
            LOGGER.info("step2 保存文件至数据库");
            FileProcessingUtil fpu = FileProcessingUtil.getInstance();
            dtlFile = fpu.saveFileAsBlob(file.getInputStream(), fileName, serialNo, this.dtlDataDao);
            dtlFile.setTotalRowNum(retData[0]);
            dtlFile.setErrRowNum(retData[1]);

            //step3更新文件处理控制表状态
            LOGGER.info("step3更新文件处理控制表状态");
            dtlControl.setDtlStatus(DtlStatus.SUCCEEDED.getKey());
            dtlControl.setReturnTime(new Date());
            this.dtlDataDao.updateDtlInfo(dtlControl);
        }
        finally {
            if(null != is ) {
                is.close();
            }
        }
        dtlFile.setFileName(fileName);
        return dtlFile;
    }

    public DtlItem getDtlItem(String serialNo) {
        DtlControl dtlControl = new DtlControl();
        dtlControl.setSerialNo(serialNo);
        List<DtlItem> dtlItems = this.dtlDataDao.queryDtlItem(dtlControl);
        DtlItem retDtlItem = null;
        if(dtlItems.size() > 0) {
            retDtlItem = dtlItems.get(0);
        } else {
            return retDtlItem;
        }
        return retDtlItem;
    }
    /**
     * 数据清理
     *
     * @return
     * @throws Exception
     */
    public List<DtlFile> syncWork() throws Exception {
        List<DtlFile> dtlFileList = new ArrayList();
        if(null == this.fileCtrlInfoMap || this.fileCtrlInfoMap.size() < 1) {
            return dtlFileList;
        }
        Iterator it = fileCtrlInfoMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<MultipartFile, DtlControl> entry = (Map.Entry)it.next();
            MultipartFile file = entry.getKey();
            DtlControl dtlControl = entry.getValue();
            String fileName = new String(file.getOriginalFilename().getBytes(), "UTF-8");
            DtlFile fi = new DtlFile();
            fi.setSerialNo("-1");
            if (null == file || file.isEmpty()) {
                continue;
            }
            fi = this.syncProcessSingleFile(file, fileName, dtlControl);
            dtlFileList.add(fi);
        }
        return dtlFileList;
    }

    /**
     * 数据清理
     * 异步处理（仅做文件保存，待后续业务端主动调用数据清理接口）
     * @return
     * @throws Exception
     */
    public void syncSaveWork() throws Exception {
        if(null == this.fileCtrlInfoMap || this.fileCtrlInfoMap.size() < 1) {
            return;
        }
        Iterator it = fileCtrlInfoMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<MultipartFile, DtlControl> entry = (Map.Entry)it.next();
            MultipartFile file = entry.getKey();
            DtlControl dtlControl = entry.getValue();
            String fileName = new String(file.getOriginalFilename().getBytes(), "UTF-8");
            DtlFile fi = new DtlFile();
            fi.setSerialNo("-1");
            if (null == file || file.isEmpty()) {
                continue;
            }
            this.saveSingleFile(file, fileName, dtlControl);
        }
    }

    /**
     * 删除解析出来的数据
     * @param serialNo
     * @return
     */
    private void deleteDtlDataInfo(String serialNo) {
        DtlData dtlData = new DtlData();
        dtlData.setSerialNo(serialNo);
        dtlData.setStatus(CommonStatus.N.getKey());//将状态置为N
        this.dtlDataDao.updateDtlDataInfo(dtlData);
    }
}
