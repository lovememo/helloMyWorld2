package com.opm.core.paymanager.app.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opm.common.model.RequestModel;
import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.trade.AbstractTradeTask;
import com.opm.core.common.tools.CheckTool;
import com.opm.core.common.tools.CompareTool;
import com.opm.core.paymanager.model.EmpPayNoteStatisticData;
import com.opm.core.paymanager.model.PayTradeConstance;
import com.opm.core.paymanager.model.PayTradeModel;
import com.opm.core.paymanager.service.PayService;

@Component
public class PayConfirmPrepareTask extends AbstractTradeTask {

	@Autowired
	PayService payService;

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PayConfirmPrepareTask.class);

	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	protected ResultMessage check(AppParam params) {
//		这里因为数据还没有插入表，所以只能校验一些前台录入的数据
//		String tradeNo = params.getTradeNo();
		PayTradeModel payTradeModel=(PayTradeModel)params.get(PayTradeConstance.PAY_REPORT_OBJ);
		if(CheckTool.isNotNull(payTradeModel.getPay_no())){
			return ResultMessage.getWrong("payNo不能为空！");
		}
		if(!CheckTool.isNotNull(payTradeModel.getPay_date())){
			return ResultMessage.getWrong("支付日期不可为空！");
		}
		if(!CheckTool.isNotNull(payTradeModel.getPay_inform_send_date())){
			return ResultMessage.getWrong("支付通知发送时间不可为空！");
		}
		
		return ResultMessage.getRight("支付划款信息校验成功！");
	}


	@Override
	protected ResultMessage doCompensate(AppParam params) {
		return null;
	}

	@Override
	protected ResultMessage doTask(AppParam params) {

		//1.1、插入数据
		String tradeNo = params.getTradeNo();
		PayTradeModel paramPayTradeModel=(PayTradeModel)params.get(PayTradeConstance.PAY_REPORT_OBJ);
//		RequestModel requestModel = (RequestModel) params.get("requestModel");
//		PayTradeModel payTradeModel = (PayTradeModel) params.get(PayTradeConstance.PAY_REPORT_OBJ);
//		String payNo = requestModel.getStringValue("payNo");
		PayTradeModel queryPayReport = payService.queryPayReportRecByPayNo(paramPayTradeModel.getPay_no());
		queryPayReport.setTrd_no(tradeNo);
//		queryPayReport.setPay_no(null);
		queryPayReport.setPay_date(paramPayTradeModel.getPay_date());
		queryPayReport.setPay_inform_send_date(paramPayTradeModel.getPay_inform_send_date());
		
		payService.insertPayTradeInfo(queryPayReport);
//		param.put(PayTradeConstance.PAY_REPORT_OBJ, queryPayReport);
		return  ResultMessage.getRight("prepare完成");
	}

	
}
