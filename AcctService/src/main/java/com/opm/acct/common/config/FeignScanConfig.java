package com.opm.acct.common.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kfzx-liuyz1 on 2016/12/1.
 */
@Configuration
@EnableFeignClients(basePackages = {"com.opm.acct","com.opm.common"})
public class FeignScanConfig {
}
