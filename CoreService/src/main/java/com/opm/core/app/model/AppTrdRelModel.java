package com.opm.core.app.model;

public class AppTrdRelModel {

	private String appNo; //������ˮ��
	private String trdNo; //������ˮ��
	private String trdType; //�������� //TODO ˭������Ҫ��Task�������
	private Long trdOrder; //TODO ��ôȡ���
	
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
