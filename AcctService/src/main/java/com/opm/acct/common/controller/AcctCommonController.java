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
		String funcName = "��ѯ���л������־";
		@SuppressWarnings("rawtypes")
		Map params = reqModel.getPrivateCtx();
		ResponseModel resModel = new ResponseModel(ResponseModel.State.SUCC, 
				funcName + "�ɹ�", iPlanClient.getPlanInfByPlanId(params.get("planId")+""));
		return resModel;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/isFinishMonend", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel isFinishMonend(RequestModel reqModel){
		String funcName = "�ж��Ƿ�����½�";
		Map params = reqModel.getPrivateCtx();
		if (null == params || !params.containsKey("planId") || !params.containsKey("date")) {
			return new ResponseModel(ResponseModel.State.FAIL, 
					funcName + "ʧ��, �޲����������ȫ", null);
		}
		RetResult ret = mdtDayendService.isFinishMonend(params.get("planId")+"", params.get("date")+"");
		if (ret.getResult()) {
			return new ResponseModel(ResponseModel.State.SUCC, 
					funcName + "�ɹ�", ret.getReturnObj());
		}
		return new ResponseModel(ResponseModel.State.FAIL, 
				funcName + "ʧ��", ret.getReturnObj());
	}
	
	
	@RequestMapping(value = "/getSysdate", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel getSysdate(){
		String funcName = "��ѯϵͳʱ��";
		ResponseModel resModel = new ResponseModel(ResponseModel.State.SUCC, 
				funcName + "�ɹ�", CommonUtils.getCurrentDate());
		return resModel;
	}
	
	@RequestMapping(value = "/firstDayNoEnd", method = { RequestMethod.POST, RequestMethod.GET })
	public String getFirstDayNoEnd(String planId){
		return mdtDayendService.getFirstDayNoEnd(planId);
	}
	
	
	
	
}
