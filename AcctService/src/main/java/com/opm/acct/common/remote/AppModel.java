package com.opm.acct.common.remote;

import java.util.Date;
import java.util.Map;

/**
 * ������
 * @author kfzx-wanghong01
 *
 */
public class AppModel {
	
	String appNo; //������
	String planId; //�ƻ����
	String appType; //��������
	String appState; //����״̬
	String appUser; //�����û�
	String appStru; //�������
	Date appTime; //����ʱ��
	String appPlanTime; //����ƻ�ʱ��
	String audUser; //����û�
	String audStru; //��˻���
	Date audTime; //���ʱ��
	String audPlanTime; //��˼ƻ�ʱ��
	String bakFlag; //����ת���־
	String opFlag; //���������� //TODO Q�����������Ҫһ��ö���ࣿ
	
	public AppModel(){}
	
	@SuppressWarnings("rawtypes")
	public AppModel(Map params){
		if ( params != null ) {
			this.appNo = (String) params.get("appNo");
			this.planId = (String) params.get("planId");
			this.appType = (String) params.get("appType");
			this.appState = (String) params.get("appState");
			this.appUser = (String) params.get("appUser");
			this.appStru = (String) params.get("appStru");
			this.appTime = (Date) params.get("appTime");
			this.appPlanTime = (String) params.get("appPlanTime");
			this.audUser = (String) params.get("audUser");
			this.audStru = (String) params.get("audStru");
			this.audTime = (Date) params.get("audTime");
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
	public Date getAppTime() {
		return appTime;
	}
	public void setAppTime(Date appTime) {
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
	public Date getAudTime() {
		return audTime;
	}
	public void setAudTime(Date audTime) {
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
	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}
	
}
