package com.opm.core.plan.model;

public class PlanAcctModel {

	private String trdNo;
	private String appNo;
	private String planId;
	private String feeType;
	private String investId;
	private String acctOpenDate;
	private String acctName;
	private String bankName;
	private String bankNo;
	private String acctNo;
	private String openBranch;
	private String acctProv;
	private String acctCity;
	private String unionBranchId;

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

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public String getAcctOpenDate() {
		return acctOpenDate;
	}

	public void setAcctOpenDate(String acctOpenDate) {
		this.acctOpenDate = acctOpenDate;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getOpenBranch() {
		return openBranch;
	}

	public void setOpenBranch(String openBranch) {
		this.openBranch = openBranch;
	}

	public String getAcctProv() {
		return acctProv;
	}

	public void setAcctProv(String acctProv) {
		this.acctProv = acctProv;
	}

	public String getAcctCity() {
		return acctCity;
	}

	public void setAcctCity(String acctCity) {
		this.acctCity = acctCity;
	}

	public String getUnionBranchId() {
		return unionBranchId;
	}

	public void setUnionBranchId(String unionBranchId) {
		this.unionBranchId = unionBranchId;
	}

	@Override
	public String toString() {
		return "PlanAcctModel [feeType=" + feeType + ", investId=" + investId + "]";
	}
	
}
