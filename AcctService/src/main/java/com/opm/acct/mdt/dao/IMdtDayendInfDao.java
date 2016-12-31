package com.opm.acct.mdt.dao;

import com.opm.acct.mdt.model.MdtDayendInfModel;

public interface IMdtDayendInfDao {
	/**
	 * 判断是否完成月结
	 * @param actId
	 * @param date
	 * @return
	 */
	public boolean isFinishMonend (String actId, String date);
	
	/**
	 * 获取受托户已完成日结日期 
	 * @param actId
	 * @return
	 */
	public String getDayendDateFinished (String actId);
	
	/**
	 * 判断日期区间是否已日结
	 * @param actId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public boolean isFinishDayendInterval (String actId, String beginDate, String endDate);
	
	/**
	 * 获取受托户明细余额
	 * @param actId
	 * @param date
	 * @return
	 */
	public String getAmtByDate (String actId, String date);
	
	/**
	 * 获取最后一个已结账日期
	 * @param actId
	 * @return
	 */
	public String getLastDayendDate (String actId);
	
	/**
	 * 获取日结信息
	 * @param actId
	 * @param date
	 * @return
	 */
	public MdtDayendInfModel getMdtDayendInf (String actId, String date);
	/**
	 * 取消日结更新相关信息
	 * @param actId
	 * @param date
	 * @param trdNo
	 * @return
	 */
	public String updForDayendCancel (String actId, String date, String trdNo);
	
	/**
	 * 判断是否完成日结
	 * @param actId
	 * @param dayendDate
	 * @return
	 */
	public boolean isFinishDayend (String actId, String dayendDate);
	
}
