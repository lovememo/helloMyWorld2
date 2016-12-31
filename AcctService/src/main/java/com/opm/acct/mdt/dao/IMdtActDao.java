package com.opm.acct.mdt.dao;

import org.apache.ibatis.annotations.Param;

public interface IMdtActDao {
	/**
	 * 插入受托户会计余额账表
	 * @return
	 */
	public String insertMdtAct (@Param("actId") String actId);
}
