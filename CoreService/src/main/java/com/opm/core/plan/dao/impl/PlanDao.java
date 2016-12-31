package com.opm.core.plan.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.plan.dao.IPlanDao;
import com.opm.core.plan.mapper.PlanMapper;
import com.opm.core.plan.model.PlanAppInfModel;
import com.opm.core.plan.model.PlanBasicInfoModel;
import com.opm.core.workflow.model.ApplicationModle;

@Repository("PlanDao")
public class PlanDao implements IPlanDao {
	@Autowired
	private PlanMapper planMapper;

	@Override
	public PlanAppInfModel getPlanAppDet(long appNo) {
		return this.planMapper.getPlanAppDet(appNo);
	}
	
	@Override
	public List<ApplicationModle> getPlanApplys(String branchId, String userId, String roleId) {
		return this.planMapper.getPlanApplys(branchId, userId, roleId);
	}
	

	/**
     * �ӿ�
     * ���뵱ǰ�ƻ����룬���ع����ƻ��б����ƻ����룬�Ƿ��������ˡ�
     * @param planId �ƻ�����
     * @return �ƻ���Ϣ
     */
    public PlanBasicInfoModel getPlanInfByPlanId(String planId){
    	return this.planMapper.getPlanInfByPlanId(planId);
    }
    

	/**
     * app
     * ����ƻ�������Ϣ
     */
    public Integer insertPlanBasicApp(PlanBasicInfoModel model){
    	return this.planMapper.insertPlanBasicApp(model);
    }
    

	/**
     * app
     * ��ѯ�ƻ�������Ϣ
     */
    public PlanBasicInfoModel selectPlanBasicApp(String appNo){
    	return this.planMapper.selectPlanBasicApp(appNo);
    }
    

	/**
     * app
     * ���¼ƻ�������Ϣ
     */
    public Integer updatePlanBasicApp(PlanBasicInfoModel model){
    	return this.planMapper.updatePlanBasicApp(model);
    }
    

	/**
     * app
     * ��ȡ����ļƻ�������Ϣ
     */
    public List<Map<String,String>> selectSavedPlanApp(){
    	return this.planMapper.selectSavedPlanApp();
    }
    
    /**
     * ����
     * ����ƻ�������Ϣ
     */
    public Integer insertPlanBasicTrd(PlanBasicInfoModel model){
    	return this.planMapper.insertPlanBasicTrd(model);
    }
    

	/**
     * ����
     * ��ѯ�ƻ�������Ϣ
     */
    public PlanBasicInfoModel selectPlanBasicTrd(String trdNo){
    	return this.planMapper.selectPlanBasicTrd(trdNo);
    }
    

	/**
     * ����
     * ���¼ƻ�������Ϣ
     */
    public Integer updatePlanBasicTrd(PlanBasicInfoModel model){
    	return this.planMapper.updatePlanBasicTrd(model);
    }
    
    /**
     * ����
     * ��ȡ����ļƻ�������Ϣ
     */
    public List<Map<String,String>> selectSavedPlanTrd(){
    	return this.planMapper.selectSavedPlanTrd();
    }
    

	/**
     * ��Ч
     * ����ƻ�������Ϣ
     */
    public Integer insertPlanBasicInf(PlanBasicInfoModel model){
    	return this.planMapper.insertPlanBasicInf(model);
    }
    

	/**
     * 
     * ��ѯ�ƻ�������Ϣ
     */
    public PlanBasicInfoModel selectPlanBasicInf(String planId){
    	return this.planMapper.selectPlanBasicInf(planId);
    }
    

	/**
     * ��Ч
     * ���¼ƻ�������Ϣ
     */
    public Integer updatePlanBasicInf(PlanBasicInfoModel model){
    	return this.planMapper.updatePlanBasicInf(model);
    }
}
