package com.opm.acct.mdt.model;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by kfzx-wanghong01 on 2016/11/23.
 */
public class MdtDetTrdModel {


	private String opDate;//操作时间 
	private String mdtSubjectType;//受托户科目  
	private String debitCreditFlag;//借贷标志
	private String tradeAmt;//交易金额
	private String trdNo;//交易流水号
	private String memo;//备注
	private String relTrdNo;//关联交易流水号
	
	@SuppressWarnings("rawtypes")
	public MdtDetTrdModel(Map params) {
		if ( params != null ) {
			if(params.containsKey("opDate")){this.opDate = (String) params.get("opDate");}
			if(params.containsKey("mdtSubjectType")){this.mdtSubjectType = (String) params.get("mdtSubjectType");}
			if(params.containsKey("debitCreditFlag")){this.debitCreditFlag = (String) params.get("debitCreditFlag");}
			if(params.containsKey("tradeAmt")){this.tradeAmt = (String) params.get("tradeAmt");}
			if(params.containsKey("trdNo")){this.trdNo = (String) params.get("trdNo");}
			if(params.containsKey("memo")){this.memo = (String) params.get("memo");}
		}
		
		
	}
	
	@SuppressWarnings("rawtypes")
	public String checkAndSetParams (Map params, MdtDetTrdModel model) {
		
		if ( params != null ) {
			StringBuilder sb = new StringBuilder();
			if (!params.containsKey("opDate") || StringUtils.isEmpty((String) params.get("opDate"))){sb.append("opDate, ");}
			model.setOpDate((String) params.get("opDate"));
			if (!params.containsKey("mdtSubjectType") || StringUtils.isEmpty((String) params.get("mdtSubjectType"))){sb.append("mdtSubjectType, ");}
			model.setMdtSubjectType((String) params.get("mdtSubjectType"));
			if (!params.containsKey("debitCreditFlag") || StringUtils.isEmpty((String) params.get("debitCreditFlag"))){sb.append("debitCreditFlag, ");}
			model.setDebitCreditFlag((String) params.get("debitCreditFlag"));
			if (!params.containsKey("tradeAmt") || StringUtils.isEmpty((String) params.get("tradeAmt"))){sb.append("tradeAmt, ");}
			model.setTradeAmt((String) params.get("tradeAmt"));
			if (params.containsKey("trdNo")){model.setRelTrdNo((String) params.get("trdNo"));}
			if(params.containsKey("memo")){model.setMemo((String) params.get("memo"));}
			
			if (StringUtils.isNotEmpty(sb.toString())) {
				return sb.toString().substring(0, sb.toString().length()-1);
			}
		}
		return null;
		
		
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
	
	public String getTrdNo() {
		return trdNo;
	}
	public void setTrdNo(String trdNo) {
		this.trdNo = trdNo;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public MdtDetTrdModel(String mdtSubjectType, String debitCreditFlag,
			String trdNo) {
		this.mdtSubjectType = mdtSubjectType;
		this.debitCreditFlag = debitCreditFlag;
		this.trdNo = trdNo;
	}

	public MdtDetTrdModel(String opDate, String mdtSubjectType, String debitCreditFlag, String tradeAmt, String trdNo,
			String memo) {
		super();
		this.opDate = opDate;
		this.mdtSubjectType = mdtSubjectType;
		this.debitCreditFlag = debitCreditFlag;
		this.tradeAmt = tradeAmt;
		this.trdNo = trdNo;
		this.memo = memo;
	}

	public MdtDetTrdModel() {
		super();
	}

	public String getRelTrdNo() {
		return relTrdNo;
	}

	public void setRelTrdNo(String relTrdNo) {
		this.relTrdNo = relTrdNo;
	}
	
	
}
