package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/11/23.
 */
public class MdtAdjActTrdModel {
	
	private String seqId;//���
	private String trdNo;//������ˮ��
	private String mdtAddActType;//��������(1 ���˲��� 2����ҵ����)
	private String opDate;//��������
	private String mdtAddOpType;//ҵ������(�ֵ�ֵ MDT_ADD_OP_TYPE)
	private String reason;//����ԭ��
	private String mdtSubjectType;//���л���Ŀ
	private String debitCreditFlag;//�����־
	private String tradeAmt;//���׽��
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	public String getTrdNo() {
		return trdNo;
	}
	public void setTrdNo(String trdNo) {
		this.trdNo = trdNo;
	}
	public String getMdtAddActType() {
		return mdtAddActType;
	}
	public void setMdtAddActType(String mdtAddActType) {
		this.mdtAddActType = mdtAddActType;
	}
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}
	public String getMdtAddOpType() {
		return mdtAddOpType;
	}
	public void setMdtAddOpType(String mdtAddOpType) {
		this.mdtAddOpType = mdtAddOpType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	public MdtAdjActTrdModel(String trdNo, String mdtAddActType, String opDate, String mdtAddOpType,
			String reason, String mdtSubjectType, String debitCreditFlag, String tradeAmt) {
		super();
		this.trdNo = trdNo;
		this.mdtAddActType = mdtAddActType;
		this.opDate = opDate;
		this.mdtAddOpType = mdtAddOpType;
		this.reason = reason;
		this.mdtSubjectType = mdtSubjectType;
		this.debitCreditFlag = debitCreditFlag;
		this.tradeAmt = tradeAmt;
	}
	public MdtAdjActTrdModel(String seqId, String trdNo) {
		super();
		this.seqId = seqId;
		this.trdNo = trdNo;
	}
          

	
}
