package com.opm.core.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.core.plan.model.OtherInvestInfModel;

@Mapper
public interface PlanOtherInvestMapper {

	/**
     * app
     * ���������ƻ�Ͷ�ʻ�����Ϣ
     */
    public Integer insertPlanOtherInvestApp(OtherInvestInfModel model);
    

	/**
     * app
     * ��ѯ�����ƻ�Ͷ�ʻ�����Ϣ
     */
    public List<OtherInvestInfModel> selectPlanOtherInvestApp(@Param("appNo")String appNo, @Param("commonPlanId")String commonPlanId);
    
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
