package com.opm.core.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.core.plan.model.PlanAcctModel;

@Mapper
public interface PlanAcctMapper {

	/**
     * app
     * �����˻�������Ϣ
     */
    public Integer insertPlanAcctApp(PlanAcctModel model);
    

	/**
     * app
     * ��ѯ�˻�������Ϣ
     */
    public List<PlanAcctModel> selectPlanAcctApp(@Param("appNo")String appNo, @Param("planId")String planId, @Param("feeType")String feeType);
    

	/**
     * app
     * �����˻�������Ϣ
     */
    public Integer updatePlanAcctApp(PlanAcctModel model);

	/**
     * app
     * ɾ���˻�������Ϣ
     */
    public Integer deletePlanAcctApp(PlanAcctModel model);
    
	/**
     * ����
     * �����˻�������Ϣ
     */
    public Integer insertPlanAcctTrd(PlanAcctModel model);
        

	/**
     * ��Ч
     * �����˻�������Ϣ
     */
    public Integer insertPlanAcctInf(PlanAcctModel model);
    

	/**
     * 
     * ��ѯ�˻�������Ϣ
     */
    public List<PlanAcctModel> selectPlanAcctInf(String planId);
    

	/**
     * ��Ч
     * �����˻�������Ϣ
     */
    public Integer updatePlanAcctInf(PlanAcctModel model);
}
