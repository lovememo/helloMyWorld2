package com.opm.core.plan.trade;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.core.plan.model.PlanBasicInfoModel;
import com.opm.core.plan.service.IPlanService;

@Service("PlanBasicAppPreTask")
public class PlanBasicAppPreTask implements ITask {

	private static final String TYPE = "PLANBASIC";

	private static final String STATE = "PREPARED";

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanBasicAppPreTask.class);

	@Autowired
	private IPlanService planService;

	@Override
	public RetResult check(TaskParam checkParam) {
		RetResult rr = new RetResult();
		rr.setResult(true);
		return rr;
	}

	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	@Transactional
	public RetResult doTask(TaskParam taskParam) {
		Map<String, Object> paramMap = (Map<String, Object>) taskParam.getSelfParam();
		String appNo = (String) taskParam.getContextParam().get("appNo");

		// 查询保存的app
		PlanBasicInfoModel app = planService.selectPlanBasicApp(appNo);

		PlanBasicInfoModel model = new PlanBasicInfoModel();
		model.setAppNo(appNo);
		// model.setPlanId((String)paramMap.get("planId"));
		model.setProdId((String) paramMap.get("prodId"));
		model.setProdName((String) paramMap.get("prodName"));
		model.setPlanName((String) paramMap.get("planName"));
		model.setPlanLocation((String) paramMap.get("planLocation"));
		model.setPlanNum((String) paramMap.get("planNum"));
		model.setPlanEvalType((String) paramMap.get("planEvalType"));
		model.setPlanType((String) paramMap.get("planType"));
		model.setMandataryPlanId((String) paramMap.get("mandataryPlanId"));
		model.setAcctBeginDate((String) paramMap.get("acctBeginDate"));
		model.setConfirmFlag((String) paramMap.get("confirmFlag"));
		model.setCommonPlanId((String) paramMap.get("commonPlanId"));
		model.setSocSecDay((String) paramMap.get("socSecDay"));
		model.setSocSecStru((String) paramMap.get("socSecStru"));
		model.setTrustconno((String) paramMap.get("trustconno"));
		model.setSubmitCtrl((String) paramMap.get("submitCtrl"));
		model.setActRate((String) paramMap.get("actRate"));
		model.setActDate((String) paramMap.get("actDate"));
		// model.setUnifPlanName((String)paramMap.get("unifPlanName"));
		// model.setUnifPlanId((String)paramMap.get("unifPlanId"));
		// model.setUnifMandataryId((String)paramMap.get("unifMandataryId"));
		// model.setUnifTrusteeId((String)paramMap.get("unifTrusteeId"));
		// model.setIcbcFlag((String)paramMap.get("icbcFlag"));
		// model.setPlanTime((String)paramMap.get("planTime"));
		// model.setInvestPreacctFlag((String)paramMap.get("investPreacctFlag"));
		// model.setInvestDefer((String)paramMap.get("investDefer"));
		model.setBranchId((String) paramMap.get("branchId"));
		if (app == null) {
			// 保存app

			this.planService.insertPlanBasicApp(model);
		} else {

			// 更新app
			this.planService.updatePlanBasicApp(model);
		}

		RetResult rr = new RetResult();
		Map<String, Object> contextObj = new HashMap<>();
		contextObj.put("appNo", appNo);

		rr.setContextObj(contextObj);
		rr.setReturnObj(contextObj);

		rr.setResult(true);
		return rr;
	}

	@Override
	public RetResult doCompensate(TaskParam compensateParam) {
		RetResult rr = new RetResult();
		rr.setResult(false);
		return rr;
	}

}
