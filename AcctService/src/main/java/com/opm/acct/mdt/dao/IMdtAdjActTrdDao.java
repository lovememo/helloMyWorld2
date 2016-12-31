package com.opm.acct.mdt.dao;

import java.util.List;

import com.opm.acct.mdt.model.MdtAdjActTrdModel;

public interface IMdtAdjActTrdDao {
	/**
	 * �������л����˽��ױ�
	 * @param list
	 * @return
	 */
	public int insertMdtAdjActTrd (List<MdtAdjActTrdModel> list);
	
	/**
	 * ��ѯ����������
	 * @param trdNo
	 * @return
	 */
	public String getMaxOpDate (String trdNo);
	
	/**
	 * ��ȡָ���ֶε����ֵ
	 * @param trdNo
	 * @param fieldName
	 * @return
	 */
	public String getMaxValueByFieldName (String trdNo,String fieldName);
	
	/**
	 * ��ѯ����������Ϣ
	 * @param trdNo
	 * @return
	 */
	public List<MdtAdjActTrdModel> getMdtAdjActTrd (String trdNo);
	
//	/**
//	 * ��ѯ������ߴ����
//	 * @param trdNo
//	 * @param debitCreditFlag
//	 * @return
//	 */
//	public String getDebitOrCreditAmt (String trdNo,String debitCreditFlag);
//	
//	/**
//	 * ��ѯ������Ȩ��
//	 * @param trdNo
//	 * @return
//	 */
//	public String getCreditAmt (String trdNo);
//	
//	/**
//	 * ��ѯ���˽�����Ϣ  �����ʲ� ,��ծ����
//	 * @param trdNo
//	 * @return
//	 */
//	public List<MdtAdjActTrdModel> getMdtAdjActTrdForCheck (String trdNo);
}
