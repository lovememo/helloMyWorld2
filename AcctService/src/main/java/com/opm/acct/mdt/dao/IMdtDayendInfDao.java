package com.opm.acct.mdt.dao;

import com.opm.acct.mdt.model.MdtDayendInfModel;

public interface IMdtDayendInfDao {
	/**
	 * �ж��Ƿ�����½�
	 * @param actId
	 * @param date
	 * @return
	 */
	public boolean isFinishMonend (String actId, String date);
	
	/**
	 * ��ȡ���л�������ս����� 
	 * @param actId
	 * @return
	 */
	public String getDayendDateFinished (String actId);
	
	/**
	 * �ж����������Ƿ����ս�
	 * @param actId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public boolean isFinishDayendInterval (String actId, String beginDate, String endDate);
	
	/**
	 * ��ȡ���л���ϸ���
	 * @param actId
	 * @param date
	 * @return
	 */
	public String getAmtByDate (String actId, String date);
	
	/**
	 * ��ȡ���һ���ѽ�������
	 * @param actId
	 * @return
	 */
	public String getLastDayendDate (String actId);
	
	/**
	 * ��ȡ�ս���Ϣ
	 * @param actId
	 * @param date
	 * @return
	 */
	public MdtDayendInfModel getMdtDayendInf (String actId, String date);
	/**
	 * ȡ���ս���������Ϣ
	 * @param actId
	 * @param date
	 * @param trdNo
	 * @return
	 */
	public String updForDayendCancel (String actId, String date, String trdNo);
	
	/**
	 * �ж��Ƿ�����ս�
	 * @param actId
	 * @param dayendDate
	 * @return
	 */
	public boolean isFinishDayend (String actId, String dayendDate);
	
}
