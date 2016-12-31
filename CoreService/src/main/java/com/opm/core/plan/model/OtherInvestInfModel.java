package com.opm.core.plan.model;

public class OtherInvestInfModel {

	private String trdNo;
	private String appNo;
	private String planId;
	private String commonPlanId;
	
	private String investId;
	private String investName;
	private String investorId;
	private String investorName;
	
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
	public String getCommonPlanId() {
		return commonPlanId;
	}
	public void setCommonPlanId(String commonPlanId) {
		this.commonPlanId = commonPlanId;
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
	public String getInvestorId() {
		return investorId;
	}
	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}
	public String getInvestorName() {
		return investorName;
	}
	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}
	@Override
	public String toString() {
		return "OtherInvestInfModel [commonPlanId=" + commonPlanId + ", investName=" + investName + "]";
	}
	
	
	

}
