package com.opm.discover.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by kfzx-liuyz1 on 2016/12/26.
 */
@Component
public class MonitorCommandLineRunner implements CommandLineRunner {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MonitorCommandLineRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        MonitorManager.INSTANCE.register(new UserbilityMonitor());
        MonitorManager.INSTANCE.start();
    }
}
