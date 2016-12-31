package com.opm.common.business.context;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/12/12.
 */
public class ContextHolder{
    private Map<String,Object> holderMap = new HashMap<String,Object>();

    public Object get(String key){
        return this.holderMap.get(key);
    }

    public Map<String,Object> getAll(){
        return this.holderMap;
    }

    public void put(String key,Object valObj){
        this.holderMap.put(key,valObj);
    }

    public void putAll(Map<String,Object> contextMap){
        if(contextMap == null){
            return;
        }
        this.holderMap.putAll(contextMap);
    }

}
