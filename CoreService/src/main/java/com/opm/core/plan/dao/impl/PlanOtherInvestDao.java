package com.opm.core.plan.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.plan.dao.IPlanOtherInvestDao;
import com.opm.core.plan.mapper.PlanOtherInvestMapper;
import com.opm.core.plan.model.OtherInvestInfModel;

@Repository("PlanOtherInvestDao")
public class PlanOtherInvestDao implements IPlanOtherInvestDao {
	
	@Autowired
	private PlanOtherInvestMapper mapper;

	@Override
	public Integer insertPlanOtherInvestApp(OtherInvestInfModel model) {
		return mapper.insertPlanOtherInvestApp(model);
	}

	@Override
	public List<OtherInvestInfModel> selectPlanOtherInvestApp(String appNo, String commonPlanId) {
		return mapper.selectPlanOtherInvestApp(appNo, commonPlanId);
	}

	@Override
	public Integer deletePlanOtherInvestApp(OtherInvestInfModel model) {
		return mapper.deletePlanOtherInvestApp(model);
	}

	@Override
	public Integer insertPlanOtherInvestTrd(OtherInvestInfModel model) {
		return mapper.insertPlanOtherInvestTrd(model);
	}

	@Override
	public Integer insertPlanOtherInvestInf(OtherInvestInfModel model) {
		return mapper.insertPlanOtherInvestInf(model);
	}

	@Override
	public List<OtherInvestInfModel> selectPlanOtherInvestInf(String planId) {
		return mapper.selectPlanOtherInvestInf(planId);
	}

	@Override
	public Integer deletePlanOtherInvestInf(OtherInvestInfModel model) {
		return mapper.deletePlanOtherInvestInf(model);
	}


}
