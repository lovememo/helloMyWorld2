package com.opm.core.paymanager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.paymanager.mapper.PayMapping;
import com.opm.core.paymanager.model.EmpPayNoteStatisticData;
import com.opm.core.paymanager.model.PayTradeConstance;
import com.opm.core.paymanager.model.PayTradeModel;

@Repository
public class PayDaoImp implements IPayDao{
	@Autowired
	PayMapping payMapping;
	
	@Override
	public int insertPayTrade(PayTradeModel payTradeModel) {
		return payMapping.insertPayTrade(payTradeModel);
	}
	
	@Override
	public EmpPayNoteStatisticData getStaticPayNoteData(String tradeNo) {
		return payMapping.getStaticPayNoteData(tradeNo);
	}

	@Override
	public PayTradeModel queryPayReport(PayTradeModel payTradeModel) {
		return payMapping.queryPayReport(payTradeModel);
	}

	@Override
	public void insertPayReportRec(PayTradeModel payTradeModel) {
		 payMapping.insertPayReportRec(payTradeModel);
	}

	@Override
	public PayTradeModel queryPayReportRecByPayNo(String payNo) {
		return payMapping.queryPayReportRecByPayNo(payNo);
	}

	@Override
	public int insertEmpPayNoteRecFromTrade(PayTradeModel payTradeModel) {
		return payMapping.insertEmpPayNoteRecFromTrade(payTradeModel);
	}

	@Override
	public int updatePayReport(PayTradeModel payTradeModel) {
		return payMapping.updatePayReport(payTradeModel);
	}

	@Override
	public int insertPayTradeDet(String fileNo, String tradeNo) {
		return payMapping.insertPayTradeDet(fileNo,tradeNo);
	}

}
