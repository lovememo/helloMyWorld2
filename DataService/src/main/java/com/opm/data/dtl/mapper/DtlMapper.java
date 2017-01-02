package com.opm.data.dtl.mapper;

import com.opm.data.dtl.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/15.
 */
public interface DtlMapper {
    /**
     * 保存文件
     *
     * @param dtlFile
     * @return
     */
    public void saveFile(DtlFile dtlFile);

    /**
     * 读取文件信息
     * @param dtlControl
     */
    public List<DtlFile> readFile(DtlControl dtlControl);

    /**
     * 数据处理登记信息
     * @param dtlControl
     * @return serialNo
     */
    public int registerDtlInfo(DtlControl dtlControl);

    /**
     * 更新文件处理状态
     * @param dtlControl
     * @return
     */
    public int updateDtlInfo(DtlControl dtlControl);

    /**
     * 更新解析数据的状态
     * @param dtlData
     * @return
     */
    public int updateDtlDataInfo(DtlData dtlData);

    /**
     *  查询上传的文件列表
     *
     /**
     *翻页查询上传的文件列表
     * @param serialNo 文件序列号
     * @param beginPos 开始位置(包含)
     * @param fetchNum 抽取数
     * @return
     */
    public List<DtlFile> queryFileUploadedList(@Param("serialNo") String serialNo, @Param("beginPos") String beginPos, @Param("fetchNum") String fetchNum);

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
    public List<DtlRule> queryDtlRule(@Param("ruleId") String ruleId);
}
