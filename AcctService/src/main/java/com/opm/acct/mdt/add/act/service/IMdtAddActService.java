package com.opm.acct.mdt.add.act.service;

import java.util.List;

public interface IMdtAddActService {
	
	/**
	 * ���л���������׼��
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
	 * ���л�������Ч
	 * @param trdNo
	 * @param actInfo
	 * @return
	 */
	public List<Object> doTrade (String trdNo, String planId);
	
}
