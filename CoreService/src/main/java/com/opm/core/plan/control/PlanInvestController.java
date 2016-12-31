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
import com.opm.core.plan.model.PlanInvestModel;
import com.opm.core.plan.service.IPlanInvestService;
import com.opm.core.plan.trade.PlanInvestAppPreTask;

@RestController
@RequestMapping("/plan")
public class PlanInvestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanInvestController.class);

	@Autowired
	private IPlanInvestService service;

	@RequestMapping(value = "/invest/saved", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel saved(RequestModel reqModel) {

		ResponseModel resModel = null;
		String funcName = "��ȡ�����Ͷ����Ϣ����";
		try {
			List<PlanInvestModel> list = service.selectPlanInvestApp(reqModel.getStringValue("appNo"), null, null);

			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", list);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}
		return resModel;
	}

	@RequestMapping(value = "/invest/submit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel investSave(RequestModel reqModel) {

		CommonBusiness business = new CommonBusiness();
		ResponseModel resModel = null;
		String type = reqModel.getStringValue("type");
		String funcName = "Ͷ�ʻ�����Ϣ";
		if ("add".equals(type)) {
			funcName = "����" + funcName;
		} else if ("update".equals(type)) {
			funcName = "�޸�" + funcName;
		} else if ("delete".equals(type)) {
			funcName = "�h��" + funcName;
		}
		try {

			RetResult ret = business.register(PlanInvestAppPreTask.class, reqModel.getPrivateCtx()).doBusiness();

			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", ret.getReturnObj());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}
		return resModel;
	}
}
