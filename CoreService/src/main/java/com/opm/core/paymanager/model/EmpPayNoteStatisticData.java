package com.opm.core.paymanager.model;

/**
 * ֧��������ϸ��Ļ�������
 * 
 * @author kfzx-zhengyy1
 *
 */
public class EmpPayNoteStatisticData {
	private String payCount;
	private String pay_bf_tax; // ˰ǰӦ�����
	private String taxed_offer_amt; // ����˰�ɷѽ��
	private String offer_amt; // �ɷ��ܽ��
	private String payable_amt; // Ӧ��˰���ö�
	private String tax_amt; // Ӧ��˰��
	private String pay_af_tax; // ˰��Ӧ�����
	public String getPay_bf_tax() {
		return pay_bf_tax;
	}
	public void setPay_bf_tax(String pay_bf_tax) {
		this.pay_bf_tax = pay_bf_tax;
	}
	public String getTaxed_offer_amt() {
		return taxed_offer_amt;
	}
	public void setTaxed_offer_amt(String taxed_offer_amt) {
		this.taxed_offer_amt = taxed_offer_amt;
	}
	public String getOffer_amt() {
		return offer_amt;
	}
	public void setOffer_amt(String offer_amt) {
		this.offer_amt = offer_amt;
	}
	public String getPayable_amt() {
		return payable_amt;
	}
	public void setPayable_amt(String payable_amt) {
		this.payable_amt = payable_amt;
	}
	public String getTax_amt() {
		return tax_amt;
	}
	public void setTax_amt(String tax_amt) {
		this.tax_amt = tax_amt;
	}
	public String getPay_af_tax() {
		return pay_af_tax;
	}
	public void setPay_af_tax(String pay_af_tax) {
		this.pay_af_tax = pay_af_tax;
	}
	
	public String getPayCount() {
		return payCount;
	}
	public void setPayCount(String payCount) {
		this.payCount = payCount;
	}
	@Override
	public String toString() {
		return "EmpPayNoteStatisticData [payCount=" + payCount + ", pay_bf_tax=" + pay_bf_tax + ", taxed_offer_amt="
				+ taxed_offer_amt + ", offer_amt=" + offer_amt + ", payable_amt=" + payable_amt + ", tax_amt=" + tax_amt
				+ ", pay_af_tax=" + pay_af_tax + "]";
	}

}
