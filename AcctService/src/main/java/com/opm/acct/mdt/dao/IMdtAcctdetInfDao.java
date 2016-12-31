package com.opm.acct.mdt.dao;

import java.util.List;
import java.util.Map;

import com.opm.acct.mdt.model.MdtAcctdetInfModel;

public interface IMdtAcctdetInfDao {
	/**
	 * ��ȡ��Ч�����л��ʽ�������Ϣ��
	 * @param actId
	 * @param validFlag
	 * @return
	 */
	public int getMdtAcctdetInfCount (String actId, String validFlag);
	
	/**
	 * �������л��ʽ�������Ϣ
	 * @param trdNo
	 * @return
	 */
	public String insertMdtAcctdetInf (String trdNo);
	
	/**
	 * ��ȡ���л������Ϣ��ʼ����
	 * @param actId
	 * @return
	 */
	public String getBeginDateForImpDraw (String actId);
	

	/**
	 * ��ѯ ����ǰ���һ����ϸ��Ϣ
	 * @return
	 */
	public List<Map<String,String>> getMdtAcctLastDet (String actId);
	
	/**
	 * ���л��޸�  ���л���ϸ��ѯ
	 * @param actId
	 * @param opDate
	 * @param amt
	 * @param oppAcctName
	 * @param direct
	 * @param beginNum
	 * @param fetchNum
	 * @return
	 */
	List<MdtAcctdetInfModel> qryMdtAcctdetInfList(String actId, String opDate,String amt, String oppAcctName,
			String direct, int beginNum, int fetchNum);
	
	/**
	 * ������ˮ�Ż�ȡ���л��ʽ�������Ϣ
	 * @param seqId
	 * @return
	 */
	public MdtAcctdetInfModel getMdtAcctdetInfBySeqId (String seqId);
	
	/**
	 * �������л��ʽ�������Ϣ
	 * @param trdNo
	 * @param seqId
	 * @return
	 */
	public String updMdtAcctdetInf (String trdNo);
	
	/**
	 * �ж��Ƿ������Ϣ,���������������������·����һ�췶Χ��
	 * @param actId
	 * @param date
	 * @return
	 */
	public boolean isCalDrawBtwDateAndLastest (String actId, String date);
	
	/**
	 * ��ȡ��Ϣ��ʼ����
	 * @param actId
	 * @param date
	 * @return
	 */
	public String getBeginDateCalDraw (String actId, String date,String actDate);
	
	/**
	 * ��ȡ���л����һ����ϸ���
	 * @param actId
	 * @param date
	 * @return
	 */
	public String getActLastAmtBefDate (String actId, String date);
	
	/**
	 * ���»�����ʲ���ֵ
	 * @param actId
	 * @param date
	 * @param isFinished
	 * @param trdNo
	 * @param amt
	 * @return
	 */
	public String updOrInsertAmt ( String actId, String date, String isFinished,
			String trdNo, String amt);
	
	/**
	 * ��ȡ���л���������֮����ʽ�������Ϣ
	 * @param actId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<MdtAcctdetInfModel> getInfBetween2Date (String actId,String beginDate, String endDate);
}
