package com.opm.core.app.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.core.app.model.AppTrdRelModel;
import com.opm.core.app.service.IAppService;

@Service("AppTrdRelTask")
public class AppTrdRelTask implements ITask{
	
	@Autowired
	private IAppService appService;

	@Override
	public RetResult check(TaskParam checkParam) {
		
		RetResult res = new RetResult();
		res.setResult(true);
		return res;
	}

	@Override
	public boolean compensable() {
		return true;
	}

	@Override
	public RetResult doTask(TaskParam taskParam) {
		String appNo = (String)taskParam.getContextParam().get("appNo");
		String trdNo = (String)taskParam.getContextParam().get("trdNo");
		String trdType = (String)taskParam.getContextParam().get("trdType");
		//Long trdOrder = Long.valueOf((String)taskParam.getContextParam().get("trdOrder"));
		
		AppTrdRelModel appTrdRelModel = new AppTrdRelModel();
		appTrdRelModel.setAppNo(appNo);
		appTrdRelModel.setTrdNo(trdNo);
		appTrdRelModel.setTrdType(trdType);
		//appTrdRelModel.setTrdOrder(trdOrder);
		this.appService.relTrdApp(appTrdRelModel);
		
		RetResult res = new RetResult();
		res.setResult(true);
		return res;
	}

	@Override
	public RetResult doCompensate(TaskParam compensateParam) {
		RetResult res = new RetResult();
		res.setResult(false);
		return res;
	}

	public IAppService getAppService() {
		return appService;
	}

	public void setAppService(IAppService appService) {
		this.appService = appService;
	}
	
}
