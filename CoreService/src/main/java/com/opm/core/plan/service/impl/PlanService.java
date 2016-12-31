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
	 * ��ȡ������ϸ��Ϣ
	 * 
	 * @param appNo
	 *            �����
	 * @return ������ϸ��Ϣ
	 */
	@Override
	public PlanAppInfModel getPlanAppDet(long appNo) {
		return this.dao.getPlanAppDet(appNo);
	}
	
	/**
     * �ӿ�
     * ���뵱ǰ�ƻ����룬���ع����ƻ��б����ƻ����룬�Ƿ��������ˡ�
     * @param planId �ƻ�����
     * @return �ƻ���Ϣ
     */
    public PlanBasicInfoModel getPlanInfByPlanId(String planId){
    	return this.dao.getPlanInfByPlanId(planId);
    }

	/**
     * app
     * ����ƻ�������Ϣ
     */
    public Integer insertPlanBasicApp(PlanBasicInfoModel model){
    	return this.dao.insertPlanBasicApp(model);
    }
    

	/**
     * app
     * ��ѯ�ƻ�������Ϣ
     */
    public PlanBasicInfoModel selectPlanBasicApp(String appNo){
    	return this.dao.selectPlanBasicApp(appNo);
    }
    

	/**
     * app
     * ���¼ƻ�������Ϣ
     */
    public Integer updatePlanBasicApp(PlanBasicInfoModel model){
    	return this.dao.updatePlanBasicApp(model);
    }
    

	/**
     * app
     * ��ȡ����ļƻ�������Ϣ
     */
    public List<Map<String,String>> selectSavedPlanApp(){
    	return this.dao.selectSavedPlanApp();
    }
    /**
     * ����
     * ����ƻ�������Ϣ
     */
    public Integer insertPlanBasicTrd(PlanBasicInfoModel model){
    	return this.dao.insertPlanBasicTrd(model);
    }
    

	/**
     * ����
     * ��ѯ�ƻ�������Ϣ
     */
    public PlanBasicInfoModel selectPlanBasicTrd(String trdNo){
    	return this.dao.selectPlanBasicTrd(trdNo);
    }
    

	/**
     * ����
     * ���¼ƻ�������Ϣ
     */
    public Integer updatePlanBasicTrd(PlanBasicInfoModel model){
    	return this.dao.updatePlanBasicTrd(model);
    }

    /**
     * ����
     * ��ȡ����ļƻ�������Ϣ
     */
    public List<Map<String,String>> selectSavedPlanTrd(){
    	return this.dao.selectSavedPlanTrd();
    }


	/**
     * ��Ч
     * ����ƻ�������Ϣ
     */
    public Integer insertPlanBasicInf(PlanBasicInfoModel model){
    	return this.dao.insertPlanBasicInf(model);
    }
    

	/**
     * 
     * ��ѯ�ƻ�������Ϣ
     */
    public PlanBasicInfoModel selectPlanBasicInf(String planId){
    	return this.dao.selectPlanBasicInf(planId);
    }
    

	/**
     * ��Ч
     * ���¼ƻ�������Ϣ
     */
    public Integer updatePlanBasicInf(PlanBasicInfoModel model){
    	return this.dao.updatePlanBasicInf(model);
    }
}
