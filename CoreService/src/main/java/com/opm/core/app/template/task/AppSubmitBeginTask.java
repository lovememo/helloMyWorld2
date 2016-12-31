package com.opm.core.app.template.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opm.core.app.dic.AppState;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.service.imp.AppService;
import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.core.IAppOpAction.SUMBIT_ACTION;
import com.opm.core.app.template.trade.AbstractTradeTask;
import com.opm.core.common.model.FileRelApp;
import com.opm.core.common.service.FileService;
import com.opm.core.common.tools.CheckTool;

/**
 * 申请提交前的准备Task，用于插入一条保存的申请
 * 
 * @author kfzx-zhengyy1
 *
 */
@Component
public class AppSubmitBeginTask extends AbstractTradeTask {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AppSubmitBeginTask.class);
	@Autowired
	AppService appService;

	@Autowired
	FileService fileService;

	@Override
	public boolean compensable() {
		return true;
	}

	@Override
	protected ResultMessage check(AppParam appParam) {
		SUMBIT_ACTION submitAction = appParam.getSubmitAction();
		if (submitAction == SUMBIT_ACTION.SUBMIT || submitAction == SUMBIT_ACTION.ASYNC_SUBMIT) {
			// 提交和異步提交都需要校验申请的数据。
			AppModel appModel = appParam.getAppModel();
			if (!CheckTool.isNotNull(appModel.getPlanId())) {
				return ResultMessage.getWrong("planid不可为空，请在APP模块对AppParam.AppModel进行初始化");
			}
			if (!CheckTool.isNotNull(appModel.getAppUser())) {
				return ResultMessage.getWrong("AppUser不可为空，请在APP模块对AppParam.AppModel进行初始化");
			}
			if (!CheckTool.isNotNull(appModel.getAudRole())) {
				return ResultMessage.getWrong("AudRole不可为空，请在APP模块对AppParam.AppModel进行初始化");
			}
			if (!CheckTool.isNotNull(appModel.getAppType())) {
				return ResultMessage.getWrong("AppType不可为空，请在APP模块对AppParam.AppModel进行初始化");
			}
		} else if (submitAction == SUMBIT_ACTION.ASYNC_CALLBACK) {
			// 异步提交的回调函数，需要传入文件号
			if (!CheckTool.isNotNull(appParam.getFileNo())) {
				return ResultMessage.getWrong("fileNo不可为空!");
			}
		}
		return ResultMessage.getRight("申请校验通过！");
	}

	@Override
	protected ResultMessage doTask(AppParam appParam) {
		SUMBIT_ACTION submitAction = appParam.getSubmitAction();
		if (submitAction == SUMBIT_ACTION.SUBMIT || submitAction == SUMBIT_ACTION.ASYNC_SUBMIT) {
			// 提交和異步提交都需要生成新的申请。
			AppModel appModel = appParam.getAppModel();
			appService.saveApp(appModel);
		} else if (submitAction == SUMBIT_ACTION.ASYNC_CALLBACK) {
			// 异步提交的回调函数
			// 1、获取申请号
			String fileNo = appParam.getFileNo();
			FileRelApp fileRelApp = new FileRelApp();
			fileRelApp.setSerial_no(fileNo);
			FileRelApp qryFileRel = fileService.qryFileRel(fileRelApp);
			appParam.getAppModel().setAppNo(qryFileRel.getApp_no());

		}
		return ResultMessage.getRight("AppPrepareBeginTask执行成功");
	}

	@Override
	protected ResultMessage doCompensate(AppParam appParam) {

		SUMBIT_ACTION submitAction = appParam.getSubmitAction();
		AppModel appModel = new AppModel();
		appModel.setAppNo(appParam.getAppModel().getAppNo());
		if (submitAction == SUMBIT_ACTION.SUBMIT || submitAction == SUMBIT_ACTION.ASYNC_SUBMIT) {
			appModel.setAppState(AppState.DELETE.toString());
			appModel.setAudMemo("交易失败，申请回滚！");
			appService.modApp(appModel);
			LOGGER.info("交易执行失败，申请删除！appParam:"+appParam);
		} else if (submitAction == SUMBIT_ACTION.ASYNC_CALLBACK) {
			appModel.setAppState(AppState.DENY.toString());
			appModel.setAudMemo("交易失败，申请否决！");
			appService.modApp(appModel);
			LOGGER.info("交易执行失败，申请否决！appParam:"+appParam);
		}
		return ResultMessage.getRight("申请compensate成功！");
	}
}
