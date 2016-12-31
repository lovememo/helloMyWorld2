package com.opm.core.investManager.entity;

import java.util.Map;

import com.opm.core.investManager.entity.NetValueEntity;

/**
 * ��ֵ��Ϣ��
 * @author kfzx-chenym
 *
 */
public class NetValueInfEntity extends NetValueEntity {
	
	private String acceptDate; //��ֵ�����ʱ��
	private String lastAppNo; //�����������
	
	public NetValueInfEntity(){
		super();
	}
	
	public NetValueInfEntity(Map params){
		super(params);
		if ( params != null ) {
			this.acceptDate = params.containsKey("acceptDate") ? params.get("acceptDate").toString() : this.acceptDate;
			this.lastAppNo = params.containsKey("lastAppNo") ? params.get("lastAppNo").toString() : this.lastAppNo;
		}
	}
	
	public NetValueInfEntity(NetValueTrdEntity netValueTrdModel) {
		if ( netValueTrdModel != null ) {
			this.setEvaluateDate(netValueTrdModel.getEvaluateDate());
			this.setInvestId(netValueTrdModel.getInvestId());
			this.setNetAmt(netValueTrdModel.getNetAmt());
			this.setOpDate(netValueTrdModel.getOpDate());
			this.setPlanId(netValueTrdModel.getPlanId());
			this.setQuotient(netValueTrdModel.getQuotient());
			this.setUnitValue(netValueTrdModel.getUnitValue());
		}
	}
	
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getLastAppNo() {
		return lastAppNo;
	}
	public void setLastAppNo(String lastAppNo) {
		this.lastAppNo = lastAppNo;
	}
	
	
}
