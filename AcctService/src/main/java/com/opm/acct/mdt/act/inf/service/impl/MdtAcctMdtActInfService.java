package com.opm.acct.mdt.act.inf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.acct.mdt.act.inf.service.IMdtAcctMdtActInfService;
import com.opm.acct.mdt.dao.impl.MdtActInfDao;
import com.opm.acct.mdt.dao.impl.MdtActRelDao;
import com.opm.acct.mdt.model.MdtActInfModel;
import com.opm.acct.mdt.model.MdtActRelModel;

@Service("MdtAcctMdtActInfService")
public class MdtAcctMdtActInfService implements IMdtAcctMdtActInfService {
	
	@Autowired
	private MdtActRelDao mdtActRelDao;
	    
	@Autowired
	private MdtActInfDao mdtActInfDao;
	
	/**
	 * 获取受托户信息
	 * @param tradeParam
	 * @return
	 */
	@Override
	public MdtActInfModel getActInf (String planId) {
		//获取受托户关系信息
    	MdtActRelModel mdtActRelModel =  mdtActRelDao.getMdtActRel(planId);
    	if (null == mdtActRelModel) {
    		return null;
    	}
    	//获取受托户信息
    	MdtActInfModel mdtActInfModel = mdtActInfDao.getMdtActInf(mdtActRelModel.getActId());
    	return mdtActInfModel;
	}
	
}
