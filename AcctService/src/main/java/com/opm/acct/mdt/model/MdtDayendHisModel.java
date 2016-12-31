package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/11/22.
 */
public class MdtDayendHisModel {


	private String actId;//受托户账户编码 
	private String dayendDate;//日结时间
	private String isFinished;//是否完成日结 0-是; 1-否
	private String updateTime;//更新时间
	private String amt;//净值  
	private String trdNo;//交易流水号
	
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
	public String getTrdNo() {
		return trdNo;
	}
	public void setTrdNo(String trdNo) {
		this.trdNo = trdNo;
	}
	public String getDayendDate() {
		return dayendDate;
	}
	public void setDayendDate(String dayendDate) {
		this.dayendDate = dayendDate;
	}
	public String getIsFinished() {
		return isFinished;
	}
	public void setIsFinished(String isFinished) {
		this.isFinished = isFinished;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}

}
