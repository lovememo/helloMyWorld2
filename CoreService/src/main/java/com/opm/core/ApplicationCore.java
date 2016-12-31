package com.opm.core;

import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.common.model.ResultModel;
import com.opm.core.common.enumdict.ReviewResultType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;

import com.opm.common.datasource.DynamicDataSourceRegister;

/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan({ "com.opm.core", "com.opm.common" })
@EnableEurekaClient
@RestController
@Import({DynamicDataSourceRegister.class})
public class ApplicationCore {

	@RequestMapping(value = "/hello",method = RequestMethod.POST,produces="application/json")
	public ResponseModel hello(@RequestBody RequestModel body) {
		ResponseModel responseModel = new ResponseModel(com.opm.common.model.ResponseModel.State.SUCC,"HELLO","HELLO");
//		ResultModel resultModel = new ResultModel("200","300");
//		return resultModel;
		return responseModel;
	}


	public static void main(String[] args) {
		SpringApplication.run(ApplicationCore.class, args);
	}
}
