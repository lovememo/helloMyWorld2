package com.opm.acct.mdt.act.rel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.acct.mdt.act.rel.service.IMdtAcctMdtActRelService;
import com.opm.acct.mdt.dao.IMdtActRelDao;
import com.opm.acct.mdt.model.MdtActRelModel;

@Service("MdtAcctMdtActRelService")
public class MdtAcctMdtActRelService implements IMdtAcctMdtActRelService {
	
	@Autowired
	private IMdtActRelDao mdtActRelDao;
	    
	
	/**
	 * 获取受托户关系信息
	 * @param planId
	 * @return
	 */
	@Override
	public MdtActRelModel getActRelInf (String planId) {
		//获取受托户关系信息
    	MdtActRelModel mdtActRelModel =  mdtActRelDao.getMdtActRel(planId);
    	return mdtActRelModel;
	}
	
	
}
