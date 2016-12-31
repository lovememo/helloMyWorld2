package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/11/22.
 */
public class MdtAcctdetHisModel {


    private String seqId;//序号
    private String actId;//受托户账户编码
    private String opDate;//交易日期
    private String opTime;//交易时间
    private String inTradeAmt;//转入金额
    private String outTradeAmt;//转出金额
    private String amt;//余额
    private String oppAcctNo;//对方账号
    private String oppAcctName;//对方户名
    private String confirmState;//确认状态
    private String dealType;//交易类型 字典值MDT_DEAL_TYPE
    private String summary1;//摘要1
    private String summary2;//摘要2
    private String memo;//备注
    private String updateTime;//更新时间
    private String validFlag;//有效标志
    private String trdNo;//交易流水号
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
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
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public String getInTradeAmt() {
		return inTradeAmt;
	}
	public void setInTradeAmt(String inTradeAmt) {
		this.inTradeAmt = inTradeAmt;
	}
	public String getOutTradeAmt() {
		return outTradeAmt;
	}
	public void setOutTradeAmt(String outTradeAmt) {
		this.outTradeAmt = outTradeAmt;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getOppAcctNo() {
		return oppAcctNo;
	}
	public void setOppAcctNo(String oppAcctNo) {
		this.oppAcctNo = oppAcctNo;
	}
	public String getOppAcctName() {
		return oppAcctName;
	}
	public void setOppAcctName(String oppAcctName) {
		this.oppAcctName = oppAcctName;
	}
	public String getConfirmState() {
		return confirmState;
	}
	public void setConfirmState(String confirmState) {
		this.confirmState = confirmState;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getSummary1() {
		return summary1;
	}
	public void setSummary1(String summary1) {
		this.summary1 = summary1;
	}
	public String getSummary2() {
		return summary2;
	}
	public void setSummary2(String summary2) {
		this.summary2 = summary2;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	public String getTrdNo() {
		return trdNo;
	}
	public void setTrdNo(String trdNo) {
		this.trdNo = trdNo;
	}

    
}
