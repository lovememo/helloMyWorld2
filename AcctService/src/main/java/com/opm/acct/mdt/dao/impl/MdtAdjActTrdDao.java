package com.opm.acct.mdt.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.mdt.dao.IMdtAdjActTrdDao;
import com.opm.acct.mdt.mapper.MdtAdjActTrdMapper;
import com.opm.acct.mdt.model.MdtAdjActTrdModel;

@Repository("MdtAdjActTrdDao")
public class MdtAdjActTrdDao implements IMdtAdjActTrdDao {
	@Autowired
	private MdtAdjActTrdMapper mdtAdjActTrdMapper;

	@Override
	public int insertMdtAdjActTrd(List<MdtAdjActTrdModel> list) {
		return mdtAdjActTrdMapper.insertMdtAdjActTrd(list);
	}

	@Override
	public String getMaxOpDate(String trdNo) {
		return mdtAdjActTrdMapper.getMaxOpDate(trdNo);
	}

	@Override
	public String getMaxValueByFieldName(String trdNo, String fieldName) {
		return mdtAdjActTrdMapper.getMaxValueByFieldName(trdNo, fieldName);
	}

	@Override
	public List<MdtAdjActTrdModel> getMdtAdjActTrd(String trdNo) {
		return mdtAdjActTrdMapper.getMdtAdjActTrd(trdNo);
	}

//	@Override
//	public String getDebitOrCreditAmt(String trdNo, String debitCreditFlag) {
//		return mdtAdjActTrdMapper.getDebitOrCreditAmt(trdNo, debitCreditFlag);
//	}
//
//	@Override
//	public String getCreditAmt(String trdNo) {
//		return mdtAdjActTrdMapper.getCreditAmt(trdNo);
//	}
//
//	@Override
//	public List<MdtAdjActTrdModel> getMdtAdjActTrdForCheck(String trdNo) {
//		return mdtAdjActTrdMapper.getMdtAdjActTrdForCheck(trdNo);
//	}

}
