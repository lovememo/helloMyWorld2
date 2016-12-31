package com.opm.core.investManager.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.core.investManager.entity.NetValueInfEntity;
import com.opm.core.investManager.model.NetValueAppFormModel2;
import com.opm.core.investManager.model.NetValueInfQueryFormModel;
import com.opm.core.investManager.service.INetValueService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/investmanager/netvalue/query")
public class NetValueQueryController {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(NetValueQueryController.class);
	
	@Autowired
	private INetValueService netValueService = null;
	
	
	/**
	 * 查询净值
	 * @param reqModel
	 * @return
	 * url:/investmanager/netvalue/list
	 */
	@RequestMapping(value = "/index",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel initQry(RequestModel reqModel) {

		String funcName = "查询净值"; //方法名称
		ResponseModel resModel = null;
		try {
			//参数整理
			String planId = reqModel.getStringValue("planId");
			//查询计划列表和定价日
			NetValueInfQueryFormModel netValueInfQueryFormModel = this.netValueService.initQryForm(planId);
			//返回结果
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", netValueInfQueryFormModel);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "失败", null);
			
		}
		//返回结果
		return resModel;
	}
	
	/**
	 * 净值明细
	 * @return
	 * url:/investmanager/netvalue/add
	 */
	@RequestMapping(value = "/detail",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel detail(RequestModel reqModel) {
		
		String funcName = "净值明细";
		ResponseModel resModel = null;
		try {
			//参数整理
			String qryPlanId = reqModel.getStringValue("qryPlanId");
			String evaluateDate = reqModel.getStringValue("evaluateDate");
			
			NetValueInfEntity netValueInfEntity = new NetValueInfEntity();
			netValueInfEntity.setPlanId(qryPlanId);
			netValueInfEntity.setEvaluateDate(evaluateDate);
			
			//查询净值明细
			NetValueAppFormModel2 netValueAppFormModel = this.netValueService.qryInfDesc(netValueInfEntity);
			
			//返回结果
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", netValueAppFormModel);
			
		} catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "失败", null);
		}

		
		return resModel;
	}
	
}
