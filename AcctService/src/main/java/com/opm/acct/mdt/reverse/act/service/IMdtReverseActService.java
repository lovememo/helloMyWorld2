package com.opm.acct.mdt.reverse.act.service;

import java.util.List;

import com.opm.acct.mdt.model.MdtDetModel;

public interface IMdtReverseActService {
	
	/**
	 * ���л�����������׼��
	 * @param planId
	 * @param seqIdStr
	 * @return
	 */
	public List<Object> doPrepare (String planId, String seqIdStr);
	/**
	 * ���л�������Ч
	 * @param trdNo
	 * @param actInfo
	 * @return
	 */
	public List<Object> doTrade (String trdNo, String planId);
	
	/**
	 * ���л��������� ���л������������ϸ��ѯ
	 * @param planId
	 * @param date
	 * @param beginNum
	 * @param fetchNum
	 * @return
	 */
	public List<MdtDetModel> qryMdtDetList (String planId,String date,
			String amt,String beginNum,String fetchNum);
	/**
	 * ���л��������븴��  ���������ѯ
	 * @param actId
	 * @return
	 */
	public List<MdtDetModel> qryReverseActApp (String trdNo);
}
