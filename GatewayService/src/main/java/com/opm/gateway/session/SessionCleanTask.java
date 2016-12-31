package com.opm.gateway.session;

import com.opm.gateway.common.SessionConfig;
import com.opm.gateway.common.SpringContextHolder;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by kfzx-liuyz1 on 2016/12/22.
 */
public class SessionCleanTask extends TimerTask {

    @Override
    public void run() {
        ConcurrentMap<String, DseSession> sessionMap = DseSessionContext.getDseSessionContext().getAllSession();

        Iterator<Map.Entry<String, DseSession>> it = sessionMap.entrySet().iterator();
        while (it.hasNext()) {
            ConcurrentMap.Entry<String, DseSession> entry = (Map.Entry<String, DseSession>) it.next();
            DseSession dseSession= entry.getValue();

            Date nowDate = new Date();
            int diff = (int) ((nowDate.getTime() - dseSession.getLastVisitTime().getTime())/1000/60);

            SessionConfig sessionConfig = SpringContextHolder.getBean(SessionConfig.class);
            assert sessionConfig != null;
            if (diff > sessionConfig.getSessionTimeout()) {
                it.remove();
            }
        }
    }
}
