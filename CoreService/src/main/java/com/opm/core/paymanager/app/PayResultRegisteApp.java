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
import com.opm.core.paymanager.app.task.PayResultRegisteDoTask;
import com.opm.core.paymanager.app.task.PayResultRegistePrepareTask;
import com.opm.core.paymanager.model.PayTradeConstance;
import com.opm.core.paymanager.model.PayTradeModel;
import com.opm.core.paymanager.service.PayService;

/**
 * ֧������Ǽ�����
 * @author kfzx-zhengyy1
 *
 */
@Component
public class PayResultRegisteApp extends AppTemplete {
	
	@Autowired
	PayService payService;
	
	@Override
	public void initTrades() {
		DefaultTrade payTrade = new DefaultTrade(TradeName.PAY_TRD).registePrepareTask(PayResultRegistePrepareTask.class)
				.registeDoTask(PayResultRegisteDoTask.class);
		register(payTrade, null);
	}

	@Override
	protected ResultMessage legalCheck(AppParam params) {
//		RequestModel requestModel = (RequestModel) params.get("requestModel");
		
		return ResultMessage.getRight("У��ͨ��");
	}

	@Override
	protected ResultMessage beforeSubmit(SUMBIT_ACTION action, AppParam param) {
		// ��ȡ�ļ������fileNo
		// ��������ϸ
		System.out.println("beforeSubmit:" + action);
			//1����ȡ��������payReport
			RequestModel requestModel = (RequestModel) param.get("requestModel");
			PayTradeModel payTradeModel = new PayTradeModel();
			payTradeModel.setPay_no(requestModel.getStringValue("payNo"));
			payTradeModel.setPay_result_fb_date(requestModel.getStringValue("payResultFeedbackDate"));
			param.put(PayTradeConstance.PAY_REPORT_OBJ, payTradeModel);
			
			//2����������Ļ�����Ϣ
			AppModel appModel =param.getAppModel();
			appModel.setAppUser(requestModel.getStringValue("userId"));
			appModel.setPlanId(requestModel.getStringValue("planId"));
			appModel.setAudRole(requestModel.getStringValue("defaultRoleId"));
			appModel.setPlanId(requestModel.getStringValue("planId"));
			appModel.setAppType(AppType.PAY_RESULT_REGISTE_APP.toString());
			
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
