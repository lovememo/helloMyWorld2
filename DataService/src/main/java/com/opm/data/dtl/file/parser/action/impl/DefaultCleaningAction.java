package com.opm.data.dtl.file.parser.action.impl;

import com.opm.data.dtl.dao.IDtlDataDao;
import com.opm.data.dtl.file.parser.action.ICleaningAction;
import com.opm.data.dtl.model.DtlData;

import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/19.
 */
public class DefaultCleaningAction implements ICleaningAction {
    private IDtlDataDao dataInfoDao;

    private String serialNo = "";

    @Override
    public String getSerialNo() {
        return serialNo;
    }

    @Override
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public DefaultCleaningAction(String serialNo, IDtlDataDao dataInfoDao) {
        this.serialNo = serialNo;
        this.dataInfoDao = dataInfoDao;
    }

    @Override
    /**
     * 处理文件结果
     * @param resultList
     * @throws Exception
     */
    public void doProcess(List<DtlData> resultList) throws Exception {
        this.doCheck(resultList);//数据清理工作
        this.dataInfoDao.insertOpmDtlDataInf(resultList);
    }

    private void doCheck(List<DtlData> resultList) throws Exception {

    }
}
