package com.opm.acct.mdt.dao;

import org.apache.ibatis.annotations.Param;

public interface IMdtActDao {
	/**
	 * �������л��������˱�
	 * @return
	 */
	public String insertMdtAct (@Param("actId") String actId);
}
