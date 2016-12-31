package com.opm.acct.mdt.add.act.service;

import java.util.List;

public interface IMdtAddActService {
	
	/**
	 * 受托户补帐数据准备
	 * @param planId
	 * @param mdtAddActType
	 * @param opDate
	 * @param mdtAddOpType
	 * @param reason
	 * @param mdtSubjectTypeStr
	 * @param debitCreditFlagStr
	 * @param tradeAmtStr
	 * @param memo
	 * @return
	 */
	public List<Object> doPrepare (String planId, String mdtAddActType, String opDate, String mdtAddOpType,
			String reason, String mdtSubjectTypeStr, String debitCreditFlagStr,
			String tradeAmtStr,String memo);
	/**
	 * 受托户补帐生效
	 * @param trdNo
	 * @param actInfo
	 * @return
	 */
	public List<Object> doTrade (String trdNo, String planId);
	
}
