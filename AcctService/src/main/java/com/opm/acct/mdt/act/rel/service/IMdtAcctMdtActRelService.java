package com.opm.acct.mdt.act.rel.service;

import com.opm.acct.mdt.model.MdtActRelModel;

public interface IMdtAcctMdtActRelService {
	
	
	/**
	 * ��ȡ���л���ϵ��Ϣ
	 * @param tradeParam
	 * @return
	 */
	public MdtActRelModel getActRelInf (String planId);
	
	
	
	
}
