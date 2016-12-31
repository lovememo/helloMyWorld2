package com.opm.gateway.common;

import ch.qos.logback.classic.Logger;
import com.opm.gateway.session.SessionCleanTask;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Timer;

/**
 * Created by kfzx-liuyz1 on 2016/10/28.
 */
@Component
public class AppInfoStartUpRunner implements CommandLineRunner {

    @Autowired
    private SessionConfig sessionConfig;

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AppInfoStartUpRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info("start session clean task...");
        Timer timer = new Timer();
        SessionCleanTask sessionCleanTask = new SessionCleanTask();
        LOGGER.info("Initializing SessionCleanTask,the session_out_time is " + sessionConfig.getSessionTimeout() + " minutes.");
        timer.schedule(sessionCleanTask, new Date(), sessionConfig.getSessionTimeout() * 60 * 1000 * 2);
    }
}
