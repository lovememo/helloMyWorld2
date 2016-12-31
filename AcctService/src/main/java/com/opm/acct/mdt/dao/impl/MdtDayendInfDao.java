package com.opm.acct.mdt.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.mdt.dao.IMdtDayendInfDao;
import com.opm.acct.mdt.mapper.MdtDayendInfMapper;
import com.opm.acct.mdt.model.MdtDayendInfModel;

@Repository("MdtDayendInfDao")
public class MdtDayendInfDao implements IMdtDayendInfDao {
	@Autowired
	private MdtDayendInfMapper mdtDayendInfMapper;

	@Override
	public boolean isFinishMonend(String actId, String date) {
		int count = mdtDayendInfMapper.isFinishMonend(actId, date);
		if (count != 0) {
			return true;
		}
		return false;
	}

	@Override
	public String getDayendDateFinished(String actId) {
		return mdtDayendInfMapper.getDayendDateFinished(actId);
	}

	@Override
	public boolean isFinishDayendInterval(String actId, String beginDate, String endDate) {
		int count = mdtDayendInfMapper.isFinishDayendInterval(actId, beginDate, endDate);
		if (count != 0) {
			return true;
		}
		return false;
	}

	@Override
	public String getAmtByDate(String actId, String date) {
		return mdtDayendInfMapper.getAmtByDate(actId, date);
	}

	@Override
	public String getLastDayendDate(String actId) {
		String date = mdtDayendInfMapper.getLastDayendDate(actId);
		if (StringUtils.isEmpty(date)) {
			return "";
		}
		return date;
	}

	@Override
	public MdtDayendInfModel getMdtDayendInf(String actId, String date) {
		return mdtDayendInfMapper.getMdtDayendInf(actId, date);
	}

	@Override
	public String updForDayendCancel(String actId, String date, String trdNo) {
		return mdtDayendInfMapper.updForDayendCancel(actId, date, trdNo);
	}

	@Override
	public boolean isFinishDayend(String actId, String dayendDate) {
		int count = mdtDayendInfMapper.isFinishDayend(actId, dayendDate);
		if (0 != count) {
			return true;
		}
		return false;
	}

}
