package com.opm.data.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kfzx-liuyz1 on 2016/10/26.
 */

@Configuration
@MapperScan("com.opm.data.*.mapper")
public class MyBatisScanConfig {

}