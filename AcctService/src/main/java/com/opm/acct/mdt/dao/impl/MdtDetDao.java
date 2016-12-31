package com.opm.acct.mdt.dao.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.common.CommonConstant;
import com.opm.acct.mdt.dao.IMdtDetDao;
import com.opm.acct.mdt.mapper.MdtDetMapper;
import com.opm.acct.mdt.model.MdtDetModel;

@Repository("MdtDetDao")
public class MdtDetDao implements IMdtDetDao {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtDetDao.class);
	
	@Autowired
	private MdtDetMapper mdtDetMapper;

	@Override
	public String insertMdtDet(String trdNo) {
		try {
			mdtDetMapper.insertMdtDet(trdNo);
			return CommonConstant.RETMSG_SUCCESS;
		} catch (Exception e) {
			LOGGER.error("入受托户会计明细账表异常", e);
			return CommonConstant.RETMSG_ERROR;
		}
	}

	@Override
	public String getAmtByDate(String actId, String date) {
		return mdtDetMapper.getAmtByDate(actId, date);
	}

	@Override
	public List<MdtDetModel> qryMdtDetList(String actId, String date, String amt, String beginNum, String fetchNum) {
		return mdtDetMapper.qryMdtDetList(actId, date, amt, beginNum, fetchNum);
	}

	@Override
	public int getCountForReverseAct(String trdNo) {
		return mdtDetMapper.getCountForReverseAct(trdNo);
	}

	@Override
	public String getMaxOpDateForReverseAct(String trdNo) {
		return mdtDetMapper.getMaxOpDateForReverseAct(trdNo);
	}

	@Override
	public List<MdtDetModel> qryReverseActApp(String trdNo) {
		return mdtDetMapper.qryReverseActApp(trdNo);
	}



}
