package com.opm.acct.record.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.acct.common.remote.AppAudTask;
import com.opm.acct.common.remote.AppChannel;
import com.opm.acct.common.remote.AppModel;
import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.param.RetResult;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.acct.common.remote.AppTrdRelTask;
import com.opm.acct.mdt.model.MdtDetTrdModel;
import com.opm.acct.record.trade.MdtAcctRecordTrdPreTask;

@RestController
@RequestMapping("/manacct/acctrecord")
public class AcctRecordController {
	private static final Logger LOGGER =  LoggerFactory.getLogger(AcctRecordController.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/submit", method = { RequestMethod.POST, RequestMethod.GET })//URL=acct/manacct/acctrecord/submit
	public ResponseModel submit(RequestModel reqModel){
		String funcName = "���л���������׼��";
		Map params = reqModel.getPrivateCtx();
		if (null == params ||  !params.containsKey("list")) {
			return new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��, ��������", null);
		}
		//���л���Ƽ��˽�������
		List<Map> tList = (List<Map>)params.get("list");
		if (null == tList || 0 == tList.size()) {
			return new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��, ��������", null);
		}
		try {
			List<MdtDetTrdModel> mdtDetTrdList = new ArrayList<MdtDetTrdModel>();
			MdtDetTrdModel mdtDetTrdModel = null;
			String retStr= null;
			Map tMap = null;
			for (int i = 0; i < tList.size(); i++) {
				tMap = tList.get(i);
				mdtDetTrdModel = new MdtDetTrdModel();
				retStr = mdtDetTrdModel.checkAndSetParams(tMap, mdtDetTrdModel);
				if (StringUtils.isNotEmpty(retStr)) {
					return new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��,"+retStr+" ����Ϊ��", null);
				}
				mdtDetTrdList.add(mdtDetTrdModel);
			}
			
			//�ύ����
			reqModel.getPrivateCtx().put("appType", "acctrecord");
			reqModel.getPrivateCtx().put("opType", "submit");
			reqModel.getPrivateCtx().put("list", mdtDetTrdList);
			CommonBusiness netValueBusiness = new CommonBusiness();
			RetResult brr = netValueBusiness
				//.register(AppAudTask.class, reqModel.getPrivateCtx()) //�ύ���� //�������
				.register(MdtAcctRecordTrdPreTask.class, reqModel.getPrivateCtx()) //����������ϸ
				//.register(AppTrdRelTask.class,  reqModel.getPrivateCtx()) //����źͽ��׺� 
			.doBusiness();
			if (!brr.getResult()) {
				return  new ResponseModel(ResponseModel.State.FAIL, brr.getReason(), null);
			}
			return new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", brr.getReturnObj());
		} catch(Exception e) {
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
		//������װ
		//TrdModel trdModel = new TrdModel(params);
		
		
		CommonBusiness netValueBusiness = new CommonBusiness();
		RetResult brr = netValueBusiness
			.register(AppAudTask.class, appModel, AppChannel.class) //�������� //������� //��������״̬дChannel 
			//.register(MdtAcctRecordTrdDoTask.class, trdModel.getTrdNo()) //ͨ�������+�������� = ȡ������ˮ�ţ���TrdNo��
			//.register(TrdTask.class, trdModel) //���½������� 
			.register(AppTrdRelTask.class, null) //����źͽ��׺� 
			.doBusiness();
		return (String)brr.getReturnObj();
	}
}
