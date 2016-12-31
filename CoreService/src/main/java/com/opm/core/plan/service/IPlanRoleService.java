package com.opm.core.plan.service;

import java.util.List;

import com.opm.core.plan.model.PlanRoleModel;

public interface IPlanRoleService {
	/**
     * app
     * 保存角色人基本信息
     */
    public Integer insertPlanRoleApp(PlanRoleModel model);
    

	/**
     * app
     * 查询角色人基本信息
     */
    public List<PlanRoleModel> selectPlanRoleApp(String appNo, String planId, String struId);
    

	/**
     * app
     * 更新角色人基本信息
     */
    public Integer updatePlanRoleApp(PlanRoleModel model);

	/**
     * app
     * 删除角色人基本信息
     */
    public Integer deletePlanRoleApp(PlanRoleModel model);
    
	/**
     * 交易
     * 保存角色人基本信息
     */
    public Integer insertPlanRoleTrd(PlanRoleModel model);
        

	/**
     * 生效
     * 保存角色人基本信息
     */
    public Integer insertPlanRoleInf(PlanRoleModel model);
    

	/**
     * 
     * 查询角色人基本信息
     */
    public List<PlanRoleModel> selectPlanRoleInf(String planId);
    

	/**
     * 生效
     * 更新角色人基本信息
     */
    public Integer updatePlanRoleInf(PlanRoleModel model);
}
