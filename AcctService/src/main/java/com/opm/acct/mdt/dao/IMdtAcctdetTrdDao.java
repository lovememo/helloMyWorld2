package com.opm.acct.mdt.dao;

import java.util.List;

import com.opm.acct.mdt.model.MdtAcctdetTrdModel;

public interface IMdtAcctdetTrdDao {
	/**
	 * ���л���ϸ���뽻��У��
	 * @param trdNo
	 * @param actId
	 * @return
	 */
	public String checkMdtDetImpTrade (String trdNo, String actId);
	
	/**
	 * ����trdNo��ѯ���л���ϸ��Ϣ
	 * @param trdNo
	 * @return
	 */
	public List<MdtAcctdetTrdModel> qryMdtAcctdetTrdListByTrdNo(String trdNo);
	
	/**
	 * ��ȡ���л���Ϣ�����еĽ�������
	 * @param trdNo
	 * @return
	 */
	public String getMdtDailyIntDrawEndDate (String trdNo);
	
	/**
	 * ��ȡ���ε�����С��������
	 * @param appNo
	 * @return
	 */
	public String getMinOpDate (String trdNo);
	
	/**
	 * ���뵥��������Ϣ
	 * @param mdtAcctdetTrdModel
	 * @return
	 */
	public int insertMdtAcctdetTrd (MdtAcctdetTrdModel mdtAcctdetTrdModel);
	
	/**
	 *  ���л��޸� ���²����ֶ�
	 * @param trdNo
	 * @param seqId
	 * @return
	 */
	public int updMdtAcctdetTrdForMod (String trdNo,  String seqId);
	
	/**
	 * ������ˮ�Ų�ѯ������Ϣ
	 * @param seqId
	 * @return
	 */
	public MdtAcctdetTrdModel getMdtAcctdetTrdBySeqId (String seqId);
	
	/**
	 * �������л����ױ�
	 * @param list
	 * @return
	 */
	public int insertMdtAcctdetTrdList (String serialNo, String actId,String trdNo);
	
	/**
	 * ��ȡУ�鲻�ɹ���¼��
	 * @param trdNo
	 * @param seqId
	 * @return
	 */
	public int getCountsByCheckFlag (String trdNo, String checkFlag);
}
