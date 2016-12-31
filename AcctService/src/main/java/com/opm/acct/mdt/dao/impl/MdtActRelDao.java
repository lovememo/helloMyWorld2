package com.opm.acct.mdt.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.mdt.dao.IMdtActRelDao;
import com.opm.acct.mdt.mapper.MdtActRelMapper;
import com.opm.acct.mdt.model.MdtActRelModel;

@Repository("MdtActRelDao")
public class MdtActRelDao implements IMdtActRelDao {
	@Autowired
	private MdtActRelMapper mdtActRelMapper;

	@Override
	public MdtActRelModel getMdtActRel(String relId) {
		return mdtActRelMapper.getMdtActRel(relId);
	}

}
