package com.opm.acct.mdt.dao;

import java.util.List;

import com.opm.acct.mdt.model.MdtDetTrdModel;

public interface IMdtDetTrdDao {
	
	/**
	 * �������л���Ƽ��˿�Ŀ
	 * @param list
	 * @return
	 */
	public int insertMdtDetTrdForImp (List<MdtDetTrdModel> list, String trdNo);
	
	/**
	 * �����ϸ�ܶ�У��
	 * @param trdNo
	 * @return
	 */
	public String debitCreditAmtCheck(String trdNo);
	
	/**
	 * ��Ǽ��˺��ʽУ��
	 * @param trdNo
	 * @return
	 */
	public String debitTradeAmtCheck(String trdNo);
	
	/**
	 * ���л����������Ϣ��Ϣ
	 * @param appNo
	 * @param opDate
	 * @param planId
	 * @return
	 */
	public int insertDrawForImp (String trdNo, String opDate, String actId);
	
	/**
	 * ���л���ϸ�޸�-�������л���Ƽ��˽��ױ� 
	 * @param list
	 * @param trdNo
	 * @param actDate
	 * @return
	 */
	public int insertMdtDetTrdForMod (List<MdtDetTrdModel> list, String trdNo, String actDate);
	
	/**
	 * �ս������Ϣ��Ϣ
	 * @param trdNo
	 * @param opDate
	 * @param actId
	 * @param amt
	 * @return
	 */
	public int insertDrawForDayend (String trdNo, String opDate, 
			String actId, String amt);
	/**
	 * �������л���Ƽ��˽��ױ�
	 * @param list
	 * @return
	 */
	public int insertMdtDetTrd (List<MdtDetTrdModel> list);
	
	/**
	 * ��صڶ������л����й���Ѻ��йܷѵ�������
	 * @param actId
	 * @param date
	 * @param trdNo
	 * @return
	 */
	public int reverseMdtAndTrustFee (String actId,  String date,String trdNo);
	
	/**
	 * �����ĩ�����ת
	 * @param actId
	 * @param trdNo
	 * @param date
	 * @param beginDateDraw
	 * @return
	 */
	public int reverseMdtMonAcct (String actId,String trdNo, String date, String beginDateDraw);
	
	/**
	 * ����-�������л���Ƽ��˽��ױ�
	 * @param trdNo
	 * @param actId
	 * @return
	 */
	public int insertMdtDetTrdForReverAct (String trdNo, String actId);
	
	/**
	 * ���л�����-�������л���Ƽ��˽��ױ�
	 * @param list
	 * @param trdNo
	 * @return
	 */
	public String insertMdtDetTrdForAcctRecord (List<MdtDetTrdModel> list, String trdNo);
}
