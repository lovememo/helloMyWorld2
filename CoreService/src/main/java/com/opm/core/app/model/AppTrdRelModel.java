package com.opm.core.app.model;

public class AppTrdRelModel {

	private String appNo; //申请流水号
	private String trdNo; //交易流水号
	private String trdType; //交易类型 //TODO 谁定？需要和Task类关联否？
	private Long trdOrder; //TODO 怎么取编号
	
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getTrdNo() {
		return trdNo;
	}
	public void setTrdNo(String trdNo) {
		this.trdNo = trdNo;
	}
	public String getTrdType() {
		return trdType;
	}
	public void setTrdType(String trdType) {
		this.trdType = trdType;
	}
	public Long getTrdOrder() {
		return trdOrder;
	}
	public void setTrdOrder(Long trdOrder) {
		this.trdOrder = trdOrder;
	}
	
	
}
