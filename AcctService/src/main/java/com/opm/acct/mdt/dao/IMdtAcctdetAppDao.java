package com.opm.acct.mdt.dao;

public interface IMdtAcctdetAppDao {
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
}
