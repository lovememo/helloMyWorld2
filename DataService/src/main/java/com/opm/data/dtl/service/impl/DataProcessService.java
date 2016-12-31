package com.opm.data.dtl.service.impl;

import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.opm.common.enumdict.DtlStatus;
import com.opm.data.dtl.controller.FileController;
import com.opm.data.dtl.dao.impl.DtlDataDao;
import com.opm.data.dtl.model.DtlControl;
import com.opm.data.dtl.model.DtlFile;
import com.opm.data.dtl.service.IDataProcessService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by kfzx-jinjf on 2016/12/14.
 */
@Service
public class DataProcessService implements IDataProcessService {
    public final static      String FILE_SAVE_ITEM_ID = "0";
    private static final Logger LOGGER            = (Logger) LoggerFactory.getLogger(FileController.class);

    public DtlDataDao getDtlDataDao() {
        return dtlDataDao;
    }

    @Autowired
    private DtlDataDao dtlDataDao;

    /**
     *翻页查询上传的文件列表
     * @param serialNo 文件序列号
     * @param beginPos 开始位置
     * @param fetchNum 抽取数
     * @return
     */
    @Deprecated
    public List<Object> turnPageFileUploadedList(String serialNo, String beginPos, String fetchNum) {
        List<DtlFile> dtlFileList = this.dtlDataDao.queryFileUploadedList(serialNo, beginPos, fetchNum);
        String totalNum = (String)this.dtlDataDao.queryFileUploadedListTotalNum(serialNo).get(0);
        List retList = new ArrayList();
        retList.addAll(dtlFileList);
        HashMap mp = new HashMap<String, String>();
        mp.put("totalNum", totalNum);
        retList.add(0, mp);
        return retList;
    }

    /**
     * 下载文件
     * @param serialNo
     * @return 文件字节流
     */
    public DtlFile readFile(String serialNo) {
        DtlControl fci = new DtlControl();
        fci.setSerialNo(serialNo);
        fci.setSerialNo(serialNo);
        return this.dtlDataDao.readFile(fci);
    }

    /**
     * 获取数据处理控制信息
     * @param dtlItemId
     * @return
     */
    public DtlControl getCtrlInfo(String dtlItemId) throws Exception {
        if(null == dtlItemId || "".equals(dtlItemId)) {
            dtlItemId = FILE_SAVE_ITEM_ID;
        }
        DtlControl dtlControl = new DtlControl();
        dtlControl.setDtlItemId(dtlItemId);
        this.dtlDataDao.registerDtlInfo(dtlControl);
        return dtlControl;
    }

    /**
     * 获取文件解析后的数据行数
     * @param serialNo
     * @return
     */
    public int getDataProcessedCount(String serialNo) {
        List<Integer> list = this.dtlDataDao.queryProcessedDataCount(serialNo);
        return null == list || list.size() < 1 ? 0 : list.get(0);
    }

    /*private DtlControl getCtrlInfo() throws Exception {
        return getCtrlInfo(FILE_SAVE_MODE);
    }*/

    public static void main(String[] args) {
        DtlFile dtlFile = new DtlFile("123", "123.txt", new byte[]{'a', 'b', 'c'});
        Gson gson = new Gson();
        System.out.println(gson.toJson(gson.toJson(dtlFile)));
        System.out.println(DtlStatus.PROCESSING.getKey());
        System.out.println(DtlStatus.PROCESSING.getText());
        System.out.println(DtlStatus.valueOf("1").name());
    }

}
