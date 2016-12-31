package com.opm.acct.mdt.dao;

import java.util.List;

import com.opm.acct.mdt.model.MdtDetModel;

public interface IMdtDetDao {
	/**
	 * �������л������ϸ�˱�
	 * @return
	 */
	public String insertMdtDet (String trdNo);
	
	/**
	 * �������ڻ�ȡ���
	 * @param actId
	 * @param date
	 * @return
	 */
	public String  getAmtByDate (String actId, String date);
	
	/**
	 * ���л��������� ���л������������ϸ��ѯ
	 * @param actId
	 * @param date
	 * @param beginNum
	 * @param fetchNum
	 * @return
	 */
	public List<MdtDetModel> qryMdtDetList (String actId,String date,
			String amt,String beginNum,String fetchNum);
	
	/**
	 * �����ս��ж�
	 * @param trdNo
	 * @return
	 */
	public int  getCountForReverseAct (String trdNo);
	
	/**
	 * ���˻�ȡ����������
	 * @param trdNo
	 * @return
	 */
	public String  getMaxOpDateForReverseAct (String trdNo);
	
	/**
	 * ���л��������븴��  ���������ѯ
	 * @param actId
	 * @return
	 */
	public List<MdtDetModel> qryReverseActApp (String trdNo);
}
