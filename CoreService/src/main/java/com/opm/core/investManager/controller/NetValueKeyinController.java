package com.opm.core.investManager.controller;

import java.util.List;

import javax.xml.ws.soap.AddressingFeature.Responses;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.param.RetResult;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.task.AppAudTask;
import com.opm.core.app.trade.AppTask;
import com.opm.core.app.trade.TrdNoByAppNoQryStep;
import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.model.NetValueAppFormModel;
import com.opm.core.investManager.service.INetValueService;
import com.opm.core.investManager.trade.NetValueAppDetTask;
import com.opm.core.investManager.trade.NetValueTrdDoTask;
import com.opm.core.investManager.trade.NetValueTrdPreTask;
import com.opm.core.investManager.trade.channel.AppChannel;
import com.opm.core.plan.model.PlanBasicInfoModel;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/investmanager/netvalue")
public class NetValueKeyinController {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(NetValueKeyinController.class);
	
	@Autowired
	private INetValueService netValueService = null;
	
	
	/**
	 * ��ѯ�ѱ���ľ�ֵ¼������
	 * @param reqModel
	 * @return
	 * url:/investmanager/netvalue/list
	 */
	@RequestMapping(value = "/list",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel qrySaveList(RequestModel reqModel) {
		//��������
		String funcName = "��ѯ��ֵ¼�뱣��״̬������";
		
		ResponseModel resModel = null;
		try {
			//��������
			String planId = reqModel.getStringValue("planId");
			AppModel appModel = new AppModel();
			appModel.setPlanId(planId);
			
			//��ѯ���ֵľ�ֵ����
			List appList = this.netValueService.qrySavedApp(appModel);
			
			//���ؽ��
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", appList);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
			
		}
		//���ؽ��
		return resModel;
	}
	
	/**
	 * ��ֵ¼��ҳ���ʼ��
	 * @return
	 * url:/investmanager/netvalue/add
	 */
	@RequestMapping(value = "/add",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel add(RequestModel reqModel) {
		
		String funcName = "���ؼƻ���Ͷ����Ϣ";
		ResponseModel resModel = null;
		try {
			//��������
			String planId = reqModel.getStringValue("planId");
			
			//��ѯ�ƻ���Ͷ�������Ϣ
			NetValueAppFormModel planBasicInfoModel = this.netValueService.qryPlanInvest(planId);
			
			//���ؽ��
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", planBasicInfoModel);
			
		} catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}

		
		return resModel;
	}
	
	/**
	 * ������޸�
	 * @param reqModel
	 * @return
	 */
	@RequestMapping(value = "/mod",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel mod(RequestModel reqModel) {
		
		String funcName = "";
		ResponseModel resModel = null;
		try{
			//��������
			String appNo = reqModel.getStringValue("appNo");

			//��ѯ
			NetValueAppFormModel netValueAppFormModel = this.netValueService.qryAppDet(appNo);

			//���ؽ��
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName+"�ɹ�", netValueAppFormModel);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName+"ʧ��", null);
		}
		return resModel;
		
	}
	
	@RequestMapping(value = "/qrySaveApp",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel qrySaveApp(RequestModel reqModel) {
		String funcName = "���ؼƻ���Ͷ����Ϣ";
		ResponseModel resModel = null;
		try {
			//��������
			String planId = reqModel.getStringValue("planId");
			
			//��ѯ�����Ͷ�����
			NetValueAppFormModel planBasicInfoModel = this.netValueService.qryPlanInvest(planId);
			
			//���ؽ��
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", planBasicInfoModel);
			
		} catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}

		
		return resModel;
	}

	/**
	 * ���澻ֵ¼��
	 * @param params
	 * @return
	 * url:/investmanager/netvalue/save
	 */
	@RequestMapping(value = "/save",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel save(RequestModel reqModel){
		String funcName = "��ֵ����";
		ResponseModel resModel = null;
		try {
			CommonBusiness netValueBusiness = new CommonBusiness();
			RetResult brr = netValueBusiness
				.register(AppTask.class, reqModel) //��������
				.register(NetValueAppDetTask.class, reqModel) //���澻ֵ������ϸ
			.doBusiness();
			
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", null);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}
		
		return resModel;
		
	}
	
	/**
	 * �ύ��ֵ¼��
	 * @param params
	 * @return
	 * url:/investmanager/netvalue/sumbit
	 */
	@RequestMapping(value = "/sumbit",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel submit(RequestModel reqModel){
		
		String funcName = "��ֵ�ύ";
		ResponseModel resModel = null;
		
		try{
			
			CommonBusiness netValueBusiness = new CommonBusiness();
			RetResult brr = netValueBusiness
				.register(AppTask.class, reqModel) //��������
				.register(NetValueAppDetTask.class, reqModel) //���澻ֵ������ϸ
				.register(NetValueTrdPreTask.class, reqModel) //���澻ֵ������ϸ
			.doBusiness();
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", null);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
			
		}
		
		return resModel;
	}

	/**
	 * ���˾�ֵ¼��
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/audit",method = {RequestMethod.POST,RequestMethod.GET})
	public String audit(RequestModel reqModel){

		CommonBusiness netValueBusiness = new CommonBusiness();
		RetResult brr = netValueBusiness
			.register(AppAudTask.class, reqModel, AppChannel.class)
			.register(TrdNoByAppNoQryStep.class, reqModel) 
			.register(NetValueTrdDoTask.class, reqModel)
			.register(AppTask.class, reqModel, AppChannel.class) //��������״̬
		.doBusiness();
		return (String)brr.getReturnObj();
	}
	
	/**
	 * ɾ������ľ�ֵ¼������
	 * @return
	 * url:/investmanager/netvalue
	 */
	@RequestMapping(value = "/delsave",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel delSave(RequestModel reqModel){
		
		String funcName = "ɾ������ľ�ֵ¼������";
		ResponseModel resModel = null;
		try {
			
			String appNo = reqModel.getStringValue("appNo");
			NetValueAppEntity netValueAppModel = new NetValueAppEntity();
			netValueAppModel.setAppNo(appNo);
			this.netValueService.delApp(netValueAppModel);
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", null);
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}
		return resModel;
	}
}
