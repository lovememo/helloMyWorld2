package com.opm.acct.mdt.dayend.service;

import java.util.List;

import com.opm.acct.mdt.model.MdtActInfModel;
import com.opm.acct.mdt.model.MdtDayendTrdModel;
import com.opm.common.business.param.RetResult;

public interface IMdtDayendService {
	
	/**
	 * ���л��ս�����׼��
	 * @param planId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object> doPrepare (String planId, String beginDate, String endDate);
	/**
	 * ���л��ս���Ч
	 * @param trdNo
	 * @param actInfo
	 * @return
	 */
	public List<Object> doTrade (String trdNo, MdtActInfModel actInfo);
	
	/**
	 * ��ȡ��һ��δ�ս�����
	 * @param planId
	 * @return
	 */
	public String getFirstDayNoEnd (String planId);
	/**
	 * ���л��սᣨȡ���սᣩ�����ѯ
	 * @param trdNo
	 * @return
	 */
	public MdtDayendTrdModel qryMdtDayendApp (String trdNo);
	
	/**
	 * �ж��Ƿ�����½�
	 * @param actId
	 * @param date
	 * @return
	 */
	public RetResult isFinishMonend (String planId, String date);
	
}
