package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/12/9.
 */
public class MdtDayendAppModel {

	private String trdNo;//������ˮ��           
	private String beginDate;//��ʼʱ��       
	private String endDate;//����ʱ��         
	private String dayendFlag;//������־(1 �ս� 2 ȡ���ս�) 
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
