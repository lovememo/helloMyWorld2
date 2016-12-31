package com.opm.acct.common.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.acct.common.CommonUtils;
import com.opm.acct.common.remote.IPlanClient;
import com.opm.acct.mdt.dayend.service.IMdtDayendService;
import com.opm.common.business.param.RetResult;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;


@RestController
@RequestMapping("/manacct/common")
public class AcctCommonController {
	
	@Autowired
	private IMdtDayendService mdtDayendService;
	
	@Autowired
	private IPlanClient iPlanClient;
	
	@RequestMapping(value = "/planInf", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel getPlanInf(RequestModel reqModel){
		String funcName = "查询受托户核算标志";
		@SuppressWarnings("rawtypes")
		Map params = reqModel.getPrivateCtx();
		ResponseModel resModel = new ResponseModel(ResponseModel.State.SUCC, 
				funcName + "成功", iPlanClient.getPlanInfByPlanId(params.get("planId")+""));
		return resModel;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/isFinishMonend", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel isFinishMonend(RequestModel reqModel){
		String funcName = "判断是否完成月结";
		Map params = reqModel.getPrivateCtx();
		if (null == params || !params.containsKey("planId") || !params.containsKey("date")) {
			return new ResponseModel(ResponseModel.State.FAIL, 
					funcName + "失败, 无参数或参数不全", null);
		}
		RetResult ret = mdtDayendService.isFinishMonend(params.get("planId")+"", params.get("date")+"");
		if (ret.getResult()) {
			return new ResponseModel(ResponseModel.State.SUCC, 
					funcName + "成功", ret.getReturnObj());
		}
		return new ResponseModel(ResponseModel.State.FAIL, 
				funcName + "失败", ret.getReturnObj());
	}
	
	
	@RequestMapping(value = "/getSysdate", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel getSysdate(){
		String funcName = "查询系统时间";
		ResponseModel resModel = new ResponseModel(ResponseModel.State.SUCC, 
				funcName + "成功", CommonUtils.getCurrentDate());
		return resModel;
	}
	
	@RequestMapping(value = "/firstDayNoEnd", method = { RequestMethod.POST, RequestMethod.GET })
	public String getFirstDayNoEnd(String planId){
		return mdtDayendService.getFirstDayNoEnd(planId);
	}
	
	
	
	
}
