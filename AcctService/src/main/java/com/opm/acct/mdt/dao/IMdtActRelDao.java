package com.opm.acct.mdt.dao;

import com.opm.acct.mdt.model.MdtActRelModel;

public interface IMdtActRelDao {
	/**
	 * ��ȡ���л���ϵ��Ϣ
	 * @param relId
	 * @return
	 */
	public MdtActRelModel getMdtActRel(String relId);
}
