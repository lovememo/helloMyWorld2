package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/11/27.
 */
public class MdtDetModel {

	private String seqId;//���
	private String actId;//���л��˻�����
	private String opDate;//����ʱ�� 
	private String mdtSubjectType;//���л���Ŀ  
	private String debitCreditFlag;//�����־
	private String tradeAmt;//���׽��
	private String trdNo;//������ˮ��
	private String memo;//��ע
	private String sysTime;//ϵͳʱ��
	
	

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
	public String getTrdNo() {
		return trdNo;
	}
	public void setTrdNo(String trdNo) {
		this.trdNo = trdNo;
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
	public String getDebitCreditFlag() {
		return debitCreditFlag;
	}
	public void setDebitCreditFlag(String debitCreditFlag) {
		this.debitCreditFlag = debitCreditFlag;
	}
	public String getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getSysTime() {
		return sysTime;
	}
	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}
	
}
