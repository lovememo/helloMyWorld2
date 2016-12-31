package com.opm.data;

import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.common.model.ResultModel;
import com.opm.data.common.proxy.CallbackProxy;
import com.opm.data.common.proxy.DynamicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.datasource.DynamicDataSourceRegister;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan({ "com.opm.data", "com.opm.common" })
@EnableEurekaClient
@Import({DynamicDataSourceRegister.class})
@RestController
public class ApplicationData {


    @RequestMapping("/hello1")
    public String hello1(){
        RequestModel requestModel = new RequestModel("a","b");
        DynamicFeignClient c = this.callbackProxy.getCallbackClient("core","/hello");
        ResponseModel responseModel = c.callback(requestModel);
        return "ok";
    }

    @RequestMapping("/hello2")
    public String hello2(){
        RequestModel requestModel = new RequestModel("a","b");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<RequestModel> formEntity = new HttpEntity<RequestModel>(requestModel, headers);
        ResponseModel result = restTemplate.postForObject("http://localhost:8082/hello", formEntity, ResponseModel.class);
        return "ok";
    }

    @Autowired
    private CallbackProxy callbackProxy;

    @RequestMapping("/hello")
    public String hello(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationData.class, args);
    }
}
