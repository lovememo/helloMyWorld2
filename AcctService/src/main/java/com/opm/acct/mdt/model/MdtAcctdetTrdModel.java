package com.opm.acct.mdt.model;

/**
 * Created by kfzx-wanghong01 on 2016/11/28.
 */
public class MdtAcctdetTrdModel {

	    private String seqId;//序号 
		private String lineNum;//行号
		private String actId;//受托户账户编码
		private String opDate;//交易日期
		private String opTime;//交易时间 
		private String inTradeAmt;//转入金额         
		private String outTradeAmt;//转出金额        
		private String amt;//余额                
		private String amtCal;//计算的余额             
		private String oppAcctNo;//对方账号          
		private String oppAcctName;//对方户名        
		private String confirmState;//确认状态       
		private String dealType;//交易类型           
		private String summary1;//摘要1           
		private String summary2;//摘要2           
		private String memo;//备注               
		private String checkFlag;//校验标志          
		private String checkMemo;//校验信息    
		private String trdNo;//交易流水号
		public String getSeqId() {
			return seqId;
		}
		public void setSeqId(String seqId) {
			this.seqId = seqId;
		}
		public String getLineNum() {
			return lineNum;
		}
		public void setLineNum(String lineNum) {
			this.lineNum = lineNum;
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
		public String getCheckFlag() {
			return checkFlag;
		}
		public void setCheckFlag(String checkFlag) {
			this.checkFlag = checkFlag;
		}
		public String getCheckMemo() {
			return checkMemo;
		}
		public void setCheckMemo(String checkMemo) {
			this.checkMemo = checkMemo;
		}
		public String getTrdNo() {
			return trdNo;
		}
		public void setTrdNo(String trdNo) {
			this.trdNo = trdNo;
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
		public String getAmtCal() {
			return amtCal;
		}
		public void setAmtCal(String amtCal) {
			this.amtCal = amtCal;
		}
		public MdtAcctdetTrdModel(String trdNo, String seqId, String checkMemo) {
			super();
			this.trdNo = trdNo;
			this.seqId = seqId;
			this.checkMemo = checkMemo;
		}
		public MdtAcctdetTrdModel(String seqId, String actId, String opDate, String dealType, String memo,
				String trdNo) {
			super();
			this.seqId = seqId;
			this.actId = actId;
			this.opDate = opDate;
			this.dealType = dealType;
			this.memo = memo;
			this.trdNo = trdNo;
		}
		public MdtAcctdetTrdModel(String lineNum, String opDate, String opTime, String outTradeAmt, String inTradeAmt,
				String amt, String oppAcctName, String oppAcctNo, String summary1,
				String summary2, String dealType, String confirmState, String memo) {
			super();
			this.lineNum = lineNum;
			this.opDate = opDate;
			this.opTime = opTime;
			this.inTradeAmt = inTradeAmt;
			this.outTradeAmt = outTradeAmt;
			this.amt = amt;
			this.oppAcctNo = oppAcctNo;
			this.oppAcctName = oppAcctName;
			this.confirmState = confirmState;
			this.dealType = dealType;
			this.summary1 = summary1;
			this.summary2 = summary2;
			this.memo = memo;
		}
		public MdtAcctdetTrdModel(String seqId, String lineNum, String actId, String opDate, String opTime,
				String inTradeAmt, String outTradeAmt, String amt, String amtCal, String oppAcctNo, String oppAcctName,
				String confirmState, String dealType, String summary1, String summary2, String memo, String checkFlag,
				String checkMemo, String trdNo) {
			super();
			this.seqId = seqId;
			this.lineNum = lineNum;
			this.actId = actId;
			this.opDate = opDate;
			this.opTime = opTime;
			this.inTradeAmt = inTradeAmt;
			this.outTradeAmt = outTradeAmt;
			this.amt = amt;
			this.amtCal = amtCal;
			this.oppAcctNo = oppAcctNo;
			this.oppAcctName = oppAcctName;
			this.confirmState = confirmState;
			this.dealType = dealType;
			this.summary1 = summary1;
			this.summary2 = summary2;
			this.memo = memo;
			this.checkFlag = checkFlag;
			this.checkMemo = checkMemo;
			this.trdNo = trdNo;
		}
		public MdtAcctdetTrdModel() {
			super();
		}
		
		
}
