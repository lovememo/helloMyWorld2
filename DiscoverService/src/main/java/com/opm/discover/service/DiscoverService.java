package com.opm.discover.service;

import ch.qos.logback.classic.Logger;
import com.opm.discover.monitor.MicroServiceInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kfzx-liuyz1 on 2016/12/23.
 */
@Service
public class DiscoverService {

    @Value("${monitor.appName}")
    private String appName;

    @Autowired
    private DiscoveryClient discoveryClient;

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DiscoverService.class);


    public Set<MicroServiceInfo> discoverMicroService(){
        Set<MicroServiceInfo> microServiceInfoSet = new HashSet<MicroServiceInfo>();
        String localHostIp = null;
        try{
            List<String> serviceIds = this.discoveryClient.getServices();
            if(!CollectionUtils.isEmpty(serviceIds)){
                for(String s : serviceIds){
                    List<ServiceInstance> serviceInstances = this.discoveryClient.getInstances(s);
                    if(!CollectionUtils.isEmpty(serviceInstances)){
                        for(ServiceInstance si:serviceInstances){
                            //buf.append("["+si.getServiceId() +" host=" +si.getHost()+" port="+si.getPort()+" uri="+si.getUri()+"]");
                            MicroServiceInfo microServiceInfo = new MicroServiceInfo(this.appName,si.getHost(),si.getServiceId());
                            microServiceInfoSet.add(microServiceInfo);
                        }
                    }
                }
            }
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
        }



//        try {
//            InetAddress addr = InetAddress.getLocalHost();
//            localHostIp = addr.getHostAddress();
//        } catch (UnknownHostException e) {
//            LOGGER.error(e.getMessage());
//        }

        //microServiceInfoSet.add(new MicroServiceInfo(this.appName,localHostIp,this.selfName));

        return microServiceInfoSet;
    }

}
