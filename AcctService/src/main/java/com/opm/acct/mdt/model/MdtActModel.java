package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/11/27.
 */
public class MdtActModel {

	private String actId;//受托户账户编码        
	private String mdtSubjectType;//受托户科目
	private String amt;//余额           
	private String updateTime;//更新时间    
	
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
