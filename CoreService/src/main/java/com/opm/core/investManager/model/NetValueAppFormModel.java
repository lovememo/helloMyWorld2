package com.opm.core.investManager.model;

import java.util.List;

import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueEntity;


public class NetValueAppFormModel {
	
	private String planId;
	private String planName;
	private String commonPlanId;
	private String planEvalType;
	private String icbcFlag;
	private String evaluateDate;
	private String nextEvaluateData;
	private NetValueAppEntity sumInvest;
	private List<Plan> planList;
	
	
	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public String getCommonPlanId() {
		return commonPlanId;
	}
	public void setCommonPlanId(String commonPlanId) {
		this.commonPlanId = commonPlanId;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanEvalType() {
		return planEvalType;
	}
	public void setPlanEvalType(String planEvalType) {
		this.planEvalType = planEvalType;
	}
	public String getIcbcFlag() {
		return icbcFlag;
	}
	public void setIcbcFlag(String icbcFlag) {
		this.icbcFlag = icbcFlag;
	}
	public String getNextEvaluateData() {
		return nextEvaluateData;
	}
	public void setNextEvaluateData(String nextEvaluateData) {
		this.nextEvaluateData = nextEvaluateData;
	}
	public NetValueAppEntity getSumInvest() {
		return sumInvest;
	}
	public void setSumInvest(NetValueAppEntity sumInvest) {
		this.sumInvest = sumInvest;
	}
	public List<Plan> getPlanList() {
		return planList;
	}
	public void setPlanList(List<Plan> planList) {
		this.planList = planList;
	}
	public String getEvaluateDate() {
		return evaluateDate;
	}
	public void setEvaluateDate(String evaluateDate) {
		this.evaluateDate = evaluateDate;
	}
	
	public class Plan {
			
			private String planId;
			private String planName;
			private String state;
			private List<NetValueAppEntity> investList;
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
			public String getState() {
				return state;
			}
			public void setState(String state) {
				this.state = state;
			}
			public List<NetValueAppEntity> getInvestList() {
				return investList;
			}
			public void setInvestList(List<NetValueAppEntity> investList) {
				this.investList = investList;
			}
	}
	
}
