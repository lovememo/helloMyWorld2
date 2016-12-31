package com.opm.core.plan.trade;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.core.app.model.AppTrdRelModel;
import com.opm.core.app.service.IAppService;
import com.opm.core.app.service.ITrdService;
import com.opm.core.plan.model.PlanBasicInfoModel;
import com.opm.core.plan.service.IPlanService;

@Service("PlanBasicPreDoTask")
public class PlanBasicTrdDoTask implements ITask {

	private static final String TRD_TYPE = "PLANBASIC";

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanBasicTrdDoTask.class);

	@Autowired
	private IPlanService planService;

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
	@Transactional
	public RetResult doTask(TaskParam taskParam) {
		Map<String, Object> paramMap = (Map<String, Object>) taskParam.getSelfParam();
		String appNo = (String) paramMap.get("appNo");

		// 申请和交易关系
		AppTrdRelModel appTrdRelModel = new AppTrdRelModel();
		appTrdRelModel.setAppNo(appNo);
		appTrdRelModel.setTrdType(TRD_TYPE);
		appTrdRelModel = this.appService.qryTrdAppRef(appTrdRelModel);
		
		//查询计划基本信息
		PlanBasicInfoModel basicTrd = planService.selectPlanBasicTrd(appTrdRelModel.getTrdNo());
		//生效basic
		planService.insertPlanBasicInf(basicTrd);

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
