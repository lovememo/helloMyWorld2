package com.opm.core.plan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.core.plan.dao.IPlanRoleDao;
import com.opm.core.plan.model.PlanRoleModel;
import com.opm.core.plan.service.IPlanRoleService;

@Service("PlanRoleService")
public class PlanRoleService implements IPlanRoleService {

	@Autowired
	private IPlanRoleDao dao;

	@Override
	public Integer insertPlanRoleApp(PlanRoleModel model) {
		return dao.insertPlanRoleApp(model);
	}

	@Override
	public List<PlanRoleModel> selectPlanRoleApp(String appNo, String planId, String struId) {
		return dao.selectPlanRoleApp(appNo, planId, struId);
	}

	@Override
	public Integer updatePlanRoleApp(PlanRoleModel model) {
		return dao.updatePlanRoleApp(model);
	}

	@Override
	public Integer deletePlanRoleApp(PlanRoleModel model) {
		return dao.deletePlanRoleApp(model);
	}

	@Override
	public Integer insertPlanRoleTrd(PlanRoleModel model) {
		return dao.insertPlanRoleTrd(model);
	}

	@Override
	public Integer insertPlanRoleInf(PlanRoleModel model) {
		return dao.insertPlanRoleInf(model);
	}

	@Override
	public List<PlanRoleModel> selectPlanRoleInf(String planId) {
		return dao.selectPlanRoleInf(planId);
	}

	@Override
	public Integer updatePlanRoleInf(PlanRoleModel model) {
		return dao.updatePlanRoleInf(model);
	}

}
