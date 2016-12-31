package com.opm.core.investManager.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.model.ResponseModel;
import com.opm.core.investManager.service.INetValueService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("investmgr/interface")
public class NetValueInterFaceController {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(NetValueModifyController.class);

	@Autowired
	private INetValueService netValueService;
	@RequestMapping(value = "/evaluatedateinuse/{planId}/{evaluateDate}",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel evaluateDateInUse(@PathVariable("planId") String planId, 
			@PathVariable("evaluateDate") String evaluateDate){
		
		String funcName = "查询定价日是否使用";
		ResponseModel resModel = null;
		try {
			Boolean res = this.netValueService.itfEveluateDateInUse(planId, evaluateDate);
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName+"成功", res);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName+"失败", null);
		}
		
		return resModel;
	}
}
