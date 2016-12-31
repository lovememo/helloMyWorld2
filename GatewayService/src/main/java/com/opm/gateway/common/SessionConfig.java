package com.opm.gateway.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kfzx-liuyz1 on 2016/12/22.
 */
@Configuration
public class SessionConfig {

    @Value("${session.timeout}")
    private int sessionTimeout;

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

}
