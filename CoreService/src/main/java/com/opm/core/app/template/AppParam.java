package com.opm.core.app.template;

import java.util.HashMap;
import java.util.Map;

import com.opm.core.app.model.AppModel;
import com.opm.core.app.template.core.IAppOpAction.SUMBIT_ACTION;

public class AppParam {
	private Map<String, Object> map = new HashMap<String, Object>();
	private Object selfParam;
	// private String appNo;
	// private String appType;
	private String tradeNo;
	private AppModel appModel;
	private String fileNo;
	private SUMBIT_ACTION submitAction;

	public AppParam(Object obj) {
		this.selfParam = obj;
		this.appModel = new AppModel();
	}

	public AppParam(AppModel appModel) {
		this.selfParam = null;
		this.appModel = appModel;
	}

	public AppParam() {
	}

	public AppParam put(String key, Object value) {
		map.put(key, value);
		return this;
	}

	public Object get(String key) {
		return map.get(key);
	}

	public Object getSelfParam() {
		return selfParam;
	}

	public void setSelfParam(Object selfParam) {
		this.selfParam = selfParam;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public AppModel getAppModel() {
		return appModel;
	}

	/**
	 * 慎用！不要轻易new一个AppModel覆盖,尽量取现成的使用
	 * 
	 * @param appModel
	 */
	@Deprecated
	public void setAppModel(AppModel appModel) {
		this.appModel = appModel;
	}

	public SUMBIT_ACTION getSubmitAction() {
		return submitAction;
	}

	public void setSubmitAction(SUMBIT_ACTION submitAction) {
		this.submitAction = submitAction;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	@Override
	public String toString() {
		return "AppParam [map=" + map + ", tradeNo=" + tradeNo + ", appModel=" + appModel
				+ ", fileNo=" + fileNo + ", submitAction=" + submitAction + "]";
	}
}
