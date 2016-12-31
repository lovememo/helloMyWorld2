package com.opm.data.dtl.model;

import com.opm.common.enumdict.DtlStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kfzx-jinjf on 2016/12/15.
 */
public class DtlControl {
    private String    serialNo;
    private String    dtlItemId;
    private String    dtlStatus;
    private transient Date      invokeTime;
    private transient Date      returnTime;

    public DtlControl() {
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String value) {
        this.serialNo = value;
    }

    /**
     * 接口ID
     */
    public String getDtlItemId() {
        return dtlItemId;
    }

    public void setDtlItemId(String value) {
        this.dtlItemId = value;
    }

    /**
     * 1-处理中 2-处理成功 3-处理失败
     */
    public String getDtlStatus() {
        return dtlStatus;
    }

    public void setDtlStatus(String status) {
        this.dtlStatus = status;
    }
//    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 请求调用时间
     */
    public Date getInvokeTime() {
        return invokeTime;
    }

    public void setInvokeTime(Date value) {
        this.invokeTime = value;
    }

    /**
     * 应答返回时间
     */
    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date value) {
        this.returnTime = value;
    }
}
