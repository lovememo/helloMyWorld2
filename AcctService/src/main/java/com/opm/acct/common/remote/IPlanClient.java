package com.opm.acct.common.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.opm.acct.mdt.model.PlanBasicInfoModel;


@FeignClient(name="core")
public interface IPlanClient {

	 @RequestMapping(value = "/plan/planInf/{planId}",method = RequestMethod.GET)
	 public PlanBasicInfoModel getPlanInfByPlanId(@PathVariable("planId") String planId);
    

}