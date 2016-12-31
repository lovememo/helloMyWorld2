package com.opm.acct.mdt.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.mdt.dao.IMdtActInfDao;
import com.opm.acct.mdt.mapper.MdtActInfMapper;
import com.opm.acct.mdt.model.MdtActInfModel;

@Repository("MdtActInfDao")
public class MdtActInfDao implements IMdtActInfDao {
	@Autowired
	private MdtActInfMapper mdtActInfMapper;

	@Override
	public MdtActInfModel getMdtActInf(String actId) {
		return mdtActInfMapper.getMdtActInf(actId);
	}


}
