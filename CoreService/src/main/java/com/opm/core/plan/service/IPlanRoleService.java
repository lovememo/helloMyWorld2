package com.opm.core.plan.service;

import java.util.List;

import com.opm.core.plan.model.PlanRoleModel;

public interface IPlanRoleService {
	/**
     * app
     * �����ɫ�˻�����Ϣ
     */
    public Integer insertPlanRoleApp(PlanRoleModel model);
    

	/**
     * app
     * ��ѯ��ɫ�˻�����Ϣ
     */
    public List<PlanRoleModel> selectPlanRoleApp(String appNo, String planId, String struId);
    

	/**
     * app
     * ���½�ɫ�˻�����Ϣ
     */
    public Integer updatePlanRoleApp(PlanRoleModel model);

	/**
     * app
     * ɾ����ɫ�˻�����Ϣ
     */
    public Integer deletePlanRoleApp(PlanRoleModel model);
    
	/**
     * ����
     * �����ɫ�˻�����Ϣ
     */
    public Integer insertPlanRoleTrd(PlanRoleModel model);
        

	/**
     * ��Ч
     * �����ɫ�˻�����Ϣ
     */
    public Integer insertPlanRoleInf(PlanRoleModel model);
    

	/**
     * 
     * ��ѯ��ɫ�˻�����Ϣ
     */
    public List<PlanRoleModel> selectPlanRoleInf(String planId);
    

	/**
     * ��Ч
     * ���½�ɫ�˻�����Ϣ
     */
    public Integer updatePlanRoleInf(PlanRoleModel model);
}
