package com.opm.core.plan.model;

public class PlanInvestModel {

	private String trdNo;
	private String appNo;
	private String planId;
	private String investId;
	private String investName;
	private String trusteeId;
	private String trusteeName;
	private String investorInvestId;
	private String offerRate;
	private String payRate;
	
	
	public String getTrdNo() {
		return trdNo;
	}
	public void setTrdNo(String trdNo) {
		this.trdNo = trdNo;
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
	public String getInvestId() {
		return investId;
	}
	public void setInvestId(String investId) {
		this.investId = investId;
	}
	public String getInvestName() {
		return investName;
	}
	public void setInvestName(String investName) {
		this.investName = investName;
	}
	public String getTrusteeId() {
		return trusteeId;
	}
	public void setTrusteeId(String trusteeId) {
		this.trusteeId = trusteeId;
	}
	public String getTrusteeName() {
		return trusteeName;
	}
	public void setTrusteeName(String trusteeName) {
		this.trusteeName = trusteeName;
	}
	public String getInvestorInvestId() {
		return investorInvestId;
	}
	public void setInvestorInvestId(String investorInvestId) {
		this.investorInvestId = investorInvestId;
	}
	public String getOfferRate() {
		return offerRate;
	}
	public void setOfferRate(String offerRate) {
		this.offerRate = offerRate;
	}
	public String getPayRate() {
		return payRate;
	}
	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}
	
	@Override
	public String toString() {
		return "PlanInvestModel [investName=" + investName + ", investorInvestId=" + investorInvestId + "]";
	}

	
}
