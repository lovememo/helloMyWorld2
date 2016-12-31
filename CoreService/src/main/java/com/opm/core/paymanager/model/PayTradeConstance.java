package com.opm.core.paymanager.model;

public class PayTradeConstance {
	public static enum PAY_REPORT_STATE{
		UNCONFIRM("待确认"),UNREPLY("已确认、待反馈"),REPLYED("已反馈"), NONE("无");
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
