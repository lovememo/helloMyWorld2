package com.opm.common.model;

import java.io.Serializable;

public class ResultModel implements Serializable {
    /**
	 * UID
	 */
	private static final long serialVersionUID = -8123153964071860937L;

	private String retCode;

    private String retMsg;

    public ResultModel() {
        this("", "");
    }

    public ResultModel(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
