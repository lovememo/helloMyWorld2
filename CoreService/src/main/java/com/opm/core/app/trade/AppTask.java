package com.opm.core.app.trade;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.common.model.RequestModel;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.service.IAppService;

import ch.qos.logback.classic.Logger;

/**
 * ��������
 * @author kfzx-chenym
 *
 */
@Service("AppTask")
public class AppTask implements ITask {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AppTask.class);

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
		RetResult rr = null;
		try {
			//������װ
			RequestModel reqModel = (RequestModel)taskParam.getSelfParam();
			AppModel appModel = new AppModel(); 
			appModel.setAppNo(reqModel.getStringValue("appNo"));
			appModel.setPlanId(reqModel.getStringValue("planId"));
			appModel.setAppUser(reqModel.getStringValue("userId"));
			appModel.setAppPlanTime(reqModel.getStringValue("planTime")); //TODO ����ʱ�ƻ�ʱ��
			appModel.setAppType(reqModel.getStringValue("appType"));
			appModel.setOpFlag(reqModel.getStringValue("opFlag"));
			appModel.setAudPlanTime(reqModel.getStringValue("planTime")); //TODO ����ʱ�ƻ�ʱ��
			appModel.setAudUser(reqModel.getStringValue("userId"));
			
			//��������
			this.appService.saveApp(appModel);
			
			//�����ж�
			/*switch (appModel.getOpFlag() ) {//TODO Q:������״̬�Ķ�Ӧ��
				case SAVE:
					appModel.setAppState("1"); //����
					break;
				case SUBMIT:
					appModel.setAppState("2"); //�����
					this.appService.controlApp(appModel);
				case AUDIT:
					appModel.setAppState("8"); //ͬ��
					this.appService.controlApp(appModel);
				case DENY:
					appModel.setAppState("9"); //���	
					this.appService.controlApp(appModel);
				case CANCEL:
					appModel.setAppState("10"); //����
					this.appService.controlApp(appModel);
					break;
				default:
					break;
			}*/
	
			rr = new RetResult();
			//bussiness����
			Map contextObj = new HashMap();
			contextObj.put("appNo", appModel.getAppNo());
			rr.setContextObj(contextObj);
			//Task����
			rr.setReturnObj(appModel);
			//Taskִ�н��
			rr.setResult(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw e;
		}
		return rr;
		
	}

	@Override
	public RetResult doCompensate(TaskParam compensateParam) {
		RetResult rr = new RetResult();
		//TODO "ROLLBACK" OPFLAG
		rr.setResult(false);
		return rr;
	}

	public IAppService getAppService() {
		return appService;
	}

	public void setAppService(IAppService appService) {
		this.appService = appService;
	}

	

}
