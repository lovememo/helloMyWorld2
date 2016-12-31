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
 * �����ύǰ��׼��Task�����ڲ���һ�����������
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
			// �ύ�ͮ����ύ����ҪУ����������ݡ�
			AppModel appModel = appParam.getAppModel();
			if (!CheckTool.isNotNull(appModel.getPlanId())) {
				return ResultMessage.getWrong("planid����Ϊ�գ�����APPģ���AppParam.AppModel���г�ʼ��");
			}
			if (!CheckTool.isNotNull(appModel.getAppUser())) {
				return ResultMessage.getWrong("AppUser����Ϊ�գ�����APPģ���AppParam.AppModel���г�ʼ��");
			}
			if (!CheckTool.isNotNull(appModel.getAudRole())) {
				return ResultMessage.getWrong("AudRole����Ϊ�գ�����APPģ���AppParam.AppModel���г�ʼ��");
			}
			if (!CheckTool.isNotNull(appModel.getAppType())) {
				return ResultMessage.getWrong("AppType����Ϊ�գ�����APPģ���AppParam.AppModel���г�ʼ��");
			}
		} else if (submitAction == SUMBIT_ACTION.ASYNC_CALLBACK) {
			// �첽�ύ�Ļص���������Ҫ�����ļ���
			if (!CheckTool.isNotNull(appParam.getFileNo())) {
				return ResultMessage.getWrong("fileNo����Ϊ��!");
			}
		}
		return ResultMessage.getRight("����У��ͨ����");
	}

	@Override
	protected ResultMessage doTask(AppParam appParam) {
		SUMBIT_ACTION submitAction = appParam.getSubmitAction();
		if (submitAction == SUMBIT_ACTION.SUBMIT || submitAction == SUMBIT_ACTION.ASYNC_SUBMIT) {
			// �ύ�ͮ����ύ����Ҫ�����µ����롣
			AppModel appModel = appParam.getAppModel();
			appService.saveApp(appModel);
		} else if (submitAction == SUMBIT_ACTION.ASYNC_CALLBACK) {
			// �첽�ύ�Ļص�����
			// 1����ȡ�����
			String fileNo = appParam.getFileNo();
			FileRelApp fileRelApp = new FileRelApp();
			fileRelApp.setSerial_no(fileNo);
			FileRelApp qryFileRel = fileService.qryFileRel(fileRelApp);
			appParam.getAppModel().setAppNo(qryFileRel.getApp_no());

		}
		return ResultMessage.getRight("AppPrepareBeginTaskִ�гɹ�");
	}

	@Override
	protected ResultMessage doCompensate(AppParam appParam) {

		SUMBIT_ACTION submitAction = appParam.getSubmitAction();
		AppModel appModel = new AppModel();
		appModel.setAppNo(appParam.getAppModel().getAppNo());
		if (submitAction == SUMBIT_ACTION.SUBMIT || submitAction == SUMBIT_ACTION.ASYNC_SUBMIT) {
			appModel.setAppState(AppState.DELETE.toString());
			appModel.setAudMemo("����ʧ�ܣ�����ع���");
			appService.modApp(appModel);
			LOGGER.info("����ִ��ʧ�ܣ�����ɾ����appParam:"+appParam);
		} else if (submitAction == SUMBIT_ACTION.ASYNC_CALLBACK) {
			appModel.setAppState(AppState.DENY.toString());
			appModel.setAudMemo("����ʧ�ܣ���������");
			appService.modApp(appModel);
			LOGGER.info("����ִ��ʧ�ܣ���������appParam:"+appParam);
		}
		return ResultMessage.getRight("����compensate�ɹ���");
	}
}
