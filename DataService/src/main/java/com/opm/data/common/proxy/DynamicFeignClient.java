package com.opm.data.common.proxy;

import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.common.model.ResultModel;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kfzx-liuyz1 on 2016/12/28.
 */

public interface DynamicFeignClient {

    @RequestLine("POST ")
    @Headers("Content-Type: application/json")
    public ResponseModel callback(@RequestBody RequestModel requestModel);
}
