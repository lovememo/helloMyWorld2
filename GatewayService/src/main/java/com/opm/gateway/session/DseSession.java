package com.opm.gateway.session;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/12/22.
 */
public class DseSession {

    private Map<String, Object> map = new HashMap<String, Object>();

    private Date lastVisitTime = new Date(); // 最后访问时间

    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Date lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public Map<String, Object> getMap() {
        return map;
    }

}
