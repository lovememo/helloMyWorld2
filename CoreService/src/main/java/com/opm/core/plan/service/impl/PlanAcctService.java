package com.opm.core.plan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.core.plan.dao.IPlanAcctDao;
import com.opm.core.plan.model.PlanAcctModel;
import com.opm.core.plan.service.IPlanAcctService;

@Service("PlanAcctService")
public class PlanAcctService implements IPlanAcctService {

	@Autowired
	private IPlanAcctDao dao;

	@Override
	public Integer insertPlanAcctApp(PlanAcctModel model) {
		return dao.insertPlanAcctApp(model);
	}

	@Override
	public List<PlanAcctModel> selectPlanAcctApp(String appNo, String planId, String feeType) {
		return dao.selectPlanAcctApp(appNo, planId, feeType);
	}

	@Override
	public Integer updatePlanAcctApp(PlanAcctModel model) {
		return dao.updatePlanAcctApp(model);
	}

	@Override
	public Integer deletePlanAcctApp(PlanAcctModel model) {
		return dao.deletePlanAcctApp(model);
	}

	@Override
	public Integer insertPlanAcctTrd(PlanAcctModel model) {
		return dao.insertPlanAcctTrd(model);
	}

	@Override
	public Integer insertPlanAcctInf(PlanAcctModel model) {
		return dao.insertPlanAcctInf(model);
	}

	@Override
	public List<PlanAcctModel> selectPlanAcctInf(String planId) {
		return dao.selectPlanAcctInf(planId);
	}

	@Override
	public Integer updatePlanAcctInf(PlanAcctModel model) {
		return dao.updatePlanAcctInf(model);
	}

}
