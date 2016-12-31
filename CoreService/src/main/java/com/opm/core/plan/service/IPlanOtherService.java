package com.opm.core.plan.service;

import java.util.List;

import com.opm.core.plan.model.OtherPlanInfModel;

public interface IPlanOtherService {

	/**
     * app
     * 保存其他计划基本信息
     */
    public Integer insertPlanOtherApp(OtherPlanInfModel model);
    

	/**
     * app
     * 查询其他计划基本信息
     */
    public List<OtherPlanInfModel> selectPlanOtherApp(String appNo, String planName, String commonPlanId);
    

	/**
     * app
     * 更新其他计划基本信息
     */
    public Integer updatePlanOtherApp(OtherPlanInfModel model);

	/**
     * app
     * 删除其他计划基本信息
     */
    public Integer deletePlanOtherApp(OtherPlanInfModel model);
    
	/**
     * 交易
     * 保存其他计划基本信息
     */
    public Integer insertPlanOtherTrd(OtherPlanInfModel model);
        

	/**
     * 生效
     * 保存其他计划基本信息
     */
    public Integer insertPlanOtherInf(OtherPlanInfModel model);
    

	/**
     * 
     * 查询其他计划基本信息
     */
    public List<OtherPlanInfModel> selectPlanOtherInf(String planId);
    

	/**
     * 生效
     * 更新其他计划基本信息
     */
    public Integer updatePlanOtherInf(OtherPlanInfModel model);
}
