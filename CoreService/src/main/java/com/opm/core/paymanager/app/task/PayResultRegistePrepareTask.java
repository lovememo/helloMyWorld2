package com.opm.core.paymanager.app.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.trade.AbstractTradeTask;
import com.opm.core.common.tools.CheckTool;
import com.opm.core.paymanager.model.PayTradeConstance;
import com.opm.core.paymanager.model.PayTradeModel;
import com.opm.core.paymanager.service.PayService;

@Component
public class PayResultRegistePrepareTask extends AbstractTradeTask {

	@Autowired
	PayService payService;

//	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PayResultRegistePrepareTask.class);

	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	protected ResultMessage check(AppParam params) {
//		������Ϊ���ݻ�û�в��������ֻ��У��һЩǰ̨¼�������
//		String tradeNo = params.getTradeNo();
		PayTradeModel payTradeModel=(PayTradeModel)params.get(PayTradeConstance.PAY_REPORT_OBJ);
		if(!CheckTool.isNotNull(payTradeModel.getPay_no())){
			return ResultMessage.getWrong("payNo����Ϊ�գ�");
		}
		if(!CheckTool.isDate(payTradeModel.getPay_result_fb_date(),false)){
			return ResultMessage.getWrong("֧������������ڸ�ʽ����ȷ��");
		}
		
		return ResultMessage.getRight("֧��������ϢУ��ɹ���");
	}


	@Override
	protected ResultMessage doCompensate(AppParam params) {
		return null;
	}

	@Override
	protected ResultMessage doTask(AppParam params) {

		//1.1����������
		String tradeNo = params.getTradeNo();
		PayTradeModel paramPayTradeModel=(PayTradeModel)params.get(PayTradeConstance.PAY_REPORT_OBJ);
		PayTradeModel queryPayReport = payService.queryPayReportRecByPayNo(paramPayTradeModel.getPay_no());
		queryPayReport.setTrd_no(tradeNo);
		queryPayReport.setPay_result_fb_date(paramPayTradeModel.getPay_result_fb_date());
		
		payService.insertPayTradeInfo(queryPayReport);
		return  ResultMessage.getRight("prepare���");
	}

	
}
