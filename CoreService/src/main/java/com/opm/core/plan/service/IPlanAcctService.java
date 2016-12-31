package com.opm.core.plan.service;

import java.util.List;

import com.opm.core.plan.model.PlanAcctModel;

public interface IPlanAcctService {
	/**
     * app
     * 保存账户基本信息
     */
    public Integer insertPlanAcctApp(PlanAcctModel model);
    

	/**
     * app
     * 查询账户基本信息
     */
    public List<PlanAcctModel> selectPlanAcctApp(String appNo, String planId, String feeType);
    

	/**
     * app
     * 更新账户基本信息
     */
    public Integer updatePlanAcctApp(PlanAcctModel model);

	/**
     * app
     * 删除账户基本信息
     */
    public Integer deletePlanAcctApp(PlanAcctModel model);
    
	/**
     * 交易
     * 保存账户基本信息
     */
    public Integer insertPlanAcctTrd(PlanAcctModel model);
        

	/**
     * 生效
     * 保存账户基本信息
     */
    public Integer insertPlanAcctInf(PlanAcctModel model);
    

	/**
     * 
     * 查询账户基本信息
     */
    public List<PlanAcctModel> selectPlanAcctInf(String planId);
    

	/**
     * 生效
     * 更新账户基本信息
     */
    public Integer updatePlanAcctInf(PlanAcctModel model);
}
