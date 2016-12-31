package com.opm.core.plan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.core.plan.dao.IPlanOtherInvestDao;
import com.opm.core.plan.model.OtherInvestInfModel;
import com.opm.core.plan.service.IPlanOtherInvestService;

@Service("PlanOtherInvestService")
public class PlanOtherInvestService implements IPlanOtherInvestService {

	@Autowired
	private IPlanOtherInvestDao dao;

	@Override
	public Integer insertPlanOtherInvestApp(OtherInvestInfModel model) {
		return dao.insertPlanOtherInvestApp(model);
	}

	@Override
	public List<OtherInvestInfModel> selectPlanOtherInvestApp(String appNo, String commonPlanId) {
		return dao.selectPlanOtherInvestApp(appNo, commonPlanId);
	}

	@Override
	public Integer deletePlanOtherInvestApp(OtherInvestInfModel model) {
		return dao.deletePlanOtherInvestApp(model);
	}

	@Override
	public Integer insertPlanOtherInvestTrd(OtherInvestInfModel model) {
		return dao.insertPlanOtherInvestTrd(model);
	}

	@Override
	public Integer insertPlanOtherInvestInf(OtherInvestInfModel model) {
		return dao.insertPlanOtherInvestInf(model);
	}

	@Override
	public List<OtherInvestInfModel> selectPlanOtherInvestInf(String planId) {
		return dao.selectPlanOtherInvestInf(planId);
	}

	@Override
	public Integer deletePlanOtherInvestInf(OtherInvestInfModel model) {
		return dao.deletePlanOtherInvestInf(model);
	}

}
