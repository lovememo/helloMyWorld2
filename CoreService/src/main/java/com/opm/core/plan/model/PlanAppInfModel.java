/**
 * 计划的申请信息
 */
package com.opm.core.plan.model;

public class PlanAppInfModel {
	private long appNo; // 申请号
	private String planId; // 计划编码
	private String prodId; // 产品编码
	private String planState; // 计划状态
	private String planName; // 计划名称
	private String planLoc; // 计划所在地
	private String planLocTotal; // 计划所在地总数
	private String valuationMethod; // 计划估值方式
	private String planType; // 计划类别
	private String planAttr; // 计划属性
	private String trustPlanId; // 受托人年金计划编码
	private String acctBeginDate; // 管理起始日期
	private String confirmFlag; // 是否获取计划确认函
	private String commonPlanId; // 计划登记号
	private String confirmDate; // 计划备案批复日期
	private String socSecStru; // 计划报备机构[字典值]
	private String trustContract; // 受托合同号
	private String submitCtrl; // 结账控制
	private String actFlag; // 是否受托户会计核算 ；0 是、1 否
	private String actRate; // 受托户银行存款利率
	private String actDate; // 受托户核算起始日期

	public long getAppNo() {
		return appNo;
	}

	public void setAppNo(long appNo) {
		this.appNo = appNo;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getPlanState() {
		return planState;
	}

	public void setPlanState(String planState) {
		this.planState = planState;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanLoc() {
		return planLoc;
	}

	public void setPlanLoc(String planLoc) {
		this.planLoc = planLoc;
	}

	public String getPlanLocTotal() {
		return planLocTotal;
	}

	public void setPlanLocTotal(String planLocTotal) {
		this.planLocTotal = planLocTotal;
	}

	public String getValuationMethod() {
		return valuationMethod;
	}

	public void setValuationMethod(String valuationMethod) {
		this.valuationMethod = valuationMethod;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getPlanAttr() {
		return planAttr;
	}

	public void setPlanAttr(String planAttr) {
		this.planAttr = planAttr;
	}

	public String getTrustPlanId() {
		return trustPlanId;
	}

	public void setTrustPlanId(String trustPlanId) {
		this.trustPlanId = trustPlanId;
	}

	public String getAcctBeginDate() {
		return acctBeginDate;
	}

	public void setAcctBeginDate(String acctBeginDate) {
		this.acctBeginDate = acctBeginDate;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getCommonPlanId() {
		return commonPlanId;
	}

	public void setCommonPlanId(String commonPlanId) {
		this.commonPlanId = commonPlanId;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getSocSecStru() {
		return socSecStru;
	}

	public void setSocSecStru(String socSecStru) {
		this.socSecStru = socSecStru;
	}

	public String getTrustContract() {
		return trustContract;
	}

	public void setTrustContract(String trustContract) {
		this.trustContract = trustContract;
	}

	public String getSubmitCtrl() {
		return submitCtrl;
	}

	public void setSubmitCtrl(String submitCtrl) {
		this.submitCtrl = submitCtrl;
	}

	public String getActFlag() {
		return actFlag;
	}

	public void setActFlag(String actFlag) {
		this.actFlag = actFlag;
	}

	public String getActRate() {
		return actRate;
	}

	public void setActRate(String actRate) {
		this.actRate = actRate;
	}

	public String getActDate() {
		return actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = actDate;
	}
}
