package com.opm.acct.mdt.dayend.cancel.service;

import java.util.List;

import com.opm.acct.mdt.model.MdtActInfModel;

public interface IMdtDayendCancelService {
	
	/**
	 * ���л�ȡ���ս�����׼��
	 * @param planId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object> doPrepare (String planId, String beginDate, String endDate);
	/**
	 * ���л�ȡ���ս���Ч
	 * @param trdNo
	 * @param actInfo
	 * @return
	 */
	public List<Object> doTrade (String trdNo, MdtActInfModel actInfo);
	

	/**
	 * ��ȡ���һ���ѽ�������
	 * @param planId
	 * @return
	 */
	public String getLastDayendDate (String planId);
	
}
