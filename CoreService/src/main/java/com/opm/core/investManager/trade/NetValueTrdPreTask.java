package com.opm.core.investManager.trade;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.common.model.RequestModel;
import com.opm.core.app.dic.TrdOpFlag;
import com.opm.core.app.model.AppTrdRelModel;
import com.opm.core.app.model.TrdModel;
import com.opm.core.app.service.IAppService;
import com.opm.core.app.service.ITrdService;
import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueTrdEntity;
import com.opm.core.investManager.service.INetValueService;

import ch.qos.logback.classic.Logger;

/**
 * 净值管理
 * Prepare
 * @author kfzx-chenym
 *
 */
@Service("NetValueTrdPreTask")
public class NetValueTrdPreTask implements ITask {
	
	private static final String TYPE = "NETVALUETRD";
	
	private static final String STATE = "PREPARED";
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(NetValueTrdPreTask.class);
	
	@Autowired
	private INetValueService netValueService;
	
	@Autowired
	private ITrdService trdService;
	
	@Autowired
	private IAppService appService;

	@Override
	public RetResult check(TaskParam checkParam) {
		RetResult rr = new RetResult();
		rr.setResult(true);
		return rr;
	}

	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	public RetResult doTask(TaskParam taskParam) {
		RequestModel paramMap = (RequestModel) taskParam.getSelfParam();
		String appNo = (String) taskParam.getContextParam().get("appNo");
		
		//通过申请号判断是否存在交易主表信息
		AppTrdRelModel qryAppTrdRelModel = new AppTrdRelModel();
		qryAppTrdRelModel.setAppNo(appNo);
		qryAppTrdRelModel.setTrdType(TYPE);
		AppTrdRelModel resAppTrdRelModel = this.appService.qryTrdAppRef(qryAppTrdRelModel);
		
		String trdNo = null;
		if ( resAppTrdRelModel == null ) {
			//保存交易主表
			TrdModel trdModel = new TrdModel();
			trdModel.setTrdType(TYPE);
			trdModel.setTrdState("PREPARED");
			this.trdService.saveTrd(trdModel);
			trdNo = trdModel.getTrdNo();
		} else {
			trdNo = resAppTrdRelModel.getTrdNo();
		}
		
		String evaluateDate = (String) paramMap.getStringValue("evaluateDate");
		String planId = (String) paramMap.getStringValue("planId");
		//保存交易明细
		//所有计划
		List planList = paramMap.getMapListValue("planList");
		for (int i = 0; i < planList.size(); i++) {
			Map tPlan = (Map) planList.get(i);
			String tPlanId = (String) tPlan.get("planId");
			
			//计划下投资组合
			List investList = (List) tPlan.get("investList");
			for (int j = 0; j < investList.size(); j++) {
				Map tInvest = (Map) investList.get(j);
				NetValueTrdEntity tNetValueTrdModel = new NetValueTrdEntity(tInvest);
				tNetValueTrdModel.setEvaluateDate(evaluateDate);
				tNetValueTrdModel.setPlanId(tPlanId);
				tNetValueTrdModel.setTrdNo(trdNo);
				//逐条保存交易
				this.netValueService.saveTrd(tNetValueTrdModel);
			}
		}
		//统一计划层
		Map sumInvest = paramMap.getMapValue("sumInvest");
		NetValueTrdEntity tNetValueTrdModel = new NetValueTrdEntity(sumInvest);
		tNetValueTrdModel.setEvaluateDate(evaluateDate);
		tNetValueTrdModel.setPlanId(planId);
		tNetValueTrdModel.setTrdNo(trdNo);
		
		
		//保存申请和交易关系
		AppTrdRelModel appTrdRelModel = new AppTrdRelModel();
		appTrdRelModel.setAppNo(appNo);
		appTrdRelModel.setTrdNo(trdNo);
		appTrdRelModel.setTrdType(TYPE);
		this.appService.relTrdApp(appTrdRelModel);
		
		RetResult rr = new RetResult();
		rr.setResult(true);
		return rr;
	}
	
	@Override
	public RetResult doCompensate(TaskParam compensateParam) {
		RetResult rr = new RetResult();
		rr.setResult(false);
		return rr;
	}

}