package com.opm.core.paymanager.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opm.common.model.RequestModel;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.AppType;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.core.AppTemplete;
import com.opm.core.app.template.trade.DefaultTrade;
import com.opm.core.app.template.trade.TradeName;
import com.opm.core.common.tools.CheckTool;
import com.opm.core.paymanager.app.task.PayConfirmDoTask;
import com.opm.core.paymanager.app.task.PayConfirmPrepareTask;
import com.opm.core.paymanager.model.PayTradeConstance;
import com.opm.core.paymanager.model.PayTradeConstance.PAY_REPORT_STATE;
import com.opm.core.paymanager.model.PayTradeModel;
import com.opm.core.paymanager.service.PayService;

/**
 * 支付划款确认申请
 * @author kfzx-zhengyy1
 *
 */
@Component
public class PayConfirmApp extends AppTemplete {
	
	@Autowired
	PayService payService;
	
	@Override
	public void initTrades() {
		DefaultTrade payTrade = new DefaultTrade(TradeName.PAY_TRD).registePrepareTask(PayConfirmPrepareTask.class)
				.registeDoTask(PayConfirmDoTask.class);
		register(payTrade, null);
	}

	@Override
	protected ResultMessage legalCheck(AppParam params) {
		RequestModel requestModel = (RequestModel) params.get("requestModel");
//		if(!CheckTool.isNotNull(requestModel.getStringValue("appNo"))){
//			return ResultMessage.getWrong("申请号不应为空！");
//		}
		
		return ResultMessage.getRight("校验通过");
	}

	@Override
	protected ResultMessage beforeSubmit(SUMBIT_ACTION action, AppParam param) {
		// 获取文件导入的fileNo
		// 插申请明细
		System.out.println("beforeSubmit:" + action);
			//1、获取数据生成payReport
			RequestModel requestModel = (RequestModel) param.get("requestModel");
			PayTradeModel payTradeModel = new PayTradeModel();
			payTradeModel.setPay_no(requestModel.getStringValue("payNo"));
			payTradeModel.setPay_date(requestModel.getStringValue("payDate"));
			payTradeModel.setPay_inform_send_date(requestModel.getStringValue("payInformSendDate"));
			param.put(PayTradeConstance.PAY_REPORT_OBJ, payTradeModel);
			
			//2、插入申请的基本信息
			AppModel appModel =param.getAppModel();
			appModel.setAppUser(requestModel.getStringValue("userId"));
			appModel.setPlanId(requestModel.getStringValue("planId"));
			appModel.setAudRole(requestModel.getStringValue("defaultRoleId"));
			appModel.setPlanId(requestModel.getStringValue("planId"));
			appModel.setAppType(AppType.PAY_CONFIRM_APP.toString());
			
			return null;
	}

	@Override
	protected ResultMessage beforeEffect(AppParam params) {
		RequestModel requestModel = (RequestModel) params.get("requestModel");
		String appNo = requestModel.getStringValue("appNo");
		assert(appNo != null);
		params.getAppModel().setAppNo(appNo);
		// params.setAppModel(appModel);
		return null;
	}
	

}
