package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/12/9.
 */
public class MdtActRelModel {

	private String actId;//受托户账户编码        
	private String relId;//关联编码
	private String relType;//关联类型         
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
	public String getRelId() {
		return relId;
	}
	public void setRelId(String relId) {
		this.relId = relId;
	}
	public String getRelType() {
		return relType;
	}
	public void setRelType(String relType) {
		this.relType = relType;
	}
	
}
