package com.opm.acct.mdt.reverse.act.service;

import java.util.List;

import com.opm.acct.mdt.model.MdtDetModel;

public interface IMdtReverseActService {
	
	/**
	 * 受托户冲账帐数据准备
	 * @param planId
	 * @param seqIdStr
	 * @return
	 */
	public List<Object> doPrepare (String planId, String seqIdStr);
	/**
	 * 受托户冲帐生效
	 * @param trdNo
	 * @param actInfo
	 * @return
	 */
	public List<Object> doTrade (String trdNo, String planId);
	
	/**
	 * 受托户冲账申请 受托户会计账务处理明细查询
	 * @param planId
	 * @param date
	 * @param beginNum
	 * @param fetchNum
	 * @return
	 */
	public List<MdtDetModel> qryMdtDetList (String planId,String date,
			String amt,String beginNum,String fetchNum);
	/**
	 * 受托户冲账申请复核  冲账申请查询
	 * @param actId
	 * @return
	 */
	public List<MdtDetModel> qryReverseActApp (String trdNo);
}
