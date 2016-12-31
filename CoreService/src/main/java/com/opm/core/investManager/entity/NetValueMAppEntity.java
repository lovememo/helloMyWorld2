package com.opm.core.investManager.entity;

import java.util.Map;

public class NetValueMAppEntity {
	private String appNo;
	private String planId;
	private String evaluateDate;
	private String opDate;
	private String acceptDate;
	private String planName;
	private String nextEvaluateDate;
	private String commonPlanId;
	
	public NetValueMAppEntity(){}
	
	public NetValueMAppEntity(Map params){
		if ( params != null ) {
			this.appNo = params.containsKey("appNo") ? params.get("appNo").toString(): this.appNo;
			this.planId = params.containsKey("planId") ? params.get("planId").toString() : this.planId;
			this.planName = params.containsKey("planName") ? params.get("planName").toString() : this.planName;
			this.evaluateDate = params.containsKey("evaluateDate") ? params.get("evaluateDate").toString() : this.evaluateDate;
			this.opDate = params.containsKey("opDate") ? params.get("opDate").toString() : this.opDate;
			this.acceptDate = params.containsKey("acceptDate") ? params.get("acceptDate").toString() : this.acceptDate;
			this.nextEvaluateDate = params.containsKey("nextEvaluateDate") ? params.get("nextEvaluateDate").toString() : this.nextEvaluateDate;
			this.commonPlanId = params.containsKey("commonPlanId") ? params.get("commonPlanId").toString() : this.commonPlanId;
		}
		
	}
	
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getEvaluateDate() {
		return evaluateDate;
	}
	public void setEvaluateDate(String evaluateDate) {
		this.evaluateDate = evaluateDate;
	}
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getNextEvaluateDate() {
		return nextEvaluateDate;
	}
	public void setNextEvaluateDate(String nextEvaluateDate) {
		this.nextEvaluateDate = nextEvaluateDate;
	}
	public String getCommonPlanId() {
		return commonPlanId;
	}
	public void setCommonPlanId(String commonPlanId) {
		this.commonPlanId = commonPlanId;
	}

	
}
