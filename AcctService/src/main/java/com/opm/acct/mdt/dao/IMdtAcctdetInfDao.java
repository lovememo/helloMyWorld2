package com.opm.acct.mdt.dao;

import java.util.List;
import java.util.Map;

import com.opm.acct.mdt.model.MdtAcctdetInfModel;

public interface IMdtAcctdetInfDao {
	/**
	 * 获取有效的受托户资金账务信息数
	 * @param actId
	 * @param validFlag
	 * @return
	 */
	public int getMdtAcctdetInfCount (String actId, String validFlag);
	
	/**
	 * 插入受托户资金账务信息
	 * @param trdNo
	 * @return
	 */
	public String insertMdtAcctdetInf (String trdNo);
	
	/**
	 * 获取受托户导入计息起始日期
	 * @param actId
	 * @return
	 */
	public String getBeginDateForImpDraw (String actId);
	

	/**
	 * 查询 导入前最后一条明细信息
	 * @return
	 */
	public List<Map<String,String>> getMdtAcctLastDet (String actId);
	
	/**
	 * 受托户修改  受托户明细查询
	 * @param actId
	 * @param opDate
	 * @param amt
	 * @param oppAcctName
	 * @param direct
	 * @param beginNum
	 * @param fetchNum
	 * @return
	 */
	List<MdtAcctdetInfModel> qryMdtAcctdetInfList(String actId, String opDate,String amt, String oppAcctName,
			String direct, int beginNum, int fetchNum);
	
	/**
	 * 根据流水号获取受托户资金账务信息
	 * @param seqId
	 * @return
	 */
	public MdtAcctdetInfModel getMdtAcctdetInfBySeqId (String seqId);
	
	/**
	 * 更新受托户资金账务信息
	 * @param trdNo
	 * @param seqId
	 * @return
	 */
	public String updMdtAcctdetInf (String trdNo);
	
	/**
	 * 判断是否计提利息,输入日期至该日期所在月份最后一天范围内
	 * @param actId
	 * @param date
	 * @return
	 */
	public boolean isCalDrawBtwDateAndLastest (String actId, String date);
	
	/**
	 * 获取计息起始日期
	 * @param actId
	 * @param date
	 * @return
	 */
	public String getBeginDateCalDraw (String actId, String date,String actDate);
	
	/**
	 * 获取受托户最后一条明细余额
	 * @param actId
	 * @param date
	 * @return
	 */
	public String getActLastAmtBefDate (String actId, String date);
	
	/**
	 * 更新或插入资产净值
	 * @param actId
	 * @param date
	 * @param isFinished
	 * @param trdNo
	 * @param amt
	 * @return
	 */
	public String updOrInsertAmt ( String actId, String date, String isFinished,
			String trdNo, String amt);
	
	/**
	 * 获取受托户两个日期之间的资金账务信息
	 * @param actId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<MdtAcctdetInfModel> getInfBetween2Date (String actId,String beginDate, String endDate);
}
