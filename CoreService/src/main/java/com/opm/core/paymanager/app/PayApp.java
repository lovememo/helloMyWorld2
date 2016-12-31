package com.opm.core.paymanager.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opm.common.model.RequestModel;
import com.opm.core.app.dic.AppState;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.AppType;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.core.AppTemplete;
import com.opm.core.app.template.trade.DefaultTrade;
import com.opm.core.app.template.trade.TradeName;
import com.opm.core.common.tools.CheckTool;
import com.opm.core.feign.IFIleClient;
import com.opm.core.paymanager.app.task.PayDoTask;
import com.opm.core.paymanager.app.task.PayPrepareTask;
import com.opm.core.paymanager.model.PayTradeConstance;
import com.opm.core.paymanager.model.PayTradeModel;
import com.opm.core.paymanager.service.PayService;

/**
 * 支付划款登记申请
 * 
 * @author kfzx-zhengyy1
 *
 */
@Component
public class PayApp extends AppTemplete {

	@Autowired
	PayService payService;

	@Override
	public void initTrades() {
		DefaultTrade payTrade = new DefaultTrade(TradeName.PAY_TRD).registePrepareTask(PayPrepareTask.class)
				.registeDoTask(PayDoTask.class);
		register(payTrade, null);
	}

	@Override
	protected ResultMessage legalCheck(AppParam params) {

		return ResultMessage.getRight("校验通过");
	}

	@Override
	protected ResultMessage beforeSubmit(SUMBIT_ACTION action, AppParam param) {
		// 获取文件导入的fileNo
		// 插申请明细
		RequestModel requestModel = (RequestModel) param.get("requestModel");
		System.out.println("beforeSubmit:" + action);
		if (action == SUMBIT_ACTION.ASYNC_SUBMIT) {
			// 0、校驗
			// if(CheckTool)
			if (!CheckTool.isNotNull(requestModel.getStringValue("isrepy"))) {
				return ResultMessage.getWrong("是否重新支付字段不应为空！");
			}
			if (!CheckTool.isNotNull(requestModel.getStringValue("payCount"))) {
				return ResultMessage.getWrong("支付笔数不应为空！");
			}
			if (!CheckTool.isNotNull(requestModel.getStringValue("payAmt"))) {
				return ResultMessage.getWrong("支付总金额字段不应为空！");
			}
			if (!CheckTool.isNotNull(requestModel.getStringValue("payAfTaxAmt"))) {
				return ResultMessage.getWrong("税后支付金额字段不应为空！");
			}
			if (!CheckTool.isNotNull(requestModel.getStringValue("payableAmt"))) {
				return ResultMessage.getWrong("应纳税金字段不应为空！");
			}
			// 1、获取数据生成payReport
			PayTradeModel payReportModel = new PayTradeModel();
			payReportModel.setIsrepay(requestModel.getStringValue("isrepy"))
					.setPay_inform_date(requestModel.getStringValue("payInformDate"))
					.setPay_count(requestModel.getStringValue("payCount")).setAmt(requestModel.getStringValue("payAmt"))
					.setAmt_af_tax(requestModel.getStringValue("payAfTaxAmt"))
					.setTax_amt(requestModel.getStringValue("payableAmt"));

			param.put(PayTradeConstance.PAY_REPORT_OBJ, payReportModel);

			// 2、获取文件号，传给trade
			String fileNo = requestModel.getStringValue("fileNo");
			if (!CheckTool.isNotNull(fileNo)) {
				return ResultMessage.getWrong("未获取到导入文件的信息！");
			}
			System.out.println("PayApp 获取FILENO:" + fileNo);
			param.setFileNo(fileNo);

			// 3、插入申请的基本信息
			AppModel appModel = param.getAppModel();
			appModel.setAppUser(requestModel.getStringValue("userId"));
			appModel.setPlanId(requestModel.getStringValue("planId"));
			appModel.setAudRole(requestModel.getStringValue("defaultRoleId"));
			appModel.setAppType(AppType.PAY_APP.toString());
			// 提交成功后默认会变成待审核，由于异步生效，这边显示指定下变成保存状态。
			appModel.setAppState(AppState.SAVE.toString());

		} else if (action == SUMBIT_ACTION.ASYNC_CALLBACK) {
			// 2、获取文件号，传给trade
//			String fileNo = requestModel.getStringValue("fileNo");
			String fileNo = requestModel.getStringValue("serialNo");
			if (CheckTool.isNull(fileNo)) {
				return ResultMessage.getWrong("fileNo为空，程序终止！");
			}
			param.setFileNo(fileNo);
		}
		return ResultMessage.getRight("ok");
	}

	@Override
	protected ResultMessage afterSubmit(SUMBIT_ACTION action, AppParam params) {

		return ResultMessage.getRight("ok");
	}


	@Override
	protected ResultMessage beforeEffect(AppParam params) {
		AppModel appModel = params.getAppModel();
		RequestModel requestModel = (RequestModel) params.get("requestModel");
		appModel.setAppNo(requestModel.getStringValue("appNo"));

		return ResultMessage.getRight("ok");
	}

}
