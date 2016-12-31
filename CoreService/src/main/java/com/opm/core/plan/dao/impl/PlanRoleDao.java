package com.opm.core.plan.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.plan.dao.IPlanRoleDao;
import com.opm.core.plan.mapper.PlanRoleMapper;
import com.opm.core.plan.model.PlanRoleModel;

@Repository("PlanRoleDao")
public class PlanRoleDao implements IPlanRoleDao {
	
	@Autowired
	private PlanRoleMapper mapper;

	@Override
	public Integer insertPlanRoleApp(PlanRoleModel model) {
		return mapper.insertPlanRoleApp(model);
	}

	@Override
	public List<PlanRoleModel> selectPlanRoleApp(String appNo, String planId, String struId) {
		return mapper.selectPlanRoleApp(appNo, planId, struId);
	}

	@Override
	public Integer updatePlanRoleApp(PlanRoleModel model) {
		return mapper.updatePlanRoleApp(model);
	}

	@Override
	public Integer deletePlanRoleApp(PlanRoleModel model) {
		return mapper.deletePlanRoleApp(model);
	}

	@Override
	public Integer insertPlanRoleTrd(PlanRoleModel model) {
		return mapper.insertPlanRoleTrd(model);
	}

	@Override
	public Integer insertPlanRoleInf(PlanRoleModel model) {
		return mapper.insertPlanRoleInf(model);
	}

	@Override
	public List<PlanRoleModel> selectPlanRoleInf(String planId) {
		return mapper.selectPlanRoleInf(planId);
	}

	@Override
	public Integer updatePlanRoleInf(PlanRoleModel model) {
		return mapper.updatePlanRoleInf(model);
	}

}
