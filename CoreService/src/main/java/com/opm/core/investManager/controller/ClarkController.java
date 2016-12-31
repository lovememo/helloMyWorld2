package com.opm.core.investManager.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.param.RetResult;
import com.opm.common.model.RequestModel;
import com.opm.core.investManager.feign.IPlanClient;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.service.IAppService;
import com.opm.core.app.trade.AppTask;
import com.opm.core.plan.model.PlanBasicInfoModel;

@RestController
@RequestMapping("/clark/test")
public class ClarkController {
	
	@Autowired
	private IAppService appService;
	
	@Autowired
	private IPlanClient planClient;
	
	@RequestMapping(value = "/testapp",method = {RequestMethod.GET, RequestMethod.POST})
	public String testApp(AppModel appModel){
		return appService.saveApp(appModel).getAppNo();
	}
	
	@RequestMapping(value = "/testfeign/{planId}",method = RequestMethod.GET)
	public PlanBasicInfoModel testFeign(@PathVariable String planId) {
		return this.planClient.getPlanInfByPlanId(planId);
	}

	public IAppService getAppService() {
		return appService;
	}

	public void setAppService(IAppService appService) {
		this.appService = appService;
	}

	public IPlanClient getPlanClient() {
		return planClient;
	}

	public void setPlanClient(IPlanClient planClient) {
		this.planClient = planClient;
	}
	
}
