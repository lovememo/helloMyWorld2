package com.opm.core.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.core.plan.model.PlanRoleModel;

@Mapper
public interface PlanRoleMapper {

	/**
     * app
     * �����ɫ�˻�����Ϣ
     */
    public Integer insertPlanRoleApp(PlanRoleModel model);
    

	/**
     * app
     * ��ѯ��ɫ�˻�����Ϣ
     */
    public List<PlanRoleModel> selectPlanRoleApp(@Param("appNo")String appNo, @Param("planId")String planId, @Param("struId")String struId);
    

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
