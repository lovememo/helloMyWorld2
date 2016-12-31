package com.opm.core.plan.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.plan.dao.IPlanOtherDao;
import com.opm.core.plan.mapper.PlanOtherMapper;
import com.opm.core.plan.model.OtherPlanInfModel;

@Repository("PlanOtherDao")
public class PlanOtherDao implements IPlanOtherDao {
	
	@Autowired
	private PlanOtherMapper mapper;

	@Override
	public Integer insertPlanOtherApp(OtherPlanInfModel model) {
		return mapper.insertPlanOtherApp(model);
	}

	@Override
	public List<OtherPlanInfModel> selectPlanOtherApp(String appNo, String planId, String feeType) {
		return mapper.selectPlanOtherApp(appNo, planId, feeType);
	}

	@Override
	public Integer updatePlanOtherApp(OtherPlanInfModel model) {
		return mapper.updatePlanOtherApp(model);
	}

	@Override
	public Integer deletePlanOtherApp(OtherPlanInfModel model) {
		return mapper.deletePlanOtherApp(model);
	}

	@Override
	public Integer insertPlanOtherTrd(OtherPlanInfModel model) {
		return mapper.insertPlanOtherTrd(model);
	}

	@Override
	public Integer insertPlanOtherInf(OtherPlanInfModel model) {
		return mapper.insertPlanOtherInf(model);
	}

	@Override
	public List<OtherPlanInfModel> selectPlanOtherInf(String planId) {
		return mapper.selectPlanOtherInf(planId);
	}

	@Override
	public Integer updatePlanOtherInf(OtherPlanInfModel model) {
		return mapper.updatePlanOtherInf(model);
	}

}
