package com.opm.core.plan.service;

import java.util.List;

import com.opm.core.plan.model.OtherPlanInfModel;

public interface IPlanOtherService {

	/**
     * app
     * ���������ƻ�������Ϣ
     */
    public Integer insertPlanOtherApp(OtherPlanInfModel model);
    

	/**
     * app
     * ��ѯ�����ƻ�������Ϣ
     */
    public List<OtherPlanInfModel> selectPlanOtherApp(String appNo, String planName, String commonPlanId);
    

	/**
     * app
     * ���������ƻ�������Ϣ
     */
    public Integer updatePlanOtherApp(OtherPlanInfModel model);

	/**
     * app
     * ɾ�������ƻ�������Ϣ
     */
    public Integer deletePlanOtherApp(OtherPlanInfModel model);
    
	/**
     * ����
     * ���������ƻ�������Ϣ
     */
    public Integer insertPlanOtherTrd(OtherPlanInfModel model);
        

	/**
     * ��Ч
     * ���������ƻ�������Ϣ
     */
    public Integer insertPlanOtherInf(OtherPlanInfModel model);
    

	/**
     * 
     * ��ѯ�����ƻ�������Ϣ
     */
    public List<OtherPlanInfModel> selectPlanOtherInf(String planId);
    

	/**
     * ��Ч
     * ���������ƻ�������Ϣ
     */
    public Integer updatePlanOtherInf(OtherPlanInfModel model);
}
