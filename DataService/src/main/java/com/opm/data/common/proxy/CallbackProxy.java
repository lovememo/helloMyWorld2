package com.opm.data.common.proxy;

import feign.Client;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kfzx-liuyz1 on 2016/12/28.
 */
@Component
public class CallbackProxy {

    @Autowired
    private Client feignClient;

    private final String protocal = "http://";

    //getCallbackClient("core","/hello");
    public DynamicFeignClient getCallbackClient(String serviceId, String url){
        String serviceIdAndUrl = serviceId + url;
        return getCallbackClient(serviceIdAndUrl);
    }

    //getCallbackClient("core/hello");
    //getCallbackClient("/core/hello");
    public DynamicFeignClient getCallbackClient(String serviceIdAndUrl){
        String tmpUrl = serviceIdAndUrl.startsWith("/") ? serviceIdAndUrl.substring(1) : serviceIdAndUrl;
        String requestUrl = this.protocal + tmpUrl;
        DynamicFeignClient client = Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .client(feignClient)
                .target(DynamicFeignClient.class, requestUrl);

        return client;
    }
}
