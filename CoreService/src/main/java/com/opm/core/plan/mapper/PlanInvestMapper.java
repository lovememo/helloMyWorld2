package com.opm.core.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.core.plan.model.PlanInvestModel;

@Mapper
public interface PlanInvestMapper {

	/**
     * app
     * 保存投资基本信息
     */
    public Integer insertPlanInvestApp(PlanInvestModel model);
    

	/**
     * app
     * 查询投资基本信息
     */
    public List<PlanInvestModel> selectPlanInvestApp(@Param("appNo")String appNo, @Param("planId")String planId, @Param("investId")String investId);
    

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
