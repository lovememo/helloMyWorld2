package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/11/27.
 */
public class MdtActModel {

	private String actId;//���л��˻�����        
	private String mdtSubjectType;//���л���Ŀ
	private String amt;//���           
	private String updateTime;//����ʱ��    
	
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
	public String getMdtSubjectType() {
		return mdtSubjectType;
	}
	public void setMdtSubjectType(String mdtSubjectType) {
		this.mdtSubjectType = mdtSubjectType;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}


}
