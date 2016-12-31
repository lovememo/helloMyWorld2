package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/12/9.
 */
public class MdtDayendTrdModel {

	private String trdNo;//交易流水号      
	private String beginDate;//开始时间
	private String endDate;//结束时间       
	private String dayendFlag;//操作标志(1 日结 2 取消日结)
	
	
	
	public MdtDayendTrdModel(String trdNo, String beginDate, String endDate, String dayendFlag) {
		super();
		this.trdNo = trdNo;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.dayendFlag = dayendFlag;
	}
	public String getTrdNo() {
		return trdNo;
	}
	public void setTrdNo(String trdNo) {
		this.trdNo = trdNo;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDayendFlag() {
		return dayendFlag;
	}
	public void setDayendFlag(String dayendFlag) {
		this.dayendFlag = dayendFlag;
	}
	
}
