package com.opm.core.app.template;

import com.opm.common.business.param.RetResult;

public class ResultMessage {
	private boolean right;
	private String msg;
	
	public ResultMessage (RetResult ret){
		this.right = ret.getResult();
		this.msg = ret.getReason();
	}
	private ResultMessage(boolean r,String msg){
		this.right = r;
		this.msg = msg;
	}
	
	public static ResultMessage getRight(String msg){
		return new ResultMessage(true, msg);
	}

	public static ResultMessage getWrong(String msg){
		return new ResultMessage(false, msg);
	}

	public boolean isRight() {
		return right;
	}

	public String getMsg() {
		return msg;
	}
	

}
