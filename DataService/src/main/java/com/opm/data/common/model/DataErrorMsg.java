package com.opm.data.common.model;

/**
 * Created by kfzx-jinjf on 2016/12/16.
 */
public class DataErrorMsg {
    private String flag = "";
    private String msg = "";

    public DataErrorMsg(String msg) {
        this.flag = "1";
        this.msg = msg;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
