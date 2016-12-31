package com.opm.core.plan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.core.plan.dao.IPlanInvestDao;
import com.opm.core.plan.model.PlanInvestModel;
import com.opm.core.plan.service.IPlanInvestService;

@Service("PlanInvestService")
public class PlanInvestService implements IPlanInvestService {

	@Autowired
	private IPlanInvestDao dao;

	@Override
	public Integer insertPlanInvestApp(PlanInvestModel model) {
		return dao.insertPlanInvestApp(model);
	}

	@Override
	public List<PlanInvestModel> selectPlanInvestApp(String appNo, String planId, String struId) {
		return dao.selectPlanInvestApp(appNo, planId, struId);
	}

	@Override
	public Integer updatePlanInvestApp(PlanInvestModel model) {
		return dao.updatePlanInvestApp(model);
	}

	@Override
	public Integer deletePlanInvestApp(PlanInvestModel model) {
		return dao.deletePlanInvestApp(model);
	}

	@Override
	public Integer insertPlanInvestTrd(PlanInvestModel model) {
		return dao.insertPlanInvestTrd(model);
	}

	@Override
	public Integer insertPlanInvestInf(PlanInvestModel model) {
		return dao.insertPlanInvestInf(model);
	}

	@Override
	public List<PlanInvestModel> selectPlanInvestInf(String planId) {
		return dao.selectPlanInvestInf(planId);
	}

	@Override
	public Integer updatePlanInvestInf(PlanInvestModel model) {
		return dao.updatePlanInvestInf(model);
	}

}
