package com.opm.data.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;


/**
 * Created by kfzx-liuyz1 on 2016/12/13.
 */
@Configuration
public class FileUploadConfig {

    //5M
    private final long maxFileSize = 5*1024*1024;

    //25M
    private final long maxRequestSize = 5*5*1024*1024;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        org.springframework.boot.web.servlet.MultipartConfigFactory factory = new org.springframework.boot.web.servlet.MultipartConfigFactory();
        factory.setMaxFileSize(this.maxFileSize);
        factory.setMaxRequestSize(this.maxRequestSize);

        return factory.createMultipartConfig();
    }

}
