package com.opm.core.investManager.entity;

import java.util.Map;

import com.opm.core.investManager.entity.NetValueEntity;

/**
 * 净值申请明细类
 * @author kfzx-chenym
 *
 */
public class NetValueAppEntity extends NetValueEntity {
	
	private String appNo = ""; //申请号
	private String acceptDate = ""; //净值表接收时间
	private String planName = ""; //计划名称
	private String investName = ""; //投资组合名称
	
	public NetValueAppEntity() {
		super();
	}
	
	public NetValueAppEntity(Map params) {
		super(params);
		if ( params != null ) {
			this.appNo = params.containsKey("appNo") ? params.get("appNo").toString() : this.appNo;
			this.acceptDate = params.containsKey("acceptDate") ? params.get("acceptDate").toString() : this.acceptDate;
		}
		
		
	}
	
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
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

	public String getInvestName() {
		return investName;
	}

	public void setInvestName(String investName) {
		this.investName = investName;
	}
	
}
