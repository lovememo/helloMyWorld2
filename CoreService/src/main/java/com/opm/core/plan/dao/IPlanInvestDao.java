package com.opm.core.plan.dao;

import java.util.List;

import com.opm.core.plan.model.PlanInvestModel;

public interface IPlanInvestDao {

	/**
     * app
     * 保存投资基本信息
     */
    public Integer insertPlanInvestApp(PlanInvestModel model);
    

	/**
     * app
     * 查询投资基本信息
     */
    public List<PlanInvestModel> selectPlanInvestApp(String appNo, String planId, String struId);
    

	/**
     * app
     * 更新投资基本信息
     */
    public Integer updatePlanInvestApp(PlanInvestModel model);

	/**
     * app
     * 删除投资基本信息
     */
    public Integer deletePlanInvestApp(PlanInvestModel model);
    
	/**
     * 交易
     * 保存投资基本信息
     */
    public Integer insertPlanInvestTrd(PlanInvestModel model);
        

	/**
     * 生效
     * 保存投资基本信息
     */
    public Integer insertPlanInvestInf(PlanInvestModel model);
    

	/**
     * 
     * 查询投资基本信息
     */
    public List<PlanInvestModel> selectPlanInvestInf(String planId);
    

	/**
     * 生效
     * 更新投资基本信息
     */
    public Integer updatePlanInvestInf(PlanInvestModel model);
}
