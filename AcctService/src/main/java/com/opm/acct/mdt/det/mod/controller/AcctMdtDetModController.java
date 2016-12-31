package com.opm.acct.mdt.det.mod.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.acct.common.remote.AppChannel;
import com.opm.acct.common.remote.AppModel;
import com.opm.acct.common.remote.AppAudTask;
import com.opm.acct.mdt.det.imp.service.IMdtDetImpService;
import com.opm.acct.mdt.det.imp.trade.MdtDetImpTrdDoTask;
import com.opm.acct.mdt.det.imp.trade.MdtDetImpTrdPreTask;
import com.opm.acct.mdt.model.MdtAcctdetInfModel;
import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.param.RetResult;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.common.model.ResponseModel.State;
import com.opm.acct.common.remote.AppTrdRelTask;

@RestController
@RequestMapping("/manacct/acctmdtdetmod")
public class AcctMdtDetModController {
	@Autowired
	private IMdtDetImpService mdtDetImpService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/qryMdtAcctdetInfList",method = { RequestMethod.POST, RequestMethod.GET })//URL=acct/manacct/acctrecord/submit
	public ResponseModel qryMdtAcctdetInf(RequestModel reqModel){
		Map params = reqModel.getPrivateCtx();
		String planId = (String) params.get("planId");
		String opDate = (String) params.get("opDate");
		String amt = (String) params.get("amt");
		String oppAcctName = (String) params.get("oppAcctName");
		String direct = (String) params.get("direct");
		String beginNum = (String) params.get("beginNum");
		String fetchNum = (String) params.get("fetchNum");
		List<MdtAcctdetInfModel> ret =  mdtDetImpService.qryMdtAcctdetInfList(planId, opDate, amt, oppAcctName, 
				direct, Integer.parseInt(beginNum), Integer.parseInt(fetchNum));
		ResponseModel responseModel = new ResponseModel(State.SUCC, "�ɹ�", ret);
		return responseModel;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/sumbit",method = { RequestMethod.POST, RequestMethod.GET })//URL=acct/manacct/acctmdtdetimp/submit
	public String submit(Map params){
		//�������������װ
		AppModel appModel = new AppModel();
		appModel.setAppType("034401");//���л���ϸ����
		appModel.setAppState("14");
		//module=5402
		appModel.setBakFlag("1");
		
//		//�������������װ
//		TrdModel trdModel = new TrdModel(params);
//		trdModel.setTrdNo("");
//		trdModel.setTrdType("034401");
//		trdModel.setTrdState("");
		
		//dataservice�ļ��ϴ� ���� ��� opm_mdt_acctdet_trd���л���ϸ���ױ� ����trdNo
		String fileNo = "";//dataservice����
		String appNo = "";//dataservice����
		HashMap map = new HashMap();
		map.put("fileNo", fileNo);
		map.put("appNo", appNo);
		map.put("trdType", "034401");
		List<HashMap> trdList = new ArrayList<HashMap>();
		trdList.add(map);
		//�ύ����
		CommonBusiness mdtDetImpBusiness = new CommonBusiness();
		RetResult brr = mdtDetImpBusiness
			.register(AppAudTask.class, appModel) //�ύ���� //�������
//			.register(TrdTask.class, trdModel) //���潻������ //TODO Q�����׹���Trade����ʵ�֣�
			.register(MdtDetImpTrdPreTask.class, fileNo) //���л���ϸ��������׼��  У��  ����źͽ��׺� //TODO Q����Ҫȡ���������������AppNo�ͱ��潻�������TrdNo
			.register(AppTrdRelTask.class, trdList) //����źͽ��׺� //TODO Q����Ҫȡ���������������AppNo�ͱ��潻�������TrdNo
		.doBusiness();
		return (String)brr.getReturnObj();
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/audit",method = { RequestMethod.POST, RequestMethod.GET })
	public String audit(Map params){
		//������װ
		AppModel appModel = new AppModel(params);
		//������װ
//		TrdModel trdModel = new TrdModel(params);
		
		CommonBusiness netValueBusiness = new CommonBusiness();//TODO Q���ع�ʱ��Ҫ����ÿһTrade��Context����Ϊͬһ��keyֵ�����ڲ�ͬ��Trade�з����仯
		RetResult brr = netValueBusiness
			.register(AppAudTask.class, appModel, AppChannel.class) //�������� //������� //��������״̬дChannel //TODO Q��Channel����Tradeִ����ɺ�ִ�У�
			.register(MdtDetImpTrdDoTask.class, null) //������Ϣ�� //TODO Q���ͱ��潻����ϸ�ϲ�Ϊһ��Trade(һ������trd�� + һ��inf��)
//			.register(TrdTask.class, trdModel) //���½������� //TODO Q��������������Trade����ʵ�֣�
			//.register(AppTrdRelTask.class, null) //����źͽ��׺� //TODO Q����Ҫȡ���������������AppNo�ͱ��潻�������TrdNo
		.doBusiness();
		return (String)brr.getReturnObj();
	}
}
