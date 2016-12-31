package com.opm.core.paymanager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.core.paymanager.model.EmpPayNoteStatisticData;
import com.opm.core.paymanager.model.PayTradeModel;

@Mapper
public interface PayMapping {
	public int insertPayTrade(PayTradeModel payTradeModel);

	public int updatePayReportRecstatus(@Param("payNo") String payNo, @Param("state") String state);

	public EmpPayNoteStatisticData getStaticPayNoteData(String tradeNo);

	public PayTradeModel queryPayReport(PayTradeModel payTradeModel);

	public int insertPayReportRec(PayTradeModel payTradeModel);

	public PayTradeModel queryPayReportRecByPayNo(String payNo);

	public int insertEmpPayNoteRecFromTrade(PayTradeModel payTradeModel);

	public int updatePayReport(PayTradeModel payTradeModel);

	public int insertPayTradeDet(@Param("fileNo")String fileNo, @Param("tradeNo")String tradeNo);

	public int qryPayReportTrdCount(String tradeNo);
}
