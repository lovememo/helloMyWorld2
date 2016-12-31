package com.opm.core.paymanager.app.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.trade.AbstractTradeTask;
import com.opm.core.paymanager.model.PayTradeConstance.PAY_REPORT_STATE;
import com.opm.core.paymanager.model.PayTradeModel;
import com.opm.core.paymanager.service.PayService;

@Component
public class PayResultRegisteDoTask extends AbstractTradeTask {

	@Autowired
	PayService payService;

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PayResultRegisteDoTask.class);

	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	protected ResultMessage check(AppParam params) {
//		PayTradeModel payTradeModel=(PayTradeModel)params.get(PayTradeConstance.PAY_REPORT_OBJ);
//		if(CheckTool.isNotNull(payTradeModel.getPay_no())){
//			return ResultMessage.getWrong("payNo不能为空！");
//		}
//		if(!CheckTool.isNotNull(payTradeModel.getPay_date())){
//			return ResultMessage.getWrong("支付日期不可为空！");
//		}
//		if(!CheckTool.isNotNull(payTradeModel.getPay_inform_send_date())){
//			return ResultMessage.getWrong("支付通知发送时间不可为空！");
//		}
		
		return ResultMessage.getRight("支付划款信息校验成功！");
	}


	@Override
	protected ResultMessage doCompensate(AppParam params) {
		return null;
	}

	@Override
	@Transactional
	protected ResultMessage doTask(AppParam param) {
		String tradeNo = param.getTradeNo();
		PayTradeModel payReport = payService.queryPayReportByTradeNo(tradeNo);

		PayTradeModel updatePayTradeModel = new PayTradeModel();
		updatePayTradeModel.setPay_no(payReport.getPay_no());
		updatePayTradeModel.setPay_result_fb_date(payReport.getPay_result_fb_date());
		updatePayTradeModel.setState(PAY_REPORT_STATE.REPLYED.toString());
		int count = payService.updatePayReport(updatePayTradeModel);
		if (count == 0) {
			LOGGER.error("更新失败，tradeNo=" + tradeNo + "," + param);
			return ResultMessage.getWrong("更新失败！tradeNo:" + tradeNo + "," + param);
		}
		return ResultMessage.getRight("更新成功！");
	}

	
}
