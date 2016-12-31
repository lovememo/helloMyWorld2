package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/11/22.
 */
public class MdtActInfModel {


	private String actId;//受托户账户编码               
	private String actRate;//受托户银行存款利率
	private String actDate;//受托户核算起始日期
	private String mdtfeeRate;//受托费率
	private String trustfeeRate;//托管费率
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
	public String getActRate() {
		return actRate;
	}
	public void setActRate(String actRate) {
		this.actRate = actRate;
	}
	public String getActDate() {
		return actDate;
	}
	public void setActDate(String actDate) {
		this.actDate = actDate;
	}
	public String getMdtfeeRate() {
		return mdtfeeRate;
	}
	public void setMdtfeeRate(String mdtfeeRate) {
		this.mdtfeeRate = mdtfeeRate;
	}
	public String getTrustfeeRate() {
		return trustfeeRate;
	}
	public void setTrustfeeRate(String trustfeeRate) {
		this.trustfeeRate = trustfeeRate;
	}
        


}
