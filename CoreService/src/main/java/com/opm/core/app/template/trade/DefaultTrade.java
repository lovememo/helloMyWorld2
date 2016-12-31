package com.opm.core.app.template.trade;

/**
 * 默认的trade实现，一般情况下用这个就OK了
 * @author kfzx-zhengyy1
 *
 */
public class DefaultTrade extends AbstractTrade {

	private String tradeName;

	public DefaultTrade(Class<? extends AbstractTradeTask> prepareTask, Class<? extends AbstractTradeTask> doTask) {
		setPrepareTask(prepareTask);
		setDoTask(doTask);
	}

	public DefaultTrade(String tradeName) {
		this.tradeName = tradeName;
	}

	/**
	 * 注册prepare阶段的交易
	 * @param task
	 * @param param 该参数应该只是一些简单的静态的参数,对应TaskParam.selfParam
	 * @return
	 */
	public DefaultTrade registePrepareTask(Class<? extends AbstractTradeTask> task, Object param) {
		setPrepareTask(task);
		super.prepareParam = param;
		return this;
	}

	/**
	 * 注册do阶段的交易
	 * @param task
	 * @param param 该参数应该只是一些简单的静态的参数,对应TaskParam.selfParam
	 * @return
	 */
	public DefaultTrade registeDoTask(Class<? extends AbstractTradeTask> task, Object param) {
		setDoTask(task);
		super.doParam = param;
		return this;
	}

	/**
	 * 注册prepare阶段的交易
	 * @param task
	 * @return
	 */
	public DefaultTrade registePrepareTask(Class<? extends AbstractTradeTask> task) {
		return registePrepareTask(task, null);
	}

	/**
	 * 注册do阶段的交易
	 * @param task
	 * @return
	 */
	public DefaultTrade registeDoTask(Class<? extends AbstractTradeTask> task) {
		return registeDoTask(task, null);
	}

	public DefaultTrade setPrepareTaskParam(Object param){
		super.prepareParam = param;
		return this;
	}

	public DefaultTrade setDoTaskParam(Object param){
		super.doParam = param;
		return this;
	}

	@Override
	public void tradeCheck() {

	}

	@Override
	public String getTradeName() {
		return tradeName;
	}
}
