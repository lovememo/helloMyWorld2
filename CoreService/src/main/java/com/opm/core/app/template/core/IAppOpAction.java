package com.opm.core.app.template.core;

import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;

public interface IAppOpAction {
	
	public enum SUMBIT_ACTION {
		SAVE, SUBMIT,ASYNC_SUBMIT,ASYNC_CALLBACK
	}

	public enum AUD_ACTION{
		AGREE,DENY
	}

	/**
	 * 初始化交易，理论上说，只应该定义静态的交易顺序
	 */
 void initTrades();
	
	/**
	 * 设置申请类型，请在AppType类中配置
	 * @return 申请类型编码
	 */
//	String getAppTypeNo();
	/**
	 * 提交，在controller中调用<br>
	 * 
	 * @param action
	 * @param params 这个参数会直接传到第一个trade中
	 */
	ResultMessage submit(SUMBIT_ACTION action,AppParam params);

	/**
	 * 复核
	 */
	ResultMessage aud(AUD_ACTION audAction,AppParam params);

	/**
	 * 生效
	 * @param params
	 */
	ResultMessage effect(AppParam params);
}
