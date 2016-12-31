package com.opm.core.app.model;

import java.util.Map;

public class AppQryParam {
	
	String appNo;
	String[] appType;
	String[] appState;
	String appUser;
	String planId;
	String appBeginDate;
	String appEndDate;
	String beginNum;
	String fetchNum;
	public AppQryParam() {
	}
	public AppQryParam(Map<String,Object> paramMap) {
		this.setAppNo((String)paramMap.get("appNo"));
		this.setAppType((String)paramMap.get("appType"));
		this.setAppState((String)paramMap.get("appState"));
		this.setAppUser((String)paramMap.get("appUser"));
		this.setPlanId((String)paramMap.get("planId"));
		this.setAppBeginDate((String)paramMap.get("appBeginDate"));
		this.setAppEndDate((String)paramMap.get("appEndDate"));
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String[] getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType =appType.split(",",1000);
	}

	public String[] getAppState() {
		return appState;
	}

	public void setAppState(String appState) {
		this.appState = appState.split(",",1000);
	}

	public String getAppUser() {
		return appUser;
	}

	public void setAppUser(String appUser) {
		this.appUser = appUser;
	}

	public String getAppBeginDate() {
		return appBeginDate;
	}

	public void setAppBeginDate(String appBeginDate) {
		this.appBeginDate = appBeginDate;
	}

	public String getAppEndDate() {
		return appEndDate;
	}

	public void setAppEndDate(String appEndDate) {
		this.appEndDate = appEndDate;
	}


	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getBeginNum() {
		return beginNum;
	}

	public void setBeginNum(String beginNum) {
		this.beginNum = beginNum;
	}

	public String getFetchNum() {
		return fetchNum;
	}

	public void setFetchNum(String fetchNum) {
		this.fetchNum = fetchNum;
	}

}
