package com.opm.core.plan.trade;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.core.plan.model.PlanInvestModel;
import com.opm.core.plan.service.IPlanInvestService;

@Service("PlanInvestAppPreTask")
public class PlanInvestAppPreTask implements ITask {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanInvestAppPreTask.class);

	@Autowired
	private IPlanInvestService service;

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
		String type = (String) paramMap.get("type");

		PlanInvestModel model = new PlanInvestModel();
		model.setAppNo((String) paramMap.get("appNo"));
		model.setPlanId((String) paramMap.get("planId"));
		model.setInvestName((String)paramMap.get("investName"));
		model.setTrusteeId((String)paramMap.get("trusteeId"));
		model.setTrusteeName((String)paramMap.get("trusteeName"));
		model.setInvestorInvestId((String)paramMap.get("investorInvestId"));
		model.setOfferRate((String)paramMap.get("offerRate"));
		model.setPayRate((String)paramMap.get("payRate"));

		if ("add".equals(type)) {

			// 保存app
			this.service.insertPlanInvestApp(model);
		} else if ("update".equals(type)) {

			// 更新app
			this.service.updatePlanInvestApp(model);
		} else if ("delete".equals(type)) {

			// 删除app
			this.service.deletePlanInvestApp(model);
		}

		RetResult rr = new RetResult();

		rr.setResult(true);
		rr.setReturnObj(service.selectPlanInvestApp((String) paramMap.get("appNo"), null, null));//获取列表
		return rr;
	}

	@Override
	public RetResult doCompensate(TaskParam compensateParam) {
		RetResult rr = new RetResult();
		rr.setResult(false);
		return rr;
	}

}
