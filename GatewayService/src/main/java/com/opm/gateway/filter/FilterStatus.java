package com.opm.gateway.filter;

/**
 * Created by kfzx-liuyz1 on 2016/12/21.
 */
public enum FilterStatus {

    SESSION_TIMEOUT(1408,"ZUUL_SESSION_TIMEOUT");

    private String bodyMsg;
    private int code;

    private FilterStatus(int code,String bodyMsg){
        this.code = code;
        this.bodyMsg = bodyMsg;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String geBodyMsg() {
        return bodyMsg;
    }

    public void setBodyMsg(String bodyMsg) {
        this.bodyMsg = bodyMsg;
    }


}
