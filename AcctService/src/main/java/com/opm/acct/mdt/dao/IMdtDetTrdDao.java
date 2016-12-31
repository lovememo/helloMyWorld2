package com.opm.acct.mdt.dao;

import java.util.List;

import com.opm.acct.mdt.model.MdtDetTrdModel;

public interface IMdtDetTrdDao {
	
	/**
	 * 插入受托户会计记账科目
	 * @param list
	 * @return
	 */
	public int insertMdtDetTrdForImp (List<MdtDetTrdModel> list, String trdNo);
	
	/**
	 * 借贷明细总额校验
	 * @param trdNo
	 * @return
	 */
	public String debitCreditAmtCheck(String trdNo);
	
	/**
	 * 会记记账恒等式校验
	 * @param trdNo
	 * @return
	 */
	public String debitTradeAmtCheck(String trdNo);
	
	/**
	 * 受托户导入插入利息信息
	 * @param appNo
	 * @param opDate
	 * @param planId
	 * @return
	 */
	public int insertDrawForImp (String trdNo, String opDate, String actId);
	
	/**
	 * 受托户明细修改-插入受托户会计记账交易表 
	 * @param list
	 * @param trdNo
	 * @param actDate
	 * @return
	 */
	public int insertMdtDetTrdForMod (List<MdtDetTrdModel> list, String trdNo, String actDate);
	
	/**
	 * 日结插入利息信息
	 * @param trdNo
	 * @param opDate
	 * @param actId
	 * @param amt
	 * @return
	 */
	public int insertDrawForDayend (String trdNo, String opDate, 
			String actId, String amt);
	/**
	 * 插入受托户会计记账交易表
	 * @param list
	 * @return
	 */
	public int insertMdtDetTrd (List<MdtDetTrdModel> list);
	
	/**
	 * 冲回第二日受托户受托管理费和托管费的账务处理
	 * @param actId
	 * @param date
	 * @param trdNo
	 * @return
	 */
	public int reverseMdtAndTrustFee (String actId,  String date,String trdNo);
	
	/**
	 * 冲回月末损益结转
	 * @param actId
	 * @param trdNo
	 * @param date
	 * @param beginDateDraw
	 * @return
	 */
	public int reverseMdtMonAcct (String actId,String trdNo, String date, String beginDateDraw);
	
	/**
	 * 冲账-插入受托户会计记账交易表
	 * @param trdNo
	 * @param actId
	 * @return
	 */
	public int insertMdtDetTrdForReverAct (String trdNo, String actId);
	
	/**
	 * 受托户记账-插入受托户会计记账交易表
	 * @param list
	 * @param trdNo
	 * @return
	 */
	public String insertMdtDetTrdForAcctRecord (List<MdtDetTrdModel> list, String trdNo);
}
