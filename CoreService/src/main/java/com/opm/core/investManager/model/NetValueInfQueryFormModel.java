package com.opm.core.investManager.model;

import java.util.List;

import com.opm.core.investManager.entity.NetValueAppEntity;

public class NetValueInfQueryFormModel {
	private List<NetValueAppEntity> planList;
	private List<NetValueAppEntity> evaluateDateList;
	
	public List<NetValueAppEntity> getPlanList() {
		return planList;
	}
	public void setPlanList(List<NetValueAppEntity> planList) {
		this.planList = planList;
	}
	public List<NetValueAppEntity> getEvaluateDateList() {
		return evaluateDateList;
	}
	public void setEvaluateDateList(List<NetValueAppEntity> evaluateDateList) {
		this.evaluateDateList = evaluateDateList;
	}
	
	
}
