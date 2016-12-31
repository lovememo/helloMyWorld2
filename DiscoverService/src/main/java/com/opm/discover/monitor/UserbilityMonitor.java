package com.opm.discover.monitor;

import com.opm.discover.support.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kfzx-liuyz1 on 2016/12/23.
 */
public class UserbilityMonitor extends MonitorRunner implements IMonitor{

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserbilityMonitor.class);

    @Override
    public void start() {

        try{
            MonitorConfig monitorConfig = SpringContextHolder.getBean(MonitorConfig.class);
            this.setMonitorConfig(monitorConfig);
            this.setPackage(new ServerUserbilityFormat());
            this.sendStart();
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
        }
    }
}
