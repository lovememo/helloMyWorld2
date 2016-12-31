package com.opm.acct.mdt.act.inf.service;

import com.opm.acct.mdt.model.MdtActInfModel;

public interface IMdtAcctMdtActInfService {
	
	/**
	 * 获取受托户信息
	 * @param planId
	 * @return
	 */
	public MdtActInfModel getActInf (String planId);
	
	
	
	
	
}
