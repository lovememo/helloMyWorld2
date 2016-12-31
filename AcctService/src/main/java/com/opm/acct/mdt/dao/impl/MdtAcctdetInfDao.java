package com.opm.acct.mdt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.mdt.dao.IMdtAcctdetInfDao;
import com.opm.acct.mdt.mapper.MdtAcctdetInfMapper;
import com.opm.acct.mdt.model.MdtAcctdetInfModel;

@Repository("MdtAcctdetInfDao")
public class MdtAcctdetInfDao implements IMdtAcctdetInfDao {
	@Autowired
	private MdtAcctdetInfMapper mdtAcctdetInfMapper;

	@Override
	public int getMdtAcctdetInfCount(String actId, String validFlag) {
		return mdtAcctdetInfMapper.getMdtAcctDetInfCount(actId, validFlag);
	}

	@Override
	public String insertMdtAcctdetInf(String trdNo) {
		return mdtAcctdetInfMapper.insertMdtAcctdetInf(trdNo);
	}

	@Override
	public String getBeginDateForImpDraw(String actId) {
		return mdtAcctdetInfMapper.getBeginDateForImpDraw(actId);
	}

	@Override
	public List<Map<String,String>> getMdtAcctLastDet(String actId) {
		return mdtAcctdetInfMapper.getMdtAcctLastDet(actId);
	}

	@Override
	public List<MdtAcctdetInfModel> qryMdtAcctdetInfList(String actId, String opDate, String amt, String oppAcctName,
			String direct, int beginNum, int fetchNum) {
		return mdtAcctdetInfMapper.qryMdtAcctdetInfList(actId, opDate, amt, oppAcctName, direct, beginNum, fetchNum);
	}

	@Override
	public MdtAcctdetInfModel getMdtAcctdetInfBySeqId(String seqId) {
		return mdtAcctdetInfMapper.getMdtAcctdetInfBySeqId(seqId);
	}

	@Override
	public String updMdtAcctdetInf(String trdNo) {
		return mdtAcctdetInfMapper.updMdtAcctdetInf(trdNo);
	}

	@Override
	public boolean isCalDrawBtwDateAndLastest(String actId, String endDate) {
		if (0 != mdtAcctdetInfMapper.isCalDrawBtwDateAndLastest(actId, endDate)) {
			return true;
		}
		return false;
	}

	@Override
	public String getBeginDateCalDraw(String actId, String endDate, String actDate) {
		return mdtAcctdetInfMapper.getBeginDateCalDraw(actId, endDate, actDate);
	}

	@Override
	public String getActLastAmtBefDate(String actId, String date) {
		return mdtAcctdetInfMapper.getActLastAmtBefDate(actId, date);
	}

	@Override
	public String updOrInsertAmt(String actId, String date, String isFinished, String trdNo, String amt) {
		return mdtAcctdetInfMapper.updOrInsertAmt(actId, date, isFinished, trdNo, amt);
	}

	@Override
	public List<MdtAcctdetInfModel> getInfBetween2Date(String actId, String beginDate, String endDate) {
		return mdtAcctdetInfMapper.getInfBetween2Date(actId, beginDate, endDate);
	}

}
