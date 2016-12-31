package com.opm.core.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.core.plan.model.PlanRoleModel;

@Mapper
public interface PlanRoleMapper {

	/**
     * app
     * 保存角色人基本信息
     */
    public Integer insertPlanRoleApp(PlanRoleModel model);
    

	/**
     * app
     * 查询角色人基本信息
     */
    public List<PlanRoleModel> selectPlanRoleApp(@Param("appNo")String appNo, @Param("planId")String planId, @Param("struId")String struId);
    

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
