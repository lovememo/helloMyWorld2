package com.opm.core.plan.model;

public class PlanRoleModel {

	private String trdNo;
	private String appNo;
	private String planId;
	private String statusType;
	private String struId;
	private String orgCode;
	private String subinstid;
	private String addr;
	private String zipcode;
	private String busiMgr;
	private String tel;
	private String fax;
	private String email;
	private String struName;
	
	
	public String getStruName() {
		return struName;
	}
	public void setStruName(String struName) {
		this.struName = struName;
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
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public String getStruId() {
		return struId;
	}
	public void setStruId(String struId) {
		this.struId = struId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getSubinstid() {
		return subinstid;
	}
	public void setSubinstid(String subinstid) {
		this.subinstid = subinstid;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getBusiMgr() {
		return busiMgr;
	}
	public void setBusiMgr(String busiMgr) {
		this.busiMgr = busiMgr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "PlanRoleModel [planId=" + planId + ", struId=" + struId + "]";
	}
}
