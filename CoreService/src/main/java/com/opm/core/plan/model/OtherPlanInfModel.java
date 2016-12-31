package com.opm.core.plan.model;

import java.util.List;

public class OtherPlanInfModel {

	private String trdNo;
	private String appNo;
	private String planId;
	private String planName;
	private String commonPlanId;
	private String trustee;
	private String trust;
	private String investNum;
	private String primaryFlag;
	
	private List<OtherInvestInfModel> otherInvestInfModelList;
	
	
	public List<OtherInvestInfModel> getOtherInvestInfModelList() {
		return otherInvestInfModelList;
	}
	public void setOtherInvestInfModelList(List<OtherInvestInfModel> otherInvestInfModelList) {
		this.otherInvestInfModelList = otherInvestInfModelList;
	}
	
	
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
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getCommonPlanId() {
		return commonPlanId;
	}
	public void setCommonPlanId(String commonPlanId) {
		this.commonPlanId = commonPlanId;
	}
	public String getTrustee() {
		return trustee;
	}
	public void setTrustee(String trustee) {
		this.trustee = trustee;
	}
	public String getTrust() {
		return trust;
	}
	public void setTrust(String trust) {
		this.trust = trust;
	}
	public String getInvestNum() {
		return investNum;
	}
	public void setInvestNum(String investNum) {
		this.investNum = investNum;
	}
	public String getPrimaryFlag() {
		return primaryFlag;
	}
	public void setPrimaryFlag(String primaryFlag) {
		this.primaryFlag = primaryFlag;
	}
	
	@Override
	public String toString() {
		return "OtherPlanInfModel [planName=" + planName + ", commonPlanId=" + commonPlanId + "]";
	}
	
	

}
