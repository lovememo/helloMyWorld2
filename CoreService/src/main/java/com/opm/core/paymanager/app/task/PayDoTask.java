package com.opm.core.paymanager.app.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.trade.AbstractTradeTask;
import com.opm.core.paymanager.model.PayTradeConstance.PAY_REPORT_STATE;
import com.opm.core.paymanager.service.PayService;

@Component
public class PayDoTask extends AbstractTradeTask {

	@Autowired
	PayService payService;

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PayDoTask.class);

	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	protected ResultMessage check(AppParam param) {
		return null;
	}

	@Override
	protected ResultMessage doCompensate(AppParam param) {
		return null;

	}

	@Override
	protected ResultMessage doTask(AppParam param) {
		String tradeNo = param.getTradeNo();
		System.out.println("taskParam:" + tradeNo);
//		int count = payService.updatePayTradeStatus(tradeNo, PAY_REPORT_STATE.UNCONFIRM);
		payService.payRegisteEffect(tradeNo);
//		if (count == 0) {
//			LOGGER.error("更新失败，tradeNo=" + tradeNo + "," + param);
//			return ResultMessage.getWrong("更新失败！tradeNo:" + tradeNo + "," + param);
//		}
		return ResultMessage.getWrong("更新成功！");
	}
}
