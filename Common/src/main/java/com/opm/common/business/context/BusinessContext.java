package com.opm.common.business.context;

import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/12/12.
 */
public class BusinessContext {

    private static ThreadLocal<ContextHolder> contextHolder = new ThreadLocal<ContextHolder>();

    public static Object get(String key) {
        if(BusinessContext.contextHolder.get() == null){
            return null;
        }

        return BusinessContext.contextHolder.get().get(key);
    }

    public static Map<String,Object> getAll() {
        if(BusinessContext.contextHolder.get() == null){
            return null;
        }
        return BusinessContext.contextHolder.get().getAll();
    }

    public static void put(String key, Object valObj) {
        ContextHolder contextHolder = BusinessContext.contextHolder.get();
        if(contextHolder == null){
            ContextHolder newContextHolder = new ContextHolder();
            newContextHolder.put(key,valObj);
            BusinessContext.contextHolder.set(newContextHolder);
        }
        else{
            contextHolder.put(key,valObj);
        }
    }

    public static void putAll(Map<String,Object> contextMap){

        if(contextMap == null){
            return;
        }
        ContextHolder contextHolder = BusinessContext.contextHolder.get();
        if(contextHolder == null){
            ContextHolder newContextHolder = new ContextHolder();
            newContextHolder.putAll(contextMap);
            BusinessContext.contextHolder.set(newContextHolder);
        }
        else{
            contextHolder.putAll(contextMap);
        }
    }
}
