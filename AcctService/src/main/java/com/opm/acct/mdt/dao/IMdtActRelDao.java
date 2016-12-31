package com.opm.acct.mdt.dao;

import com.opm.acct.mdt.model.MdtActRelModel;

public interface IMdtActRelDao {
	/**
	 * 获取受托户关系信息
	 * @param relId
	 * @return
	 */
	public MdtActRelModel getMdtActRel(String relId);
}
