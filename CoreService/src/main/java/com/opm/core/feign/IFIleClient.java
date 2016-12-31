package com.opm.core.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;

@FeignClient(name="data")
public interface IFIleClient {

	@RequestMapping(value="/dtl/cleaning",method=RequestMethod.GET)
    public ResponseModel clean(RequestModel requestModel) ;
}
