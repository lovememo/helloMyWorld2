package com.opm.core.app.template.task;

import org.springframework.stereotype.Component;

import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.trade.AbstractTradeTask;

/**
 * ¿ÕµÄ
 * @author kfzx-zhengyy1
 *
 */
@Component
public class EmptyAppTradeTask extends AbstractTradeTask{

	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	protected ResultMessage check(AppParam appParam) {
		return null;
	}

	@Override
	protected ResultMessage doCompensate(AppParam appParam) {
		return null;
	}

	@Override
	protected ResultMessage doTask(AppParam appParam) {
		return null;
	}

}
