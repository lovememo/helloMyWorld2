package com.opm.core.investManager.model;

import java.util.List;

import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueEntity;
import com.opm.core.investManager.entity.NetValueMAppEntity;


public class NetValueAppFormModel2 {
	
	private NetValueMAppEntity mApp;
	private NetValueEntity sumInvest;
	private List<NetValueAppEntity> investList;
	
	public NetValueMAppEntity getMApp() {
		return mApp;
	}
	public void setMApp(NetValueMAppEntity mApp) {
		this.mApp = mApp;
	}
	public NetValueEntity getSumInvest() {
		return sumInvest;
	}
	public void setSumInvest(NetValueEntity sumInvest) {
		this.sumInvest = sumInvest;
	}
	public List<NetValueAppEntity> getInvestList() {
		return investList;
	}
	public void setInvestList(List<NetValueAppEntity> investList) {
		this.investList = investList;
	}
	
	
	
}
