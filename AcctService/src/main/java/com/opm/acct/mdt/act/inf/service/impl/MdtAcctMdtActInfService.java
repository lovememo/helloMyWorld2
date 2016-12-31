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
	 * ��ȡ���л���Ϣ
	 * @param tradeParam
	 * @return
	 */
	@Override
	public MdtActInfModel getActInf (String planId) {
		//��ȡ���л���ϵ��Ϣ
    	MdtActRelModel mdtActRelModel =  mdtActRelDao.getMdtActRel(planId);
    	if (null == mdtActRelModel) {
    		return null;
    	}
    	//��ȡ���л���Ϣ
    	MdtActInfModel mdtActInfModel = mdtActInfDao.getMdtActInf(mdtActRelModel.getActId());
    	return mdtActInfModel;
	}
	
}
