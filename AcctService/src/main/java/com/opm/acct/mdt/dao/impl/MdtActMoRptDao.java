package com.opm.acct.mdt.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.common.CommonUtils;
import com.opm.acct.common.CurrencyUtils;
import com.opm.acct.mdt.dao.IMdtActMoRptDao;
import com.opm.acct.mdt.mapper.MdtActMoRptMapper;
import com.opm.acct.mdt.mapper.MdtDetMapper;
import com.opm.acct.mdt.model.MdtActInfModel;
import com.opm.acct.mdt.model.MdtActMoRptModel;

@Repository("MdtActMoRptDao")
public class MdtActMoRptDao implements IMdtActMoRptDao {
	@Autowired
	private MdtActMoRptMapper mdtActMoRptMapper;
	
	@Autowired
	private MdtDetMapper mdtDetMapper;

	@Override
	public String getAmtByDate(MdtActInfModel actInfo, String mdtSubjectType, String date) {
		//获取date上一个月的最后一天日期
		String lastDate = CommonUtils.getLastDateOfMonth(CommonUtils.addMonths(date, -1));
		MdtActMoRptModel mdtActMoRpt = mdtActMoRptMapper.getMdtActMoRpt(actInfo.getActId(), lastDate, mdtSubjectType);
		//未做过月结，取受托户核算起始日期前一天
		if (null == mdtActMoRpt) {
			lastDate = CommonUtils.addDays(actInfo.getActDate(), -1);
		}
		//获取上期余额
		String lastAmt = mdtActMoRptMapper.getAmt(actInfo.getActId(), lastDate, mdtSubjectType);
		//获取当期交易金额
		String preAmt = mdtDetMapper.getPreAmt(actInfo.getActId(), lastDate, date, mdtSubjectType);
		
		return CurrencyUtils.sumDouble(lastAmt, preAmt)+"";
	}

	@Override
	public int insertMdtActMoRpt(List<MdtActMoRptModel> list) {
		return mdtActMoRptMapper.insertMdtActMoRpt(list);
	}

	@Override
	public int deleteMdtActMoRpt(String actId, String date) {
		return mdtActMoRptMapper.deleteMdtActMoRpt(actId, date);
	}



}
