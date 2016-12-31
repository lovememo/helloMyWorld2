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
 * 申请主表
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
			//参数封装
			RequestModel reqModel = (RequestModel)taskParam.getSelfParam();
			AppModel appModel = new AppModel(); 
			appModel.setAppNo(reqModel.getStringValue("appNo"));
			appModel.setPlanId(reqModel.getStringValue("planId"));
			appModel.setAppUser(reqModel.getStringValue("userId"));
			appModel.setAppPlanTime(reqModel.getStringValue("planTime")); //TODO 申请时计划时间
			appModel.setAppType(reqModel.getStringValue("appType"));
			appModel.setOpFlag(reqModel.getStringValue("opFlag"));
			appModel.setAudPlanTime(reqModel.getStringValue("planTime")); //TODO 复核时计划时间
			appModel.setAudUser(reqModel.getStringValue("userId"));
			
			//保存申请
			this.appService.saveApp(appModel);
			
			//操作判断
			/*switch (appModel.getOpFlag() ) {//TODO Q:操作和状态的对应？
				case SAVE:
					appModel.setAppState("1"); //保存
					break;
				case SUBMIT:
					appModel.setAppState("2"); //待审核
					this.appService.controlApp(appModel);
				case AUDIT:
					appModel.setAppState("8"); //同意
					this.appService.controlApp(appModel);
				case DENY:
					appModel.setAppState("9"); //否决	
					this.appService.controlApp(appModel);
				case CANCEL:
					appModel.setAppState("10"); //撤销
					this.appService.controlApp(appModel);
					break;
				default:
					break;
			}*/
	
			rr = new RetResult();
			//bussiness参数
			Map contextObj = new HashMap();
			contextObj.put("appNo", appModel.getAppNo());
			rr.setContextObj(contextObj);
			//Task参数
			rr.setReturnObj(appModel);
			//Task执行结果
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
