package com.opm.core.plan.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.plan.dao.IPlanDao;
import com.opm.core.plan.mapper.PlanMapper;
import com.opm.core.plan.model.PlanAppInfModel;
import com.opm.core.plan.model.PlanBasicInfoModel;
import com.opm.core.workflow.model.ApplicationModle;

@Repository("PlanDao")
public class PlanDao implements IPlanDao {
	@Autowired
	private PlanMapper planMapper;

	@Override
	public PlanAppInfModel getPlanAppDet(long appNo) {
		return this.planMapper.getPlanAppDet(appNo);
	}
	
	@Override
	public List<ApplicationModle> getPlanApplys(String branchId, String userId, String roleId) {
		return this.planMapper.getPlanApplys(branchId, userId, roleId);
	}
	

	/**
     * 接口
     * 传入当前计划编码，返回关联计划列表，含计划编码，是否主受托人。
     * @param planId 计划编码
     * @return 计划信息
     */
    public PlanBasicInfoModel getPlanInfByPlanId(String planId){
    	return this.planMapper.getPlanInfByPlanId(planId);
    }
    

	/**
     * app
     * 保存计划基本信息
     */
    public Integer insertPlanBasicApp(PlanBasicInfoModel model){
    	return this.planMapper.insertPlanBasicApp(model);
    }
    

	/**
     * app
     * 查询计划基本信息
     */
    public PlanBasicInfoModel selectPlanBasicApp(String appNo){
    	return this.planMapper.selectPlanBasicApp(appNo);
    }
    

	/**
     * app
     * 更新计划基本信息
     */
    public Integer updatePlanBasicApp(PlanBasicInfoModel model){
    	return this.planMapper.updatePlanBasicApp(model);
    }
    

	/**
     * app
     * 获取保存的计划基本信息
     */
    public List<Map<String,String>> selectSavedPlanApp(){
    	return this.planMapper.selectSavedPlanApp();
    }
    
    /**
     * 交易
     * 保存计划基本信息
     */
    public Integer insertPlanBasicTrd(PlanBasicInfoModel model){
    	return this.planMapper.insertPlanBasicTrd(model);
    }
    

	/**
     * 交易
     * 查询计划基本信息
     */
    public PlanBasicInfoModel selectPlanBasicTrd(String trdNo){
    	return this.planMapper.selectPlanBasicTrd(trdNo);
    }
    

	/**
     * 交易
     * 更新计划基本信息
     */
    public Integer updatePlanBasicTrd(PlanBasicInfoModel model){
    	return this.planMapper.updatePlanBasicTrd(model);
    }
    
    /**
     * 交易
     * 获取保存的计划基本信息
     */
    public List<Map<String,String>> selectSavedPlanTrd(){
    	return this.planMapper.selectSavedPlanTrd();
    }
    

	/**
     * 生效
     * 保存计划基本信息
     */
    public Integer insertPlanBasicInf(PlanBasicInfoModel model){
    	return this.planMapper.insertPlanBasicInf(model);
    }
    

	/**
     * 
     * 查询计划基本信息
     */
    public PlanBasicInfoModel selectPlanBasicInf(String planId){
    	return this.planMapper.selectPlanBasicInf(planId);
    }
    

	/**
     * 生效
     * 更新计划基本信息
     */
    public Integer updatePlanBasicInf(PlanBasicInfoModel model){
    	return this.planMapper.updatePlanBasicInf(model);
    }
}
