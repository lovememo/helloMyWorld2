package com.opm.core.app.model;

import java.util.Map;

import com.opm.core.app.dic.TrdOpFlag;

/**
 * core��������
 * @author kfzx-chenym
 *
 */
public class TrdModel {
	
	String trdNo; //������ˮ��
	String trdType; //��������
	String trdState; //����״̬
	String updateTime; //����ʱ��
	TrdOpFlag trdOpFlag; //TODO Q�����������Ҫһ��ö���ࣿ
	
	public TrdModel(){}
	
	public TrdModel(Map params){
		if ( params != null ) {
			this.trdNo = params.containsKey("trdNo") ? params.get("trdNo").toString() : this.trdNo;
			this.trdType = params.containsKey("trdType") ? params.get("trdType").toString() : this.trdType;
			this.trdState = params.containsKey("trdState") ? params.get("trdState").toString() : this.trdState;
		}
	}
	
	public String getTrdNo() {
		return trdNo;
	}
	public void setTrdNo(String trdNo) {
		this.trdNo = trdNo;
	}
	public String getTrdType() {
		return trdType;
	}
	public void setTrdType(String trdType) {
		this.trdType = trdType;
	}
	public String getTrdState() {
		return trdState;
	}
	public void setTrdState(String trdState) {
		this.trdState = trdState;
	}
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public TrdOpFlag getTrdOpFlag() {
		return trdOpFlag;
	}
	public void setTrdOpFlag(TrdOpFlag trdOpFlag) {
		this.trdOpFlag = trdOpFlag;
	}
	

	
}
