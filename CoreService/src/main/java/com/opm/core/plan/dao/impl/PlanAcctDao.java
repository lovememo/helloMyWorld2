package com.opm.core.plan.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.plan.dao.IPlanAcctDao;
import com.opm.core.plan.mapper.PlanAcctMapper;
import com.opm.core.plan.model.PlanAcctModel;

@Repository("PlanAcctDao")
public class PlanAcctDao implements IPlanAcctDao {
	
	@Autowired
	private PlanAcctMapper mapper;

	@Override
	public Integer insertPlanAcctApp(PlanAcctModel model) {
		return mapper.insertPlanAcctApp(model);
	}

	@Override
	public List<PlanAcctModel> selectPlanAcctApp(String appNo, String planId, String feeType) {
		return mapper.selectPlanAcctApp(appNo, planId, feeType);
	}

	@Override
	public Integer updatePlanAcctApp(PlanAcctModel model) {
		return mapper.updatePlanAcctApp(model);
	}

	@Override
	public Integer deletePlanAcctApp(PlanAcctModel model) {
		return mapper.deletePlanAcctApp(model);
	}

	@Override
	public Integer insertPlanAcctTrd(PlanAcctModel model) {
		return mapper.insertPlanAcctTrd(model);
	}

	@Override
	public Integer insertPlanAcctInf(PlanAcctModel model) {
		return mapper.insertPlanAcctInf(model);
	}

	@Override
	public List<PlanAcctModel> selectPlanAcctInf(String planId) {
		return mapper.selectPlanAcctInf(planId);
	}

	@Override
	public Integer updatePlanAcctInf(PlanAcctModel model) {
		return mapper.updatePlanAcctInf(model);
	}

}
