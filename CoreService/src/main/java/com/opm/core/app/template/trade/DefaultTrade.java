package com.opm.core.app.template.trade;

/**
 * Ĭ�ϵ�tradeʵ�֣�һ��������������OK��
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
	 * ע��prepare�׶εĽ���
	 * @param task
	 * @param param �ò���Ӧ��ֻ��һЩ�򵥵ľ�̬�Ĳ���,��ӦTaskParam.selfParam
	 * @return
	 */
	public DefaultTrade registePrepareTask(Class<? extends AbstractTradeTask> task, Object param) {
		setPrepareTask(task);
		super.prepareParam = param;
		return this;
	}

	/**
	 * ע��do�׶εĽ���
	 * @param task
	 * @param param �ò���Ӧ��ֻ��һЩ�򵥵ľ�̬�Ĳ���,��ӦTaskParam.selfParam
	 * @return
	 */
	public DefaultTrade registeDoTask(Class<? extends AbstractTradeTask> task, Object param) {
		setDoTask(task);
		super.doParam = param;
		return this;
	}

	/**
	 * ע��prepare�׶εĽ���
	 * @param task
	 * @return
	 */
	public DefaultTrade registePrepareTask(Class<? extends AbstractTradeTask> task) {
		return registePrepareTask(task, null);
	}

	/**
	 * ע��do�׶εĽ���
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
