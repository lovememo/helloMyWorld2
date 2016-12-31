package com.opm.core.app.template.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opm.common.model.RequestModel;
import com.opm.core.app.dic.AppState;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.service.imp.AppService;
import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.core.IAppOpAction.SUMBIT_ACTION;
import com.opm.core.app.template.trade.AbstractTradeTask;
import com.opm.core.common.service.FileService;

/**
 * 申请提交结束的task，用于更新申请状态
 * 
 * @author kfzx-zhengyy1
 *
 */
@Component
public class AppSubmitEndTask extends AbstractTradeTask {
	@Autowired
	AppService appService;

	@Autowired
	FileService fileService;
	
	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	protected ResultMessage doTask(AppParam appParam) {
		SUMBIT_ACTION submitAction = appParam.getSubmitAction();
		AppModel appModel = appParam.getAppModel();
		String newAppState = appModel.getAppState();
		if (submitAction == SUMBIT_ACTION.SUBMIT || submitAction == SUMBIT_ACTION.ASYNC_CALLBACK) {
			if (newAppState == null || "".equals(newAppState.trim())) {
				newAppState = AppState.AGREE.toString();
			}
			updateAppState(appModel.getAppNo(), newAppState);
		} else if (submitAction == SUMBIT_ACTION.ASYNC_SUBMIT) {
			if (newAppState == null || "".equals(newAppState.trim())) {
				newAppState = AppState.SAVE.toString();
			}
			updateAppState(appModel.getAppNo(), newAppState);
			//告知文件我已经搞定
			RequestModel requestModel = new RequestModel();
			requestModel.getPrivateCtx().put("serialNo", appParam.getFileNo());
			System.out.println(requestModel.getPrivateCtx().get("serialNo"));
			System.out.println((String)requestModel.getPrivateCtx().get("serialNo"));
			fileService.beginFileProcess(requestModel);
		}
		return null;
	}

	private void updateAppState(String appNo, String appState) {
		AppModel appModel = new AppModel();
		appModel.setAppNo(appNo);
		appModel.setAppState(appState);
		appService.modApp(appModel);
	}

	@Override
	protected ResultMessage doCompensate(AppParam compensateParam) {
		return null;
	}

	@Override
	protected ResultMessage check(AppParam compensateParam) {
		return null;
	}
	
	public static void main(String[] args) {

		RequestModel requestModel = new RequestModel();
		requestModel.getPrivateCtx().put("serialNo","123");
		System.out.println(requestModel.getPrivateCtx().get("serialNo"));
	}
}
