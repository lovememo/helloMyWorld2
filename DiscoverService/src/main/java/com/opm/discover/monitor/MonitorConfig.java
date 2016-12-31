package com.opm.discover.monitor;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by kfzx-liuyz1 on 2016/12/26.
 */
@Component
public class MonitorConfig {

    @Value("${monitor.ip}")
    private String ip;
    @Value("${monitor.port}")
    private String port;
    @Value("${monitor.frequency}")
    private String frequency;

    public int getFrequency() {
        return Integer.parseInt(frequency);
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return Integer.parseInt(port);
    }

    public void setPort(String port) {
        this.port = port;
    }



}
