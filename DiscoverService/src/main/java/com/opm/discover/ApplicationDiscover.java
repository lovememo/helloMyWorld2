package com.opm.discover;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */
@EnableEurekaServer
@SpringBootApplication
public class ApplicationDiscover {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationDiscover.class).web(true).run(args);
    }
}
