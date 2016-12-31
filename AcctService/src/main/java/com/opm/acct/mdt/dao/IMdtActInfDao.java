package com.opm.acct.mdt.dao;

import com.opm.acct.mdt.model.MdtActInfModel;

public interface IMdtActInfDao {
	/**
	 * 获取受托户信息
	 * @param actId
	 * @return
	 */
	public MdtActInfModel getMdtActInf(String actId);
}
