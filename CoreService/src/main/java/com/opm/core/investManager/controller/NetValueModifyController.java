package com.opm.core.investManager.controller;

import java.util.List;


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
import com.opm.core.app.model.AppQryParam;
import com.opm.core.app.trade.AppTask;
import com.opm.core.app.trade.TrdNoByAppNoQryStep;
import com.opm.core.investManager.model.NetValueAppFormModel;
import com.opm.core.investManager.service.INetValueService;
import com.opm.core.investManager.trade.NetValueAppDetTask;
import com.opm.core.investManager.trade.NetValueTrdDoTask;
import com.opm.core.investManager.trade.NetValueTrdPreTask;
import com.opm.core.investManager.trade.channel.AppChannel;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/investmanager/netvalue/modify")
public class NetValueModifyController {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(NetValueModifyController.class);
	
	@Autowired
	private INetValueService netValueService = null;
	
	
	/**
	 * ��ѯ�ѱ���ľ�ֵ�޸�����
	 * @param reqModel
	 * @return
	 * url:/investmanager/netvalue/list
	 */
	@RequestMapping(value = "/list",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel list(RequestModel reqModel) {
		//��������
		String funcName = "��ѯ��ֵ¼�뱣��״̬������";
		
		ResponseModel resModel = null;
		try {
			//��������
			String planId = reqModel.getStringValue("planId");
			AppQryParam appQryParam = new AppQryParam();
			appQryParam.setPlanId(planId);
			appQryParam.setAppState("8");
			
			//��ѯ���ֵľ�ֵ����
			List appList = this.netValueService.qryOpmApp(appQryParam);

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
	 * ���ر���������
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
	
	/**
	 * �ύ��ֵ¼��
	 * @param params
	 * @return
	 * url:/investmanager/netvalue/sumbit
	 */
	@RequestMapping(value = "/sumbit",method = {RequestMethod.POST,RequestMethod.GET})
	public Object submit(RequestModel reqModel){
		CommonBusiness netValueBusiness = new CommonBusiness();
		RetResult brr = netValueBusiness
			.register(AppTask.class, reqModel) //��������
			.register(NetValueAppDetTask.class, reqModel) //���澻ֵ������ϸ
			.register(NetValueTrdPreTask.class, reqModel) //���澻ֵ������ϸ
		.doBusiness();
		return brr.getReturnObj();
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
			.register(AppTask.class, reqModel, AppChannel.class)
			.register(TrdNoByAppNoQryStep.class, reqModel) 
			.register(NetValueTrdDoTask.class, reqModel)
			.register(AppTask.class, reqModel, AppChannel.class) //��������״̬
		.doBusiness();
		return (String)brr.getReturnObj();
		
	}
	
}
