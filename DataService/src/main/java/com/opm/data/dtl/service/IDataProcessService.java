package com.opm.data.dtl.service;

import com.opm.data.dtl.dao.impl.DtlDataDao;
import com.opm.data.dtl.model.DtlControl;
import com.opm.data.dtl.model.DtlFile;

import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/14.
 */
public interface IDataProcessService {
    /**
     * 处理上传的文件，并生成文件号
     * @param file
     * @param fileCtrlInfo
     * @return
     */
    //public DtlFile processUploadedFile(MultipartFile file, String fileName, DtlControl fileCtrlInfo) throws Exception;

    /**
     * 下载文件
     * @param serialNo
     * @return 文件字节流
     */
    public DtlFile readFile(String serialNo);
    /**
     *翻页查询上传的文件列表
     * @param serialNo 文件序列号
     * @param beginPos 开始位置
     * @param fetchNum 抽取数
     * @return
     */
    public List<Object> turnPageFileUploadedList(String serialNo, String beginPos, String fetchNum);

    /**
     * 获取文件解析后的数据行数
     * @param serialNo
     * @return
     */
    public int getDataProcessedCount(String serialNo);

    /**
     * 获取数据处理控制信息
     * @param dtlItemId
     * @return
     */
    public DtlControl getCtrlInfo(String dtlItemId) throws Exception;
    public DtlDataDao getDtlDataDao();
}
