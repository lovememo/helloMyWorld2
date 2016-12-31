package com.opm.common.business.api;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by kfzx-liuyz1 on 2016/11/30.
 */
@FeignClient(name="voucher")
public interface VoucherBusinessApi extends IBusinessApi {
}
