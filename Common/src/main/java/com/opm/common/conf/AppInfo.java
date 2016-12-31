package com.opm.common.conf;

/**
 * Created by kfzx-liuyz1 on 2016/10/28.
 */
public class AppInfo {

	private AppServiceEnum appServiceEnum;

	private static final AppInfo appInfo = new AppInfo();

	public AppServiceEnum getAppServiceEnum() {
		return appServiceEnum;
	}

	public void setAppServiceEnum(AppServiceEnum appServiceEnum) {
		this.appServiceEnum = appServiceEnum;
	}

	private AppInfo() {
	}

	public static AppInfo getInstance() {
		return appInfo;
	}
}
