package com.opm.discover.monitor;

import com.opm.discover.service.DiscoverService;
import com.opm.discover.support.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kfzx-liuyz1 on 2016/12/26.
 */
public class MonitorRunner {
    protected IFormat format;
    protected MonitorConfig monitorConfig;
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger("monitor");

    public void setMonitorConfig(MonitorConfig monitorConfig){
        this.monitorConfig = monitorConfig;
    }

    public void setPackage(IFormat format){
        this.format = format;
    }

    public void sendStart(){
        Timer timer = new Timer();
        SendTask sendTask = new SendTask();
        timer.schedule(sendTask, new Date(), this.monitorConfig.getFrequency() * 6 * 1000);
    }


    class SendTask extends TimerTask{

        @Override
        public void run() {
            try{
                DiscoverService discoverService = SpringContextHolder.getBean(DiscoverService.class);
                if(null != discoverService){
                    Set<MicroServiceInfo> microServiceInfoSet = discoverService.discoverMicroService();
                    DatagramSocket client = new DatagramSocket();
                    for(MicroServiceInfo microServiceInfo : microServiceInfoSet){
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        MonitorRunner.this.format.doFormat(microServiceInfo,outputStream);
                        byte[] sendBuf = outputStream.toString().getBytes();
                        InetAddress addr = InetAddress.getByName(MonitorRunner.this.monitorConfig.getIp());
                        DatagramPacket sendPacket = new DatagramPacket(sendBuf ,sendBuf.length , addr , MonitorRunner.this.monitorConfig.getPort());
                        client.send(sendPacket);
                        LOGGER.info(outputStream.toString());
                        outputStream.close();
                    }
                    client.close();
                }

            }
            catch (Exception e){
                LOGGER.error(e.getMessage());
            }
        }
    }
}
