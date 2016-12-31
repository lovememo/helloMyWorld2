package com.opm.core.investManager.entity;

import java.util.Map;

/**
 * ��ֵ������ϸ��
 * @author kfzx-chenym
 *
 */
public class NetValueTrdEntity extends NetValueEntity {

	private String trdNo;
	
	public NetValueTrdEntity() {
		super();
	}

	public NetValueTrdEntity(Map params) {
		super(params);
		if ( params != null) {
			this.trdNo = params.containsKey("trdNo") ? params.get("trdNo").toString() : this.trdNo;
		}
	}

	public String getTrdNo() {
		return trdNo;
	}

	public void setTrdNo(String trdNo) {
		this.trdNo = trdNo;
	}
	
}
