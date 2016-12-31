package com.opm.acct.mdt.dao;

public interface IMdtAcctdetAppDao {
	/**
	 * 获取受托户计息区间中的结束日期
	 * @param trdNo
	 * @return
	 */
	public String getMdtDailyIntDrawEndDate (String trdNo);
	
	/**
	 * 获取本次导入最小操作日期
	 * @param appNo
	 * @return
	 */
	public String getMinOpDate (String trdNo);
}
