package com.opm.core.app.dic;

public enum TrdState {
	PREPARED("PREPARED"),SUCCEEDED("SUCCEEDED"),FAILED("FAILED"),CANCELED("CANCELED"),COMPENSATED("COMPENSATED");
	
	String value;
	TrdState(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
