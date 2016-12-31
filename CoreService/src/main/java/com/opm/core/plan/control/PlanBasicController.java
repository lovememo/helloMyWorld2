package com.opm.core.plan.control;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.param.RetResult;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.core.app.task.AppAudTask;
import com.opm.core.plan.model.PlanBasicInfoModel;
import com.opm.core.plan.service.IPlanService;
import com.opm.core.plan.trade.PlanBasicAppPreTask;
import com.opm.core.plan.trade.PlanBasicTrdDoTask;
import com.opm.core.plan.trade.PlanBasicTrdPreTask;

@RestController
@RequestMapping("/plan")
public class PlanBasicController {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger(PlanBasicController.class);
	
	@Autowired
	private IPlanService planService;

	@RequestMapping(value = "/basic/save", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel basicSave(RequestModel reqModel) {

		CommonBusiness business = new CommonBusiness();
		ResponseModel resModel = null;
		String funcName = "保存计划基本信息";
		try{
			
		RetResult ret = business.register(AppAudTask.class, reqModel.getPrivateCtx()) // 保存申请
				.register(PlanBasicAppPreTask.class, reqModel.getPrivateCtx()) // 保存交易
				.doBusiness();
		
		resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", ret.getContextObj());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "失败", null);
		}
		return resModel;
	}

	@RequestMapping(value = "/saved", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel saved(RequestModel reqModel) {

		ResponseModel resModel = null;
		String funcName = "获取保存的计划信息申请";
		try {
			List<Map<String, String>> list = planService.selectSavedPlanApp();

			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "失败", null);
		}
		return resModel;
	}

	@RequestMapping(value = "/basic/saved", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel basicSaved(RequestModel reqModel) {

		ResponseModel resModel = null;
		String funcName = "获取保存的计划基本信息";
		try {
			String appNo = reqModel.getStringValue("appNo");
			PlanBasicInfoModel model = planService.selectPlanBasicApp(appNo);

			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", model);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "失败", null);
		}
		return resModel;
	}
	

	@RequestMapping(value = "/basic/submit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel basicSubmit(RequestModel reqModel) {

		CommonBusiness business = new CommonBusiness();
		ResponseModel resModel = null;
		String funcName = "提交计划基本信息";
		try{
			
		RetResult ret = business.register(AppAudTask.class, reqModel.getPrivateCtx()) // 提交申请
				.register(PlanBasicAppPreTask.class, reqModel.getPrivateCtx()) // 保存app
				.register(PlanBasicTrdPreTask.class, reqModel.getPrivateCtx()) // 提交交易
				.doBusiness();
		
		resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", ret.getContextObj());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "失败", null);
		}
		return resModel;
	}
	
	@RequestMapping(value = "/basic/audit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel basicAudit(RequestModel reqModel) {

		CommonBusiness business = new CommonBusiness();
		ResponseModel resModel = null;
		String funcName = "复核计划基本信息";
		try{
			
		RetResult ret = business.register(AppAudTask.class, reqModel.getPrivateCtx()) // 提交申请
				.register(PlanBasicTrdDoTask.class, reqModel.getPrivateCtx()) // 提交交易
				.doBusiness();
		
		resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", ret.getContextObj());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "失败", null);
		}
		return resModel;
	}
}
