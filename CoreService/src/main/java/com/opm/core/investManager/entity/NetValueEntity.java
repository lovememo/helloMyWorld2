package com.opm.core.investManager.entity;

import java.util.Map;

/**
 * 净值基础类
 * @author kfzx-chenym
 *
 */
public class NetValueEntity {

	private String planId = ""; //计划编号
	private String investId = ""; //投资组合编号
	private String evaluateDate = ""; //定价日
	private String opDate = ""; //汇总日
	private Double unitValue = 0D; //每份金额
	private Long quotient = 0L; //累计份额
	private Double netAmt = 0D; //资产净值
	
	public NetValueEntity(){}
	
	public NetValueEntity(Map params) {
		if ( params != null ) {
			this.planId = params.containsKey("planId") ? params.get("planId").toString() : this.planId;
			this.investId = params.containsKey("investId") ? params.get("investId").toString() : this.investId;
			this.evaluateDate = params.containsKey("evaluateDate") ? params.get("evaluateDate").toString() : this.evaluateDate;
			this.opDate = params.containsKey("opDate") ? params.get("opDate").toString() : this.opDate;
			this.unitValue = params.containsKey("unitValue") ? Double.valueOf(params.get("unitValue").toString()) : this.unitValue;
			this.quotient = params.containsKey("quotient") ? Long.valueOf(params.get("quotient").toString()) : this.quotient;
			this.netAmt = params.containsKey("netAmt") ? Double.valueOf(params.get("netAmt").toString()) : this.netAmt;
		}
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
	public Double getUnitValue() {
		return unitValue;
	}
	public void setUnitValue(Double unitValue) {
		this.unitValue = unitValue;
	}
	public Long getQuotient() {
		return quotient;
	}
	public void setQuotient(Long quotient) {
		this.quotient = quotient;
	}
	public Double getNetAmt() {
		return netAmt;
	}
	public void setNetAmt(Double netAmt) {
		this.netAmt = netAmt;
	}
	
	
}
