package com.opm.acct.record.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opm.acct.common.CommonConstant;
import com.opm.acct.common.remote.IPlanClient;
import com.opm.acct.mdt.dao.IMdtActDao;
import com.opm.acct.mdt.dao.IMdtActRelDao;
import com.opm.acct.mdt.dao.IMdtDetDao;
import com.opm.acct.mdt.dao.IMdtDetTrdDao;
import com.opm.acct.mdt.model.MdtActRelModel;
import com.opm.acct.mdt.model.MdtDetTrdModel;
import com.opm.acct.mdt.model.PlanBasicInfoModel;
import com.opm.acct.record.service.IMdtAcctRecordService;
import com.opm.common.business.param.RetResult;

@Repository("MdtAcctRecordService")
public class MdtAcctRecordService implements IMdtAcctRecordService {
	
    @Autowired
   	private IMdtActRelDao mdtActRelDao;
	    
	@Autowired
	private IMdtDetTrdDao mdtDetTrdDao;
	
	@Autowired
	private IMdtDetDao mdtDetDao;
	
	@Autowired
	private IMdtActDao mdtActDao;
	
	 @Autowired
	 private IPlanClient iPlanClient;
	    
	public RetResult doPrepare(List<MdtDetTrdModel> list, String trdNo, String planId) {
		RetResult rr = new RetResult();
		Map<String, Object> contextObj = new HashMap<>();
		contextObj.put("trdNo", trdNo);
		rr.setContextObj(contextObj);
		rr.setReturnObj(contextObj);
		
		//�����л���Ƽ��˽��ױ�
    	String retStr = mdtDetTrdDao.insertMdtDetTrdForAcctRecord(list, trdNo);
    	if (CommonConstant.RETMSG_ERROR.equals(retStr)) {
    		return new RetResult(false,"���л������������ʧ��",null);
    	}
    	
    	//У��
		RetResult checkResult =  checkBeforeMdtAcctRecord (trdNo, planId);
		rr.setResult(checkResult.getResult());
		rr.setReason(checkResult.getReason());
		return rr;
	}

	/**
	 * ���л�����ǰУ��
	 * @return
	 */
	public RetResult checkBeforeMdtAcctRecord (String trdNo, String planId) {
    	//��ȡ���л������־ actFlag;
//		PlanBasicInfoModel planModel = iPlanClient.getPlanInfByPlanId("planId");
//		if (null == planModel) {
//			return new RetResult(false, "�޼ƻ���Ϣ",null);
//		}
//    	String actFlag = planModel.getAccountingFlag();
//		//���л������־У��
//		if (CommonConstant.FLAG_N.equals(actFlag)) {
//			return new RetResult(false, "�ƻ�����:planId="+planId+",�ƻ������ϲ��������л�����",null);
//		}
		//3.���У�� (1) �����ϸ�ܶ�У��
		String checkResult ;//= mdtDetTrdDao.debitCreditAmtCheck(trdNo);
//		if (CommonConstant.RETMSG_ERROR.equals(checkResult)) {
//			return new RetResult(false, "�ƻ�����:planId="+planId+",�����ϸ�ܶ���",null);
//		}
		//3.���У�� (2)�Ƿ�������˻�ƺ��ʽ
		checkResult = mdtDetTrdDao.debitTradeAmtCheck(trdNo);
		if (CommonConstant.RETMSG_ERROR.equals(checkResult)) {
			return new RetResult(false, "�ƻ�����:planId="+planId+",��������˻�ƺ��ʽ",null);
		}
		return new RetResult(true, "���л�����У��ͨ��",null);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public RetResult doTrade(String trdNo, String planId) {
		//����ǰ�ٴ�У�� �жϷ���????????????
//		RetResult checkResult =   checkBeforeMdtAcctRecord (trdNo, planId);
//		if (!checkResult.getResult()) {
//			return new RetResult(checkResult.getResult(), checkResult.getReason(),null);
//		}
		//��ȡ���л���ϵ��Ϣ
    	MdtActRelModel mdtActRelModel =  (MdtActRelModel) mdtActRelDao.getMdtActRel(planId);
    	if (null == mdtActRelModel) {
    		return new RetResult(false, "�ƻ�����:planId="+planId+",�����л���Ϣ",null);
    	}
		//�����л������ϸ�˱�???????�ع�
		String retStr = mdtDetDao.insertMdtDet(trdNo);
		if (CommonConstant.RETMSG_ERROR.equals(retStr)) {
			return new RetResult(false, "���л������ϸ�˼���ʧ��",null);
		}
		//�����л��������˱�
		retStr = mdtActDao.insertMdtAct(mdtActRelModel.getActId());
		if (CommonConstant.RETMSG_ERROR.equals(retStr)) {
			return new RetResult(false, "���л��������˼���ʧ��",null);
		}
		return new RetResult(true, "���л����˳ɹ�",null);
	}
}
