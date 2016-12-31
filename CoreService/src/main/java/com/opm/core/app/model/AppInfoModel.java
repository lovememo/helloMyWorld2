package com.opm.core.app.model;

public class AppInfoModel {
	/**
	 * 申请号
	 */
	public int appNo;

	/**
	 * 计划名称
	 */
	public String planName;

	/**
	 * 申请用户
	 */
	public String appUser;

	/**
	 * 申请类型
	 */
	public String appType;

	/**
	 * 申请状态
	 */
	public String appState;

	/**
	 * 申请时间
	 */
	public String appTime;

	public int getAppNo() {
		return appNo;
	}

	public void setAppNo(int appNo) {
		this.appNo = appNo;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getAppUser() {
		return appUser;
	}

	public void setAppUser(String appUser) {
		this.appUser = appUser;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppState() {
		return appState;
	}

	public void setAppState(String appState) {
		this.appState = appState;
	}

	public String getAppTime() {
		return appTime;
	}

	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}
}
