package com.opm.acct.mdt.det.imp.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.acct.common.remote.AppAudTask;
import com.opm.acct.common.remote.AppChannel;
import com.opm.acct.common.remote.AppModel;
import com.opm.acct.common.remote.AppTrdRelTask;
import com.opm.acct.mdt.det.imp.service.IMdtDetImpService;
import com.opm.acct.mdt.det.imp.trade.MdtDetImpTrdDoTask;
import com.opm.acct.mdt.det.imp.trade.MdtDetImpTrdPreTask;
import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.param.RetResult;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;

@RestController
@RequestMapping("/manacct/acctmdtdetimp")
public class AcctMdtDetImpController {
	private static final Logger LOGGER =  LoggerFactory.getLogger(AcctMdtDetImpController.class);
	@Autowired
	private IMdtDetImpService mdtDetImpService;
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/submit", method = { RequestMethod.POST, RequestMethod.GET })//URL=acct/manacct/acctmdtdetimp/submit
	public ResponseModel submit(RequestModel reqModel){
		String funcName = "���л���ϸ����";
		Map params = reqModel.getPrivateCtx();
		String serialNo = params.get("serialNo")+"";
		if (StringUtils.isEmpty(serialNo)) {
			return new ResponseModel(ResponseModel.State.FAIL, funcName + "�ύʧ��, δ�����ļ�", null);
		}
		try{
				//�ύ����
				CommonBusiness mdtDetImpBusiness = new CommonBusiness();
				RetResult brr = mdtDetImpBusiness
					.register(AppAudTask.class, reqModel.getPrivateCtx()) //�ύ���� 
					.register(MdtDetImpTrdPreTask.class, reqModel.getPrivateCtx()) //���л���ϸ��������׼��  У��  ����źͽ��׺� 
					.register(AppTrdRelTask.class, reqModel.getPrivateCtx()) //����źͽ��׺� 
				.doBusiness();
				if (!brr.getResult()) {
					return  new ResponseModel(ResponseModel.State.FAIL, brr.getReason(), null);
				}
				return new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", brr.getReturnObj());
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				return new ResponseModel(ResponseModel.State.FAIL, funcName + "�쳣", null);
			}
		
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/audit",method = RequestMethod.POST)
	public String audit(Map params){
		//������װ
		AppModel appModel = new AppModel(params);
		
		CommonBusiness netValueBusiness = new CommonBusiness();//TODO Q���ع�ʱ��Ҫ����ÿһTrade��Context����Ϊͬһ��keyֵ�����ڲ�ͬ��Trade�з����仯
		RetResult brr = netValueBusiness
			.register(AppAudTask.class, appModel, AppChannel.class) //�������� //������� //��������״̬дChannel //TODO Q��Channel����Tradeִ����ɺ�ִ�У�
			.register(MdtDetImpTrdDoTask.class, null) //������Ϣ�� //TODO Q���ͱ��潻����ϸ�ϲ�Ϊһ��Trade(һ������trd�� + һ��inf��)
			.register(AppTrdRelTask.class, null) //����źͽ��׺� //TODO Q����Ҫȡ���������������AppNo�ͱ��潻�������TrdNo
		.doBusiness();
		return (String)brr.getReturnObj();
	}
	
	@RequestMapping(value = "/mdtAcctLastDet", method = { RequestMethod.POST, RequestMethod.GET })//URL=acct/manacct/acctrecord/submit
	public ResponseModel mdtAcctLastDet(RequestModel reqModel){
		String funcName = "��ѯ���һ��������ϸ";
		@SuppressWarnings("rawtypes")
		Map params = reqModel.getPrivateCtx();
		ResponseModel resModel = new ResponseModel(ResponseModel.State.SUCC, 
				funcName + "�ɹ�", mdtDetImpService.getMdtAcctLastDet(params.get("planId")+""));
		return resModel;
	}
}
