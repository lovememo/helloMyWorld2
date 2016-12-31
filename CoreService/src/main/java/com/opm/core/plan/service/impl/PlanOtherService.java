package com.opm.core.plan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.core.plan.dao.IPlanOtherDao;
import com.opm.core.plan.model.OtherPlanInfModel;
import com.opm.core.plan.service.IPlanOtherService;

@Service("PlanOtherService")
public class PlanOtherService implements IPlanOtherService {

	@Autowired
	private IPlanOtherDao dao;

	@Override
	public Integer insertPlanOtherApp(OtherPlanInfModel model) {
		return dao.insertPlanOtherApp(model);
	}

	@Override
	public List<OtherPlanInfModel> selectPlanOtherApp(String appNo, String planId, String feeType) {
		return dao.selectPlanOtherApp(appNo, planId, feeType);
	}

	@Override
	public Integer updatePlanOtherApp(OtherPlanInfModel model) {
		return dao.updatePlanOtherApp(model);
	}

	@Override
	public Integer deletePlanOtherApp(OtherPlanInfModel model) {
		return dao.deletePlanOtherApp(model);
	}

	@Override
	public Integer insertPlanOtherTrd(OtherPlanInfModel model) {
		return dao.insertPlanOtherTrd(model);
	}

	@Override
	public Integer insertPlanOtherInf(OtherPlanInfModel model) {
		return dao.insertPlanOtherInf(model);
	}

	@Override
	public List<OtherPlanInfModel> selectPlanOtherInf(String planId) {
		return dao.selectPlanOtherInf(planId);
	}

	@Override
	public Integer updatePlanOtherInf(OtherPlanInfModel model) {
		return dao.updatePlanOtherInf(model);
	}

}
