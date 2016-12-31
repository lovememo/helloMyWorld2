package com.opm.acct.mdt.det.imp.service;

import java.util.List;
import java.util.Map;

import com.opm.acct.mdt.model.MdtAcctdetInfModel;
import com.opm.acct.mdt.model.MdtAcctdetTrdModel;
import com.opm.common.business.param.RetResult;

public interface IMdtDetImpService {
	
	/**
	 * ���л���ϸ��������׼��
	 * @param trdNo
	 * @param planId
	 * @param fileNo
	 * @return
	 */
	public RetResult doPrepare (String trdNo, String planId, String serialNo);
	/**
	 * ���л���ϸ������Ч
	 * @param trdNo
	 * @param planId
	 * @return
	 */
	public List<Object> doTrade (String trdNo, String planId);
	
	/**
	 * ��ȡ���л�������ϸ
	 * @param trdNo
	 * @return
	 */
	public List<MdtAcctdetTrdModel> qryMdtAcctdetTrdList (String trdNo);
	
	/**
	 * ��ѯ ����ǰ���һ����ϸ��Ϣ
	 * @return
	 */
	public List<Map<String,String>> getMdtAcctLastDet (String planId);
	
	/**
	 * ���л��޸� ���л���ϸ��ѯ
	 * @param actId
	 * @param opDate
	 * @param amt
	 * @param oppAcctName
	 * @param direct
	 * @param beginNum
	 * @param fetchNum
	 * @return
	 */
	List<MdtAcctdetInfModel> qryMdtAcctdetInfList(String planId, String opDate, String amt, String oppAcctName, String direct,
			int beginNum,  int fetchNum);
	
}
