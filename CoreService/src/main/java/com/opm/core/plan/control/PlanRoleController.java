package com.opm.core.plan.control;

import java.util.List;

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
import com.opm.core.plan.model.PlanRoleModel;
import com.opm.core.plan.service.IPlanRoleService;
import com.opm.core.plan.trade.PlanRoleAppPreTask;

@RestController
@RequestMapping("/plan")
public class PlanRoleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanRoleController.class);

	@Autowired
	private IPlanRoleService service;

	@RequestMapping(value = "/role/saved", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel saved(RequestModel reqModel) {

		ResponseModel resModel = null;
		String funcName = "获取保存的角色人信息申请";
		try {
			List<PlanRoleModel> list = service.selectPlanRoleApp(reqModel.getStringValue("appNo"), null, null);

			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "失败", null);
		}
		return resModel;
	}

	@RequestMapping(value = "/role/submit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel roleSaved(RequestModel reqModel) {

		CommonBusiness business = new CommonBusiness();
		ResponseModel resModel = null;
		String type = reqModel.getStringValue("type");
		String funcName = "角色人基本信息";
		if ("add".equals(type)) {
			funcName = "新增" + funcName;
		} else if ("update".equals(type)) {
			funcName = "修改" + funcName;
		} else if ("delete".equals(type)) {
			funcName = "刪除" + funcName;
		}
		try {

			RetResult ret = business.register(PlanRoleAppPreTask.class, reqModel.getPrivateCtx()).doBusiness();

			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", ret.getReturnObj());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "失败", null);
		}
		return resModel;
	}
}
