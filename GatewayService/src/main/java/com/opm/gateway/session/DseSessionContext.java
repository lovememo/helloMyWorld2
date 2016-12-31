package com.opm.gateway.session;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.netflix.zuul.context.RequestContext;
import com.opm.gateway.enumtype.SessionAttribute;

/**
 * Created by kfzx-liuyz1 on 2016/12/22.
 */
public class DseSessionContext {

    private ConcurrentMap<String, DseSession> sessionMap = new ConcurrentHashMap<String, DseSession>(); // session数据

    private DseSessionContext(){

    }

    private static class DseSessionContextHolder {
        private static DseSessionContext instance = new DseSessionContext();
    }

    public static DseSessionContext getDseSessionContext() {
        return DseSessionContextHolder.instance;
    }



    public void addSession(String sessionId) {
        DseSession dseSession = new DseSession();
        dseSession.setLastVisitTime(new Date());
        DseSessionContextHolder.instance.sessionMap.put(sessionId, dseSession);
    }


    /**
     * 获取session
     */
    private DseSession getSessionById(String dseSessionId) {
        String sessionId = dseSessionId;
        if (StringUtils.isEmpty(sessionId)) {
            return null;
        }
        DseSession dseSession = DseSessionContextHolder.instance.sessionMap.get(sessionId);
        if (null == dseSession) {
            dseSession = new DseSession();
            DseSessionContextHolder.instance.sessionMap.put(sessionId, dseSession);
        }

        this.setSessionLastTime(sessionId);

        return dseSession;
    }

    /**
     * 获取session
     */
    public DseSession getSession(RequestContext requestContext) {
        String sessionId = this.getSessionId(requestContext);
        return this.getSessionById(sessionId);
    }

    /**
     * 获取session
     */
    public DseSession getSession(HttpServletRequest httpServletRequest) {
        Object sessionId = httpServletRequest.getParameter(SessionAttribute.DSE_SESSIONID);
        if(sessionId == null){
            return null;
        }
        else{
            return this.getSessionById(sessionId.toString());
        }
    }

    /**
     * 获取sessionId
     */
    public String getSessionId(RequestContext requestContext) {

        String sessionId = requestContext.getRequest().getParameter(SessionAttribute.DSE_SESSIONID);
        if(StringUtils.isNotEmpty(sessionId)){
            return sessionId;
        }

        return null;
    }

    /**
     * 获取所有session
     */
    public ConcurrentMap<String, DseSession> getAllSession() {
        return DseSessionContextHolder.instance.sessionMap;
    }

    /**
     * 设置session最后访问时间
     */
    public void setSessionLastTime(String sessionId) {
        DseSession dseSession = DseSessionContextHolder.instance.sessionMap.get(sessionId);
        dseSession.setLastVisitTime(new Date());
    }
}
