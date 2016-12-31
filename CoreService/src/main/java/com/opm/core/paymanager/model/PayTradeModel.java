package com.opm.core.paymanager.model;

/**
 * 支付划款登记交易信息表
 * 
 * @author kfzx-zhengyy1
 *
 */
public class PayTradeModel {
	String isrepay;
	String pay_date;
	String pay_count;
	String amt;
	String amt_af_tax;
	String tax_amt;
	String state;
	String trd_no;
	String pay_no;
	String pay_inform_date;
	String pay_inform_send_date;
	String pay_result_fb_date;
	String record_time;
	String update_time;

	public String getIsrepay() {
		return isrepay;
	}

	public PayTradeModel setIsrepay(String isrepay) {
		this.isrepay = isrepay;
		return this;
	}

	public String getPay_date() {
		return pay_date;
	}

	public PayTradeModel setPay_date(String pay_date) {
		this.pay_date = pay_date;
		return this;
	}

	public String getPay_count() {
		return pay_count;
	}

	public PayTradeModel setPay_count(String pay_count) {
		this.pay_count = pay_count;
		return this;
	}

	public String getAmt() {
		return amt;
	}

	public PayTradeModel setAmt(String amt) {
		this.amt = amt;
		return this;
	}

	public String getAmt_af_tax() {
		return amt_af_tax;
	}

	public PayTradeModel setAmt_af_tax(String amt_af_tax) {
		this.amt_af_tax = amt_af_tax;
		return this;
	}

	public String getTax_amt() {
		return tax_amt;
	}

	public PayTradeModel setTax_amt(String tax_amt) {
		this.tax_amt = tax_amt;
		return this;
	}

	public String getState() {
		return state;
	}

	public PayTradeModel setState(String state) {
		this.state = state;
		return this;
	}

	public String getTrd_no() {
		return trd_no;
	}

	public PayTradeModel setTrd_no(String trd_no) {
		this.trd_no = trd_no;
		return this;
	}

	public String getPay_inform_date() {
		return pay_inform_date;
	}

	public PayTradeModel setPay_inform_date(String pay_inform_date) {
		this.pay_inform_date = pay_inform_date;
		return this;
	}

	public String getPay_inform_send_date() {
		return pay_inform_send_date;
	}

	public PayTradeModel setPay_inform_send_date(String pay_inform_send_date) {
		this.pay_inform_send_date = pay_inform_send_date;
		return this;
	}

	public String getPay_no() {
		return pay_no;
	}

	public void setPay_no(String pay_no) {
		this.pay_no = pay_no;
	}

	public String getRecord_time() {
		return record_time;
	}

	public void setRecord_time(String record_time) {
		this.record_time = record_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getPay_result_fb_date() {
		return pay_result_fb_date;
	}

	public void setPay_result_fb_date(String pay_result_fb_date) {
		this.pay_result_fb_date = pay_result_fb_date;
	}

	@Override
	public String toString() {
		return "PayTradeModel [isrepay=" + isrepay + ", pay_date=" + pay_date + ", pay_count=" + pay_count + ", amt="
				+ amt + ", amt_af_tax=" + amt_af_tax + ", tax_amt=" + tax_amt + ", state=" + state + ", trd_no="
				+ trd_no + ", pay_no=" + pay_no + ", pay_inform_date=" + pay_inform_date + ", pay_inform_send_date="
				+ pay_inform_send_date + ", pay_result_fb_date=" + pay_result_fb_date + ", record_time=" + record_time
				+ ", update_time=" + update_time + "]";
	}

}
