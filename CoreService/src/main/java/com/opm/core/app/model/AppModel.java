package com.opm.core.app.model;

import java.util.Map;


/**
 * 申请类
 * @author kfzx-chenym
 *
 */
public class AppModel {
	
	String appNo; //申请编号
	String planId; //计划编号
	String appType; //申请类型
	String appState; //申请状态
	String appUser; //申请用户
	String appStru; //申请机构
	String appTime; //申请时间
	String appPlanTime; //申请计划时间
	String audUser; //审核用户
	String audRole;//审核用户角色
	String audStru; //审核机构
	String audTime; //审核时间
	String audMemo;//审批备注
	String retFlag;//返回标志
	String retMsg;//返回值
	String audPlanTime; //审核计划时间
	String bakFlag; //申请转存标志
	String opFlag; //申请操作标记 //TODO Q：申请操作需要一个枚举类？
	
	public AppModel(){}
	
	public AppModel(Map params){
		if ( params != null ) {
			this.appNo = (String) params.get("appNo");
			this.planId = (String) params.get("planId");
			this.appType = (String) params.get("appType");
			this.appState = (String) params.get("appState");
			this.appUser = (String) params.get("appUser");
			this.appStru = (String) params.get("appStru");
			this.appTime = (String) params.get("appTime");
			this.appPlanTime = (String) params.get("appPlanTime");
			this.audUser = (String) params.get("audUser");
			this.audStru = (String) params.get("audStru");
			this.audTime = (String) params.get("audTime");
			this.audPlanTime = (String) params.get("audPlanTime");
			this.bakFlag = (String) params.get("bakFlag");
		}
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
	public String getAppUser() {
		return appUser;
	}
	public void setAppUser(String appUser) {
		this.appUser = appUser;
	}
	public String getAppStru() {
		return appStru;
	}
	public void setAppStru(String appStru) {
		this.appStru = appStru;
	}
	public String getAppTime() {
		return appTime;
	}
	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}
	public String getAppPlanTime() {
		return appPlanTime;
	}
	public void setAppPlanTime(String appPlanTime) {
		this.appPlanTime = appPlanTime;
	}
	public String getAudUser() {
		return audUser;
	}
	public void setAudUser(String audUser) {
		this.audUser = audUser;
	}
	public String getAudStru() {
		return audStru;
	}
	public void setAudStru(String audStru) {
		this.audStru = audStru;
	}
	public String getAudTime() {
		return audTime;
	}
	public void setAudTime(String audTime) {
		this.audTime = audTime;
	}
	public String getAudPlanTime() {
		return audPlanTime;
	}
	public void setAudPlanTime(String audPlanTime) {
		this.audPlanTime = audPlanTime;
	}
	public String getBakFlag() {
		return bakFlag;
	}
	public void setBakFlag(String bakFlag) {
		this.bakFlag = bakFlag;
	}
	public String getOpFlag() {
		return opFlag;
	}
	public void setOpFlag(String string) {
		this.opFlag = string;
	}

	public String getAudRole() {
		return audRole;
	}

	public void setAudRole(String audRole) {
		this.audRole = audRole;
	}

	public String getAudMemo() {
		return audMemo;
	}

	public void setAudMemo(String audMemo) {
		this.audMemo = audMemo;
	}

	public String getRetFlag() {
		return retFlag;
	}

	public void setRetFlag(String retFlag) {
		this.retFlag = retFlag;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	@Override
	public String toString() {
		return "AppModel [appNo=" + appNo + ", planId=" + planId + ", appType=" + appType + ", appState=" + appState
				+ ", appUser=" + appUser + ", appStru=" + appStru + ", appTime=" + appTime + ", appPlanTime="
				+ appPlanTime + ", audUser=" + audUser + ", audRole=" + audRole + ", audStru=" + audStru + ", audTime="
				+ audTime + ", audMemo=" + audMemo + ", retFlag=" + retFlag + ", retMsg=" + retMsg + ", audPlanTime="
				+ audPlanTime + ", bakFlag=" + bakFlag + ", opFlag=" + opFlag + "]";
	}
	
}
