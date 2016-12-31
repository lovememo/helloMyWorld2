package com.opm.core.plan.service;

import java.util.List;

import com.opm.core.plan.model.PlanInvestModel;

public interface IPlanInvestService {
	/**
     * app
     * ����Ͷ�ʻ�����Ϣ
     */
    public Integer insertPlanInvestApp(PlanInvestModel model);
    

	/**
     * app
     * ��ѯͶ�ʻ�����Ϣ
     */
    public List<PlanInvestModel> selectPlanInvestApp(String appNo, String planId, String investId);
    

	/**
     * app
     * ����Ͷ�ʻ�����Ϣ
     */
    public Integer updatePlanInvestApp(PlanInvestModel model);

	/**
     * app
     * ɾ��Ͷ�ʻ�����Ϣ
     */
    public Integer deletePlanInvestApp(PlanInvestModel model);
    
	/**
     * ����
     * ����Ͷ�ʻ�����Ϣ
     */
    public Integer insertPlanInvestTrd(PlanInvestModel model);
        

	/**
     * ��Ч
     * ����Ͷ�ʻ�����Ϣ
     */
    public Integer insertPlanInvestInf(PlanInvestModel model);
    

	/**
     * 
     * ��ѯͶ�ʻ�����Ϣ
     */
    public List<PlanInvestModel> selectPlanInvestInf(String planId);
    

	/**
     * ��Ч
     * ����Ͷ�ʻ�����Ϣ
     */
    public Integer updatePlanInvestInf(PlanInvestModel model);
}
