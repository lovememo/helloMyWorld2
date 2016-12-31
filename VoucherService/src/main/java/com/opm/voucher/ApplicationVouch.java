package com.opm.voucher;


import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.api.CoreBusinessApi;
import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.datasource.DynamicDataSourceRegister;
import com.opm.voucher.order.trade.*;
import com.opm.voucher.order.trade.RemoteTestTask;
import com.opm.voucher.order.trade.TestTask2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan({"com.opm.voucher","com.opm.common"})
@EnableEurekaClient
@RestController
@Import({DynamicDataSourceRegister.class})
public class ApplicationVouch {

    @Autowired
    private CoreBusinessApi coreBusinessApi;

    @RequestMapping("/test")
    public RetResult test(@RequestBody Object valObj){

        return this.coreBusinessApi.check("TestTrade",(TaskParam)valObj);
    }

    @RequestMapping(value = "/testbusiness",method = RequestMethod.POST,consumes="application/json")
    public RetResult testBusiness(@RequestBody TaskParam valObj) {
        CommonBusiness testCommonBusiness = new CommonBusiness();
        return testCommonBusiness
                .register(TestTask2.class,new StringBuffer("aaa"))
                .register(RemoteTestTask.class,new StringBuffer("bbb"))
                .register(RemoteTestTask3.class,new StringBuffer("ccc"))
                .doBusiness();
    }

    public static void main(String[] args){
        SpringApplication.run(ApplicationVouch.class,args);
    }

}
