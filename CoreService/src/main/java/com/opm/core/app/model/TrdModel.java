package com.opm.core.app.model;

import java.util.Map;

import com.opm.core.app.dic.TrdOpFlag;

/**
 * core交易主表
 * @author kfzx-chenym
 *
 */
public class TrdModel {
	
	String trdNo; //交易流水号
	String trdType; //交易类型
	String trdState; //交易状态
	String updateTime; //更新时间
	TrdOpFlag trdOpFlag; //TODO Q：申请操作需要一个枚举类？
	
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
