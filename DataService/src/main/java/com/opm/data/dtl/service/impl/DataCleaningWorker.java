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
     * ������������
     * @param fileName
     * @param serialNo
     * @param is
     * @return
     * @throws Exception
     */
    private int[] parseAndCleaningWork(String fileName, String serialNo, InputStream is) throws Exception{
        //��ȡ�������������Ϣ
        DtlItem dtlItem = this.getDtlItem(serialNo);
        //step1 CSV�ļ����ݽ������������־û�
        LOGGER.info("step1 �ļ����ݽ���������, serialNoΪ��" + serialNo);
        this.deleteDtlDataInfo(serialNo);//��ɾ��ԭ�е�serialNo��Ӧ������
        ParserFactory pf = new ParserFactory();
        String fileExt = FileUtil.getFileExtension(fileName);
        int fileFormat = ParserFactory.SEPARATOR_FILE;
        IParser parser = pf.getParser(fileExt, 1500, fileFormat);
        if(null == parser) {
            throw new Exception("����������ʧ�ܣ�fileExt:" + fileExt + ", fileFormat:" + fileFormat);
        }
        ICleaningAction action = new DefaultCleaningAction(serialNo, this.dtlDataDao);
        return parser.parse(is, action);
    }

    /**
     * �Ǽ��ļ�������Ϣ
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
                //�Ǽ��ļ�������Ϣ
                DtlControl dtlControl = this.dataProcessService.getCtrlInfo(this.dtlItemId);
                dtlControlList.add(dtlControl);
                MultipartFile file = multiRequest.getFile(it.next());
                this.fileCtrlInfoMap.put(file, dtlControl);
            }
        }
        return dtlControlList;
    }

    /**
     * ��������
     * @return
     * @throws Exception
     */
    private void saveSingleFile(MultipartFile file, String fileName, DtlControl dtlControl) throws Exception {
        InputStream is = file.getInputStream();
        try {
            String serialNo = dtlControl.getSerialNo();
            //step3 �ļ��鵵
            LOGGER.info("step1 �����ļ������ݿ�");
            FileProcessingUtil fpu = FileProcessingUtil.getInstance();
            fpu.saveFileAsBlob(file.getInputStream(), fileName, serialNo, this.dtlDataDao);

            //step4�����ļ�������Ʊ�״̬
            LOGGER.info("step2�����ļ�������Ʊ�״̬");
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
     * ׼������
     * @return
     * @throws Exception
     */
    public List<DtlControl> beforeWork() throws Exception {
        return this.getDataCtrlInfoMap();
    }



    /**
     * �첽������
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

        //step2�����ļ�������Ʊ�״̬
        LOGGER.info("step2�����ļ�������Ʊ�״̬");
        fci.setDtlStatus(DtlStatus.SUCCEEDED.getKey());
        fci.setReturnTime(new Date());
        this.dtlDataDao.updateDtlInfo(fci);
        return retData;
    }

    /**
     *  �����ϴ����ļ����������ļ���
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
            //����������
            int[] retData = this.parseAndCleaningWork(fileName, serialNo, is);

            //step2 �ļ��鵵
            LOGGER.info("step2 �����ļ������ݿ�");
            FileProcessingUtil fpu = FileProcessingUtil.getInstance();
            dtlFile = fpu.saveFileAsBlob(file.getInputStream(), fileName, serialNo, this.dtlDataDao);
            dtlFile.setTotalRowNum(retData[0]);
            dtlFile.setErrRowNum(retData[1]);

            //step3�����ļ�������Ʊ�״̬
            LOGGER.info("step3�����ļ�������Ʊ�״̬");
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
     * ��������
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
     * ��������
     * �첽���������ļ����棬������ҵ�������������������ӿڣ�
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
     * ɾ����������������
     * @param serialNo
     * @return
     */
    private void deleteDtlDataInfo(String serialNo) {
        DtlData dtlData = new DtlData();
        dtlData.setSerialNo(serialNo);
        dtlData.setStatus(CommonStatus.N.getKey());//��״̬��ΪN
        this.dtlDataDao.updateDtlDataInfo(dtlData);
    }
}
