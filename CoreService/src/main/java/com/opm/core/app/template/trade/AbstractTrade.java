package com.opm.core.app.template.trade;

import com.opm.common.business.task.ITask;

/**
 * 自定义trade的实现类，用于用户实现trade
 * @author kfzx-zhengyy1
 *
 */
public abstract class AbstractTrade implements  ITrade {

	private String tradeNo;
	private Class<? extends ITask> prepareTask =  EmptyTradeTask.class;
	private Class<? extends ITask> doTask =  EmptyTradeTask.class;
	Object prepareParam;
	Object doParam;
	
	/**
	 * 获取交易序列号
	 * @return
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	public Class<? extends ITask> getPrepareTask() {
		return prepareTask;
	}

	public ITrade setPrepareTask(Class<? extends ITask> prepareTask) {
		this.prepareTask = prepareTask;
		return this;
	}

	
	public Class<? extends ITask> getDoTask() {
		return doTask;
	}

	public ITrade setDoTask(Class<? extends ITask> doTask) {
		this.doTask = doTask;
		return this;
	}

}
