package com.opm.acct.mdt.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.mdt.dao.IMdtAcctdetHisDao;
import com.opm.acct.mdt.mapper.MdtAcctdetHisMapper;

@Repository("MdtAcctdetHisDao")
public class MdtAcctdetHisDao implements IMdtAcctdetHisDao {
	@Autowired
	private MdtAcctdetHisMapper mdtAcctdetHisMapper;

	@Override
	public String insertMdtAcctDetHis(String trdNo) {
		return mdtAcctdetHisMapper.insertMdtAcctDetHis(trdNo);
	}


}
