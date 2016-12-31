package com.opm.core.investManager.trade;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opm.common.business.task.ITask;
import com.opm.common.model.RequestModel;
import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueMAppEntity;
import com.opm.core.investManager.service.INetValueService;

import ch.qos.logback.classic.Logger;

/**
 * 净值
 * Prepare
 * @author kfzx-chenym
 *
 */
@Service("NetValueAppDetTask")
public class NetValueAppDetTask implements ITask {
	
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(NetValueAppDetTask.class);
    
    @Autowired
    private INetValueService netValueService; 

	@Override
	public RetResult check(TaskParam checkParam) {
		LOGGER.debug("checkParam=" + checkParam.toString());
		RetResult rr = new RetResult(); 
		rr.setResult(true); 
		return rr;
	}

	@Override
	public boolean compensable() {
		return false;
	}

	@Transactional
	@Override
	public RetResult doTask(TaskParam taskParam) {
		RequestModel reqModel = (RequestModel)taskParam.getSelfParam();
		String appNo = (String) taskParam.getContextParam().get("appNo");
		
		String commonPlanId = (String) reqModel.getStringValue("commonPlanId");
		String evaluateDate = (String) reqModel.getStringValue("evaluateDate");
		String acceptDate = (String) reqModel.getStringValue("acceptDate");
		String nextEvaluateDate = (String) reqModel.getStringValue("nextEvaluateDate");
		String planId = (String) reqModel.getStringValue("planId");
		List planList = (List) reqModel.getMapListValue("planList");
		for (int i = 0; i < planList.size(); i++) {
			Map tPlan = (Map) planList.get(i);
			String tPlanId = tPlan.get("planId").toString();
			
			NetValueMAppEntity tNetValueMAppEntity = new NetValueMAppEntity(tPlan);
			tNetValueMAppEntity.setAcceptDate(acceptDate);
			tNetValueMAppEntity.setEvaluateDate(evaluateDate);
			tNetValueMAppEntity.setNextEvaluateDate(nextEvaluateDate);
			tNetValueMAppEntity.setCommonPlanId(commonPlanId);
			tNetValueMAppEntity.setAppNo(appNo);
			this.netValueService.saveMApp(tNetValueMAppEntity);
			List investList = (List) tPlan.get("investList");
			for (int j = 0; j < investList.size(); j++) {
				Map tInvest = (Map) investList.get(j);
				NetValueAppEntity tNetValueAppModel = new NetValueAppEntity(tInvest);
				tNetValueAppModel.setPlanId(tPlanId);
				tNetValueAppModel.setAppNo(appNo);
				tNetValueAppModel.setEvaluateDate(evaluateDate);
				this.netValueService.saveApp(tNetValueAppModel);
			}
		}
		//统一计划层
		Map sumInvest = reqModel.getMapValue("sumInvest");
		NetValueAppEntity sumNetValueAppModel = new NetValueAppEntity(sumInvest);
		sumNetValueAppModel.setAppNo(appNo);
		sumNetValueAppModel.setAcceptDate(acceptDate);
		sumNetValueAppModel.setEvaluateDate(evaluateDate);
		sumNetValueAppModel.setAppNo(appNo);
		sumNetValueAppModel.setPlanId(planId);
		this.netValueService.saveApp(sumNetValueAppModel);

		RetResult rr = new RetResult();
		rr.setResult(true);
		rr.setReturnObj(appNo);
		return rr;
	}

	@Override
	public RetResult doCompensate(TaskParam compensateParam) {
		RetResult rr = new RetResult();
		rr.setResult(false);
		return rr;
	}

	public INetValueService getNetValueService() {
		return netValueService;
	}

	public void setNetValueService(INetValueService netValueService) {
		this.netValueService = netValueService;
	}

}
