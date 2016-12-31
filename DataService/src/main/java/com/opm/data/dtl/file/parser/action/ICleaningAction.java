package com.opm.data.dtl.file.parser.action;

import com.opm.data.dtl.model.DtlData;

import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/19.
 */
public interface ICleaningAction {
    /**
     * 处理文件结果
     * @param resultList
     * @throws Exception
     */
    public void doProcess(List<DtlData> resultList) throws Exception;

    public void setSerialNo(String serialNo);

    public String getSerialNo();
}
