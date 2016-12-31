package com.opm.core.plan.service;

import java.util.List;

import com.opm.core.plan.model.PlanAcctModel;

public interface IPlanAcctService {
	/**
     * app
     * �����˻�������Ϣ
     */
    public Integer insertPlanAcctApp(PlanAcctModel model);
    

	/**
     * app
     * ��ѯ�˻�������Ϣ
     */
    public List<PlanAcctModel> selectPlanAcctApp(String appNo, String planId, String feeType);
    

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
