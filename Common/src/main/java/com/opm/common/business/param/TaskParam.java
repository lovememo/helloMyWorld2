package com.opm.common.business.param;

import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/12/1.
 */
public class TaskParam {

    private Map<String,Object> contextParam;
    private Object prevParam;
    private Object selfParam;

    public Map<String, Object> getContextParam() {
        return contextParam;
    }

    public void setContextParam(Map<String, Object> contextParam) {
        this.contextParam = contextParam;
    }

    public TaskParam(){

    }
    public TaskParam(Object prevParam, Object selfParam) {
        this.prevParam = prevParam;
        this.selfParam = selfParam;
    }

    public TaskParam(Object prevParam, Object selfParam, Map<String, Object> contextParam) {
        this.prevParam = prevParam;
        this.selfParam = selfParam;
        this.contextParam = contextParam;
    }


    public Object getPrevParam() {
        return prevParam;
    }

    public void setPrevParam(Object prevParam) {
        this.prevParam = prevParam;
    }

    public Object getSelfParam() {
        return selfParam;
    }

    public void setSelfParam(Object selfParam) {
        this.selfParam = selfParam;
    }

}
