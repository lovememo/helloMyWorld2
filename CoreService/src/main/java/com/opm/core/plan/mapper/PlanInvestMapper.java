package com.opm.core.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.core.plan.model.PlanInvestModel;

@Mapper
public interface PlanInvestMapper {

	/**
     * app
     * ����Ͷ�ʻ�����Ϣ
     */
    public Integer insertPlanInvestApp(PlanInvestModel model);
    

	/**
     * app
     * ��ѯͶ�ʻ�����Ϣ
     */
    public List<PlanInvestModel> selectPlanInvestApp(@Param("appNo")String appNo, @Param("planId")String planId, @Param("investId")String investId);
    

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
