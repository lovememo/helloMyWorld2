package com.opm.common.business.param;

import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/11/30.
 */
public class RetResult {

    private boolean result; //out_flag
    private String reason; //out_msg



    private Object returnObj; //本交易产生的且需要传给下一个交易的参数

    private Map<String,Object> contextObj;

    public RetResult(){

    }

    public RetResult( boolean result, String reason,Object returnObj) {
        this.returnObj = returnObj;
        this.reason = reason;
        this.result = result;
    }

    public RetResult(boolean _result,String _reason){
        this.reason = _reason;
        this.result = _result;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getReturnObj() {
        return this.returnObj;
    }

    public void setReturnObj(Object returnObj) {
        this.returnObj = returnObj;
    }

    public Map<String, Object> getContextObj() {
        return contextObj;
    }

    public void setContextObj(Map<String, Object> contextObj) {
        this.contextObj = contextObj;
    }
}
