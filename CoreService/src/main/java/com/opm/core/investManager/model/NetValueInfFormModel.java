package com.opm.core.investManager.model;

import java.util.List;

import com.opm.core.investManager.entity.NetValueEntity;

public class NetValueInfFormModel {

	private String planId;
	private String investId;
	private String evaluateDate;
	private String acceptDate;
	private String opDate;
	private String lastAppNo;
	private List<NetValueEntity> investList;
	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getInvestId() {
		return investId;
	}
	public void setInvestId(String investId) {
		this.investId = investId;
	}
	public String getEvaluateDate() {
		return evaluateDate;
	}
	public void setEvaluateDate(String evaluateDate) {
		this.evaluateDate = evaluateDate;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}
	public String getLastAppNo() {
		return lastAppNo;
	}
	public void setLastAppNo(String lastAppNo) {
		this.lastAppNo = lastAppNo;
	}
	public List<NetValueEntity> getInvestList() {
		return investList;
	}
	public void setInvestList(List<NetValueEntity> investList) {
		this.investList = investList;
	}
	
}
