package com.opm.data.dtl.dao;

import com.opm.data.dtl.model.*;

import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/14.
 */
public interface IDtlDataDao {
    /**
     * 保存数据
     *
     * @param dataInfoList
     * @return
     */
    public boolean insertDataInfo(List<String[]> dataInfoList);

    /**
     * 保存文件
     * @param dtlFile
     */
    public void saveFile(DtlFile dtlFile);

    /**
     * 读取文件信息
     * @param dtlControl
     */
    public DtlFile readFile(DtlControl dtlControl);

    /**
     * 文件处理登记
     * @param dtlControl
     */
    public void registerDtlInfo(DtlControl dtlControl) throws Exception;

    /**
     * 更新文件处理状态
     * @param dtlControl
     * @return
     */
    public boolean updateDtlInfo(DtlControl dtlControl);

    /**
     * 更新解析数据的状态
     * @param dtlData
     * @return
     */
    public int updateDtlDataInfo(DtlData dtlData);


    /**
     * 插入解析的数据
     * @param resultList
     * @return
     */
    public boolean insertOpmDtlDataInf(List<DtlData> resultList);

    /**
     *翻页查询上传的文件列表
     * @param serialNo 文件序列号
     * @param beginPos 开始位置
     * @param fetchNum 抽取数
     * @return
     */
    public List<DtlFile> queryFileUploadedList(String serialNo, String beginPos, String fetchNum);

    /**
     * 翻页查询上传的文件列表总数
     * @param serialNo
     * @return
     */
    public List queryFileUploadedListTotalNum(String serialNo);

    /**
     * 查询处理过的数据记录总条数
     * @param serialNo
     * @return
     */
    public List<Integer> queryProcessedDataCount(String serialNo);

    /**
     * 查询数据清洗项目
     * @param dtlControl
     * @return
     */
    public List<DtlItem> queryDtlItem(DtlControl dtlControl);

    /**
     * 查询规则集合
     * @param ruleId
     * @return
     */
    public List<DtlRule> queryDtlRule(String ruleId);
}
