package com.opm.acct.mdt.dao;

import java.util.List;

import com.opm.acct.mdt.model.MdtDetModel;

public interface IMdtDetDao {
	/**
	 * 插入受托户会计明细账表
	 * @return
	 */
	public String insertMdtDet (String trdNo);
	
	/**
	 * 根据日期获取余额
	 * @param actId
	 * @param date
	 * @return
	 */
	public String  getAmtByDate (String actId, String date);
	
	/**
	 * 受托户冲账申请 受托户会计账务处理明细查询
	 * @param actId
	 * @param date
	 * @param beginNum
	 * @param fetchNum
	 * @return
	 */
	public List<MdtDetModel> qryMdtDetList (String actId,String date,
			String amt,String beginNum,String fetchNum);
	
	/**
	 * 冲账日结判断
	 * @param trdNo
	 * @return
	 */
	public int  getCountForReverseAct (String trdNo);
	
	/**
	 * 冲账获取最大操作日期
	 * @param trdNo
	 * @return
	 */
	public String  getMaxOpDateForReverseAct (String trdNo);
	
	/**
	 * 受托户冲账申请复核  冲账申请查询
	 * @param actId
	 * @return
	 */
	public List<MdtDetModel> qryReverseActApp (String trdNo);
}
