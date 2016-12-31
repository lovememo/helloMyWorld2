package com.opm.core.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.core.plan.model.OtherInvestInfModel;

@Mapper
public interface PlanOtherInvestMapper {

	/**
     * app
     * 保存其他计划投资基本信息
     */
    public Integer insertPlanOtherInvestApp(OtherInvestInfModel model);
    

	/**
     * app
     * 查询其他计划投资基本信息
     */
    public List<OtherInvestInfModel> selectPlanOtherInvestApp(@Param("appNo")String appNo, @Param("commonPlanId")String commonPlanId);
    
	/**
     * app
     * 删除其他计划投资基本信息
     */
    public Integer deletePlanOtherInvestApp(OtherInvestInfModel model);
    
	/**
     * 交易
     * 保存其他计划投资基本信息
     */
    public Integer insertPlanOtherInvestTrd(OtherInvestInfModel model);
        

	/**
     * 生效
     * 保存其他计划投资基本信息
     */
    public Integer insertPlanOtherInvestInf(OtherInvestInfModel model);
    

	/**
     * 
     * 查询其他计划投资基本信息
     */
    public List<OtherInvestInfModel> selectPlanOtherInvestInf(String planId);
    

	/**
     * 生效
     * 更新其他计划投资基本信息
     */
    public Integer deletePlanOtherInvestInf(OtherInvestInfModel model);
}
