package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/12/9.
 */
public class MdtActMoRptModel {


	private String actId;//���л��˻�����                        
	private String opDate;//����ʱ��                       
	private String mdtSubjectType;//���л���Ŀ               
	private String amt;//���                          
	private String updateTime;//����ʱ��
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = opDate;
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
	public MdtActMoRptModel(String actId, String opDate, String mdtSubjectType, String amt) {
		super();
		this.actId = actId;
		this.opDate = opDate;
		this.mdtSubjectType = mdtSubjectType;
		this.amt = amt;
	}
	
	
	
}
