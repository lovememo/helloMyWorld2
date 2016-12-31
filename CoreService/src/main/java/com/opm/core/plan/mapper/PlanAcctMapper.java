package com.opm.core.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.core.plan.model.PlanAcctModel;

@Mapper
public interface PlanAcctMapper {

	/**
     * app
     * 保存账户基本信息
     */
    public Integer insertPlanAcctApp(PlanAcctModel model);
    

	/**
     * app
     * 查询账户基本信息
     */
    public List<PlanAcctModel> selectPlanAcctApp(@Param("appNo")String appNo, @Param("planId")String planId, @Param("feeType")String feeType);
    

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
