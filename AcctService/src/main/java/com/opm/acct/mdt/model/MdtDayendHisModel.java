package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/11/22.
 */
public class MdtDayendHisModel {


	private String actId;//���л��˻����� 
	private String dayendDate;//�ս�ʱ��
	private String isFinished;//�Ƿ�����ս� 0-��; 1-��
	private String updateTime;//����ʱ��
	private String amt;//��ֵ  
	private String trdNo;//������ˮ��
	
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
