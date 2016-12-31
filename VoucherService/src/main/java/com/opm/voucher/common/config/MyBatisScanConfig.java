package com.opm.voucher.common.config;

/**
 * Created by kfzx-liuyz1 on 2016/10/26.
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.opm.voucher.*.mapper")
public class MyBatisScanConfig {

}