package com.opm.core.plan.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.plan.dao.IPlanInvestDao;
import com.opm.core.plan.mapper.PlanInvestMapper;
import com.opm.core.plan.model.PlanInvestModel;

@Repository("PlanInvestDao")
public class PlanInvestDao implements IPlanInvestDao {
	
	@Autowired
	private PlanInvestMapper mapper;

	@Override
	public Integer insertPlanInvestApp(PlanInvestModel model) {
		return mapper.insertPlanInvestApp(model);
	}

	@Override
	public List<PlanInvestModel> selectPlanInvestApp(String appNo, String planId, String investId) {
		return mapper.selectPlanInvestApp(appNo, planId, investId);
	}

	@Override
	public Integer updatePlanInvestApp(PlanInvestModel model) {
		return mapper.updatePlanInvestApp(model);
	}

	@Override
	public Integer deletePlanInvestApp(PlanInvestModel model) {
		return mapper.deletePlanInvestApp(model);
	}

	@Override
	public Integer insertPlanInvestTrd(PlanInvestModel model) {
		return mapper.insertPlanInvestTrd(model);
	}

	@Override
	public Integer insertPlanInvestInf(PlanInvestModel model) {
		return mapper.insertPlanInvestInf(model);
	}

	@Override
	public List<PlanInvestModel> selectPlanInvestInf(String planId) {
		return mapper.selectPlanInvestInf(planId);
	}

	@Override
	public Integer updatePlanInvestInf(PlanInvestModel model) {
		return mapper.updatePlanInvestInf(model);
	}

}
