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
		String funcName = "��ȡ����Ľ�ɫ����Ϣ����";
		try {
			List<PlanRoleModel> list = service.selectPlanRoleApp(reqModel.getStringValue("appNo"), null, null);

			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", list);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}
		return resModel;
	}

	@RequestMapping(value = "/role/submit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel roleSaved(RequestModel reqModel) {

		CommonBusiness business = new CommonBusiness();
		ResponseModel resModel = null;
		String type = reqModel.getStringValue("type");
		String funcName = "��ɫ�˻�����Ϣ";
		if ("add".equals(type)) {
			funcName = "����" + funcName;
		} else if ("update".equals(type)) {
			funcName = "�޸�" + funcName;
		} else if ("delete".equals(type)) {
			funcName = "�h��" + funcName;
		}
		try {

			RetResult ret = business.register(PlanRoleAppPreTask.class, reqModel.getPrivateCtx()).doBusiness();

			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", ret.getReturnObj());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}
		return resModel;
	}
}
