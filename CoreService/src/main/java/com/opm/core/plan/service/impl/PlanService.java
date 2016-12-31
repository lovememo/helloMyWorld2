package com.opm.core.plan.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.core.plan.dao.impl.PlanDao;
import com.opm.core.plan.model.PlanAppInfModel;
import com.opm.core.plan.model.PlanBasicInfoModel;
import com.opm.core.plan.model.PlanInfoModle;
import com.opm.core.plan.service.IPlanService;
import com.opm.core.workflow.model.ApplicationModle;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-liuyz1 on 2016/10/24.
 */
@Service("PlanService")
public class PlanService implements IPlanService {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PlanService.class);

	@Autowired
	private PlanDao dao;

	@Override
	public PlanInfoModle getPlanBasicInfo(String planId) {
		LOGGER.warn("getPlanBasicInfo");
		return new PlanInfoModle();
	}

	@Override
	public List<Map<String, String>> getPlanApplys(String branchId, String userId, String roleId) {
		List<Map<String, String>> ls = new ArrayList<Map<String, String>>();
		List<ApplicationModle> list = this.dao.getPlanApplys(branchId, userId, roleId);
		for (ApplicationModle apply : list) {
			Map<String, String> t = new HashMap<String, String>();
			t.put("appNo", apply.getApplyId());
			t.put("planName", apply.getPlanName());
			t.put("appUser", apply.getApplyUser());
			t.put("appType", apply.getApplyType().getDescription());
			t.put("appState", apply.getApplyStatus().getDescription());
			t.put("appTime", new SimpleDateFormat("yyyy-MM-dd hhmmss").format(apply.getApplyTime()));

			ls.add(t);
		}
		return ls;
	}

	/**
	 * 获取申请明细信息
	 * 
	 * @param appNo
	 *            申请号
	 * @return 申请明细信息
	 */
	@Override
	public PlanAppInfModel getPlanAppDet(long appNo) {
		return this.dao.getPlanAppDet(appNo);
	}
	
	/**
     * 接口
     * 传入当前计划编码，返回关联计划列表，含计划编码，是否主受托人。
     * @param planId 计划编码
     * @return 计划信息
     */
    public PlanBasicInfoModel getPlanInfByPlanId(String planId){
    	return this.dao.getPlanInfByPlanId(planId);
    }

	/**
     * app
     * 保存计划基本信息
     */
    public Integer insertPlanBasicApp(PlanBasicInfoModel model){
    	return this.dao.insertPlanBasicApp(model);
    }
    

	/**
     * app
     * 查询计划基本信息
     */
    public PlanBasicInfoModel selectPlanBasicApp(String appNo){
    	return this.dao.selectPlanBasicApp(appNo);
    }
    

	/**
     * app
     * 更新计划基本信息
     */
    public Integer updatePlanBasicApp(PlanBasicInfoModel model){
    	return this.dao.updatePlanBasicApp(model);
    }
    

	/**
     * app
     * 获取保存的计划基本信息
     */
    public List<Map<String,String>> selectSavedPlanApp(){
    	return this.dao.selectSavedPlanApp();
    }
    /**
     * 交易
     * 保存计划基本信息
     */
    public Integer insertPlanBasicTrd(PlanBasicInfoModel model){
    	return this.dao.insertPlanBasicTrd(model);
    }
    

	/**
     * 交易
     * 查询计划基本信息
     */
    public PlanBasicInfoModel selectPlanBasicTrd(String trdNo){
    	return this.dao.selectPlanBasicTrd(trdNo);
    }
    

	/**
     * 交易
     * 更新计划基本信息
     */
    public Integer updatePlanBasicTrd(PlanBasicInfoModel model){
    	return this.dao.updatePlanBasicTrd(model);
    }

    /**
     * 交易
     * 获取保存的计划基本信息
     */
    public List<Map<String,String>> selectSavedPlanTrd(){
    	return this.dao.selectSavedPlanTrd();
    }


	/**
     * 生效
     * 保存计划基本信息
     */
    public Integer insertPlanBasicInf(PlanBasicInfoModel model){
    	return this.dao.insertPlanBasicInf(model);
    }
    

	/**
     * 
     * 查询计划基本信息
     */
    public PlanBasicInfoModel selectPlanBasicInf(String planId){
    	return this.dao.selectPlanBasicInf(planId);
    }
    

	/**
     * 生效
     * 更新计划基本信息
     */
    public Integer updatePlanBasicInf(PlanBasicInfoModel model){
    	return this.dao.updatePlanBasicInf(model);
    }
}
