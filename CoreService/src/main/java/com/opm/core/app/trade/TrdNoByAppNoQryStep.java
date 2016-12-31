package com.opm.core.app.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.core.app.service.IAppService;

@Service("TrdNoByAppNoQryStep")
public class TrdNoByAppNoQryStep implements ITask {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TrdNoByAppNoQryStep.class);
	
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
		String appNo = (String)taskParam.getSelfParam();
		String trdType = (String)taskParam.getSelfParam();
		this.appService.qryTrdNo(appNo, trdType);
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
