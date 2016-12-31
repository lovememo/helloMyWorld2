package com.opm.acct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.datasource.DynamicDataSourceRegister;

/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan({ "com.opm.acct", "com.opm.common" })
@EnableEurekaClient
@Import({DynamicDataSourceRegister.class})
@RestController
public class ApplicationAcct {

	@RequestMapping("/hello")
	public String hello() {
		return " hello acct";
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationAcct.class, args);
	}
}
