package com.opm.discover.monitor;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kfzx-liuyz1 on 2016/12/23.
 */
public class MonitorManager {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MonitorManager.class);

    public static final MonitorManager INSTANCE = new MonitorManager();

    private List<IMonitor> monitorList = new ArrayList<IMonitor>();

    private MonitorManager(){}

    public void register(IMonitor monitor){
        this.monitorList.add(monitor);
    }

    public void start(){
        for(IMonitor monitor : this.monitorList){
            monitor.start();
        }
    }
}
