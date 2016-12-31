package com.opm.acct.mdt.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.mdt.dao.IMdtDayendTrdDao;
import com.opm.acct.mdt.mapper.MdtDayendTrdMapper;
import com.opm.acct.mdt.model.MdtDayendTrdModel;

@Repository("MdtDayendTrdDao")
public class MdtDayendTrdDao implements IMdtDayendTrdDao {
	@Autowired
	private MdtDayendTrdMapper mdtDayendTrdMapper;

	@Override
	public int insertMdtDayendTrd(MdtDayendTrdModel mdtDayendTrdModel) {
		return mdtDayendTrdMapper.insertMdtDayendTrd(mdtDayendTrdModel);
	}

	@Override
	public MdtDayendTrdModel getMdtDayendTrd(String trdNo) {
		return mdtDayendTrdMapper.getMdtDayendTrd(trdNo);
	}


}
