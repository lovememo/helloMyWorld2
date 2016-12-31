package com.opm.core.investManager.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.core.investManager.entity.NetValueInfEntity;
import com.opm.core.investManager.model.NetValueAppFormModel2;
import com.opm.core.investManager.model.NetValueInfQueryFormModel;
import com.opm.core.investManager.service.INetValueService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/investmanager/netvalue/query")
public class NetValueQueryController {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(NetValueQueryController.class);
	
	@Autowired
	private INetValueService netValueService = null;
	
	
	/**
	 * ��ѯ��ֵ
	 * @param reqModel
	 * @return
	 * url:/investmanager/netvalue/list
	 */
	@RequestMapping(value = "/index",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel initQry(RequestModel reqModel) {

		String funcName = "��ѯ��ֵ"; //��������
		ResponseModel resModel = null;
		try {
			//��������
			String planId = reqModel.getStringValue("planId");
			//��ѯ�ƻ��б�Ͷ�����
			NetValueInfQueryFormModel netValueInfQueryFormModel = this.netValueService.initQryForm(planId);
			//���ؽ��
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", netValueInfQueryFormModel);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
			
		}
		//���ؽ��
		return resModel;
	}
	
	/**
	 * ��ֵ��ϸ
	 * @return
	 * url:/investmanager/netvalue/add
	 */
	@RequestMapping(value = "/detail",method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseModel detail(RequestModel reqModel) {
		
		String funcName = "��ֵ��ϸ";
		ResponseModel resModel = null;
		try {
			//��������
			String qryPlanId = reqModel.getStringValue("qryPlanId");
			String evaluateDate = reqModel.getStringValue("evaluateDate");
			
			NetValueInfEntity netValueInfEntity = new NetValueInfEntity();
			netValueInfEntity.setPlanId(qryPlanId);
			netValueInfEntity.setEvaluateDate(evaluateDate);
			
			//��ѯ��ֵ��ϸ
			NetValueAppFormModel2 netValueAppFormModel = this.netValueService.qryInfDesc(netValueInfEntity);
			
			//���ؽ��
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", netValueAppFormModel);
			
		} catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
		}

		
		return resModel;
	}
	
}
