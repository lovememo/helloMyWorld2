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
import com.opm.core.plan.model.PlanAcctModel;
import com.opm.core.plan.service.IPlanAcctService;

@Service("PlanAcctAppPreTask")
public class PlanAcctAppPreTask implements ITask {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanAcctAppPreTask.class);

	@Autowired
	private IPlanAcctService service;

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

		PlanAcctModel model = new PlanAcctModel();
		model.setAppNo((String) paramMap.get("appNo"));
		model.setPlanId((String) paramMap.get("planId"));
		model.setFeeType((String)paramMap.get("feeType"));
		model.setInvestId((String)paramMap.get("investId"));
		model.setAcctOpenDate((String)paramMap.get("acctOpenDate"));
		model.setAcctName((String)paramMap.get("acctName"));
		model.setBankName((String)paramMap.get("bankName"));
		model.setBankNo((String)paramMap.get("bankNo"));
		model.setAcctNo((String)paramMap.get("acctNo"));
		model.setOpenBranch((String)paramMap.get("openBranch"));
		model.setAcctProv((String)paramMap.get("acctProv"));
		model.setAcctCity((String)paramMap.get("acctCity"));
		model.setUnionBranchId((String)paramMap.get("unionBranchId"));

		if ("add".equals(type)) {

			// 保存app
			this.service.insertPlanAcctApp(model);
		} else if ("update".equals(type)) {

			// 更新app
			this.service.updatePlanAcctApp(model);
		} else if ("delete".equals(type)) {

			// 删除app
			this.service.deletePlanAcctApp(model);
		}

		RetResult rr = new RetResult();

		rr.setResult(true);
		rr.setReturnObj(service.selectPlanAcctApp((String) paramMap.get("appNo"), null, null));//获取列表
		return rr;
	}

	@Override
	public RetResult doCompensate(TaskParam compensateParam) {
		RetResult rr = new RetResult();
		rr.setResult(false);
		return rr;
	}

}
