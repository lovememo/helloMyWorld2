package com.opm.acct.mdt.dao;

import com.opm.acct.mdt.model.MdtActInfModel;

public interface IMdtActInfDao {
	/**
	 * ��ȡ���л���Ϣ
	 * @param actId
	 * @return
	 */
	public MdtActInfModel getMdtActInf(String actId);
}
