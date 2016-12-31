package com.opm.core.app.template.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opm.core.app.model.AppModel;
import com.opm.core.app.service.imp.AppService;
import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.trade.AbstractTradeTask;

/**
 * 申请提交结束的task，用于更新申请状态
 * @author kfzx-zhengyy1
 *
 */
@Component
public class AppDoEndTask extends AbstractTradeTask{
	@Autowired
	AppService appService;


	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	protected ResultMessage doTask(AppParam taskParam) {
		updateAppToAgree(taskParam.getAppModel().getAppNo());
		return null;
	}

	private void updateAppToAgree(String appNo) {
		AppModel appModel = new AppModel();
		appModel.setAppNo(appNo);
		appModel.setAppState("8");
		appService.modApp(appModel);
	}

	@Override
	protected ResultMessage doCompensate(AppParam compensateParam) {
		return null;
	}


	@Override
	protected ResultMessage check(AppParam compensateParam) {
		return null;
	}}
