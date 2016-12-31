package com.opm.core.investManager.controller;

import java.util.List;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.param.RetResult;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.model.AppQryParam;
import com.opm.core.app.trade.AppTask;
import com.opm.core.app.trade.TrdNoByAppNoQryStep;
import com.opm.core.investManager.model.NetValueAppFormModel;
import com.opm.core.investManager.service.INetValueService;
import com.opm.core.investManager.trade.NetValueAppDetTask;
import com.opm.core.investManager.trade.NetValueTrdDoTask;
import com.opm.core.investManager.trade.NetValueTrdPreTask;
import com.opm.core.investManager.trade.channel.AppChannel;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/investmanager/netvalue/modify")
public class NetValueModifyController {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(NetValueModifyController.class);
	
	@Autowired
	private INetValueService netValueService = null;
	
	
	/**
	 * 查询已保存的净值修改申请
	 * @param reqModel
	 * @return
	 * url:/investmanager/netvalue/list
	 */
	@RequestMapping(value = "/list",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel list(RequestModel reqModel) {
		//方法名称
		String funcName = "查询净值录入保存状态的申请";
		
		ResponseModel resModel = null;
		try {
			//参数整理
			String planId = reqModel.getStringValue("planId");
			AppQryParam appQryParam = new AppQryParam();
			appQryParam.setPlanId(planId);
			appQryParam.setAppState("8");
			
			//查询保持的净值申请
			List appList = this.netValueService.qryOpmApp(appQryParam);

			//返回结果
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", appList);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "失败", null);
			
		}
		//返回结果
		return resModel;
	}
	
	/**
	 * 加载保存后的内容
	 * @param reqModel
	 * @return
	 */
	@RequestMapping(value = "/mod",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel mod(RequestModel reqModel) {
		
		String funcName = "";
		ResponseModel resModel = null;
		try{
			//参数整理
			String appNo = reqModel.getStringValue("appNo");

			//查询
			NetValueAppFormModel netValueAppFormModel = this.netValueService.qryAppDet(appNo);

			//返回结果
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName+"成功", netValueAppFormModel);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName+"失败", null);
		}
		return resModel;
		
	}
	
	/**
	 * 提交净值录入
	 * @param params
	 * @return
	 * url:/investmanager/netvalue/sumbit
	 */
	@RequestMapping(value = "/sumbit",method = {RequestMethod.POST,RequestMethod.GET})
	public Object submit(RequestModel reqModel){
		CommonBusiness netValueBusiness = new CommonBusiness();
		RetResult brr = netValueBusiness
			.register(AppTask.class, reqModel) //保存申请
			.register(NetValueAppDetTask.class, reqModel) //保存净值申请明细
			.register(NetValueTrdPreTask.class, reqModel) //保存净值交易明细
		.doBusiness();
		return brr.getReturnObj();
	}

	/**
	 * 复核净值录入
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/audit",method = {RequestMethod.POST,RequestMethod.GET})
	public String audit(RequestModel reqModel){

		CommonBusiness netValueBusiness = new CommonBusiness();
		RetResult brr = netValueBusiness
			.register(AppTask.class, reqModel, AppChannel.class)
			.register(TrdNoByAppNoQryStep.class, reqModel) 
			.register(NetValueTrdDoTask.class, reqModel)
			.register(AppTask.class, reqModel, AppChannel.class) //更新申请状态
		.doBusiness();
		return (String)brr.getReturnObj();
		
	}
	
}
