package com.opm.core.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.core.plan.model.OtherPlanInfModel;

@Mapper
public interface PlanOtherMapper {

	/**
     * app
     * ���������ƻ�������Ϣ
     */
    public Integer insertPlanOtherApp(OtherPlanInfModel model);
    

	/**
     * app
     * ��ѯ�����ƻ�������Ϣ
     */
    public List<OtherPlanInfModel> selectPlanOtherApp(@Param("appNo")String appNo, @Param("planName")String planName, @Param("commonPlanId")String commonPlanId);
    

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
