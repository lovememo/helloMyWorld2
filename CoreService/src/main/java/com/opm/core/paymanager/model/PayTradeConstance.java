package com.opm.core.paymanager.model;

public class PayTradeConstance {
	public static enum PAY_REPORT_STATE{
		UNCONFIRM("��ȷ��"),UNREPLY("��ȷ�ϡ�������"),REPLYED("�ѷ���"), NONE("��");
		String value;
		
		PAY_REPORT_STATE(String value){
			this.value = value;
		}
		
		@Override
		public String toString() {
			return value;
		}
	};
	
	public static final String PAY_REPORT_OBJ="PAY_REPORT_OBJ";

}
