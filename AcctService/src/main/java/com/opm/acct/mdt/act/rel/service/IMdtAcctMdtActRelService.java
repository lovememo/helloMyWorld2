package com.opm.acct.mdt.act.rel.service;

import com.opm.acct.mdt.model.MdtActRelModel;

public interface IMdtAcctMdtActRelService {
	
	
	/**
	 * 获取受托户关系信息
	 * @param tradeParam
	 * @return
	 */
	public MdtActRelModel getActRelInf (String planId);
	
	
	
	
}
