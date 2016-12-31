package com.opm.core.plan.service;

import java.util.List;

import com.opm.core.plan.model.OtherInvestInfModel;

public interface IPlanOtherInvestService {

	/**
     * app
     * ���������ƻ�Ͷ�ʻ�����Ϣ
     */
    public Integer insertPlanOtherInvestApp(OtherInvestInfModel model);
    

	/**
     * app
     * ��ѯ�����ƻ�Ͷ�ʻ�����Ϣ
     */
    public List<OtherInvestInfModel> selectPlanOtherInvestApp(String appNo, String commonPlanId);
    
	/**
     * app
     * ɾ�������ƻ�Ͷ�ʻ�����Ϣ
     */
    public Integer deletePlanOtherInvestApp(OtherInvestInfModel model);
    
	/**
     * ����
     * ���������ƻ�Ͷ�ʻ�����Ϣ
     */
    public Integer insertPlanOtherInvestTrd(OtherInvestInfModel model);
        

	/**
     * ��Ч
     * ���������ƻ�Ͷ�ʻ�����Ϣ
     */
    public Integer insertPlanOtherInvestInf(OtherInvestInfModel model);
    

	/**
     * 
     * ��ѯ�����ƻ�Ͷ�ʻ�����Ϣ
     */
    public List<OtherInvestInfModel> selectPlanOtherInvestInf(String planId);
    

	/**
     * ��Ч
     * ���������ƻ�Ͷ�ʻ�����Ϣ
     */
    public Integer deletePlanOtherInvestInf(OtherInvestInfModel model);
}
