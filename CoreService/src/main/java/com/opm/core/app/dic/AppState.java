package com.opm.core.app.dic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum AppState {
	
	SAVE("1","����"),AUDITING("2","�����"),AGREE("8","ͬ��"),DENY("9","���"),DELETE("10","ɾ��");
	
	String value;
	String text;
	AppState(String value,String text){
		this.value = value;
		this.text=text;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	public String getCode() {
		return value;
	}
	
	private Map<String,String> getMap(){
		Map<String,String> mp=new HashMap<String,String>();
		mp.put("text", text);
		mp.put("value", value);
		mp.put("title", text);
		return mp;
	}
	public static List<Map<String,String>> getList(){
		List<Map<String,String>> ls=new ArrayList<Map<String,String>>();
		for(AppState stt :AppState.values()){
			if(stt !=AppState.DELETE){
				ls.add(stt.getMap());}
		}
		return ls;
	}

}
