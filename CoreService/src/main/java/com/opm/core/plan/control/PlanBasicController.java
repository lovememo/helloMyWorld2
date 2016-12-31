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
		String funcName = "����ƻ�������Ϣ";
		try{
			
		RetResult ret = business.register(AppAudTask.class, reqModel.getPrivateCtx()) // ��������
				.register(PlanBasicAppPreTask.class, reqModel.getPrivateCtx()) // ���潻��
				.doBusiness();
		
		resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", ret.getContextObj());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}
		return resModel;
	}

	@RequestMapping(value = "/saved", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel saved(RequestModel reqModel) {

		ResponseModel resModel = null;
		String funcName = "��ȡ����ļƻ���Ϣ����";
		try {
			List<Map<String, String>> list = planService.selectSavedPlanApp();

			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", list);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}
		return resModel;
	}

	@RequestMapping(value = "/basic/saved", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel basicSaved(RequestModel reqModel) {

		ResponseModel resModel = null;
		String funcName = "��ȡ����ļƻ�������Ϣ";
		try {
			String appNo = reqModel.getStringValue("appNo");
			PlanBasicInfoModel model = planService.selectPlanBasicApp(appNo);

			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", model);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}
		return resModel;
	}
	

	@RequestMapping(value = "/basic/submit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel basicSubmit(RequestModel reqModel) {

		CommonBusiness business = new CommonBusiness();
		ResponseModel resModel = null;
		String funcName = "�ύ�ƻ�������Ϣ";
		try{
			
		RetResult ret = business.register(AppAudTask.class, reqModel.getPrivateCtx()) // �ύ����
				.register(PlanBasicAppPreTask.class, reqModel.getPrivateCtx()) // ����app
				.register(PlanBasicTrdPreTask.class, reqModel.getPrivateCtx()) // �ύ����
				.doBusiness();
		
		resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", ret.getContextObj());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}
		return resModel;
	}
	
	@RequestMapping(value = "/basic/audit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel basicAudit(RequestModel reqModel) {

		CommonBusiness business = new CommonBusiness();
		ResponseModel resModel = null;
		String funcName = "���˼ƻ�������Ϣ";
		try{
			
		RetResult ret = business.register(AppAudTask.class, reqModel.getPrivateCtx()) // �ύ����
				.register(PlanBasicTrdDoTask.class, reqModel.getPrivateCtx()) // �ύ����
				.doBusiness();
		
		resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", ret.getContextObj());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}
		return resModel;
	}
}
