package com.opm.core.paymanager.dao;

import com.opm.core.paymanager.model.EmpPayNoteStatisticData;
import com.opm.core.paymanager.model.PayTradeModel;

public interface IPayDao {
	public int insertPayTrade(PayTradeModel payTradeModel);

	public EmpPayNoteStatisticData getStaticPayNoteData(String tradeNo);

	public PayTradeModel queryPayReport(PayTradeModel payTradeModel);

	public void insertPayReportRec(PayTradeModel payTradeModel);

	public PayTradeModel queryPayReportRecByPayNo(String payNo);

	public int insertEmpPayNoteRecFromTrade(PayTradeModel payTradeModel);

	public int updatePayReport(PayTradeModel payTradeModel);

	public int insertPayTradeDet(String fileNo, String tradeNo);

}
