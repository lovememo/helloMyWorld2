package com.opm.core.paymanager.app.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.core.IAppOpAction.SUMBIT_ACTION;
import com.opm.core.app.template.trade.AbstractTradeTask;
import com.opm.core.common.service.FileService;
import com.opm.core.common.tools.CompareTool;
import com.opm.core.paymanager.model.EmpPayNoteStatisticData;
import com.opm.core.paymanager.model.PayTradeConstance;
import com.opm.core.paymanager.model.PayTradeConstance.PAY_REPORT_STATE;
import com.opm.core.paymanager.model.PayTradeModel;
import com.opm.core.paymanager.service.PayService;

@Component
public class PayPrepareTask extends AbstractTradeTask {

	@Autowired
	PayService payService;

	@Autowired
	FileService fileService;

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PayPrepareTask.class);

	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	protected ResultMessage check(AppParam params) {
		// ������Ϊ���ݻ�û�в��������ֻ��У��һЩǰ̨¼�������
		// String tradeNo = params.getTradeNo();

		return ResultMessage.getRight("֧��������ϢУ��ɹ���");
	}

	@Override
	protected ResultMessage doCompensate(AppParam params) {
		return null;
	}

	@Override
	protected ResultMessage doTask(AppParam params) {

		String tradeNo = params.getTradeNo();
		SUMBIT_ACTION submitAction = params.getSubmitAction();
		if (submitAction == SUMBIT_ACTION.ASYNC_SUBMIT) {

			// 1����������
			PayTradeModel payTradeModel = (PayTradeModel) params.get(PayTradeConstance.PAY_REPORT_OBJ);
			payTradeModel.setTrd_no(tradeNo);
			payTradeModel.setState(PAY_REPORT_STATE.NONE.toString());
			payService.insertPayTradeInfo(payTradeModel);
			System.out.println("payTradeModelpayTradeModel��" + payTradeModel);

			// 2�������ļ���app�Ĺ�����
			String fileNo = (String) params.getFileNo();
			assert (fileNo != null);
			assert (params.getAppModel().getAppNo() != null);
			fileService.insertFileRelApp(fileNo, params.getAppModel().getAppNo());

		} else if (submitAction == SUMBIT_ACTION.ASYNC_CALLBACK) {
			String fileNo = (String) params.getFileNo();
			// 1��������ϸ
			// String tradeNo = payService.qryTradeNoByFileNo(fileNo);
			payService.insertPayTradeDet(fileNo, tradeNo);

			// 2��У��
			PayTradeModel payTradeModel = payService.queryPayReportByTradeNo(tradeNo);
			// ��Щ������Ϣ��֪��Ҫ��ô������
			ResultMessage resultMessage = checkImportData(tradeNo, payTradeModel);
			if (!resultMessage.isRight()) {
				return resultMessage;
			}
		}
		LOGGER.info("ok");
		// 3�����ļ������ȡ����,������ϸ��У�����ڻص���������
		return null;
	}

	public ResultMessage checkImportData(String tradeNo, PayTradeModel payTradeModel) {

		EmpPayNoteStatisticData staticPayNoteData = payService.getStaticPayNoteData(tradeNo);
		System.out.println("staticPayNoteData:"+staticPayNoteData);

		// PayTradeModel payTradeModel = (PayTradeModel)
		// params.get(PayTradeConstance.PAY_REPORT_OBJ);
		if (CompareTool.compareNumber(payTradeModel.getAmt(), staticPayNoteData.getPay_bf_tax()) != 0) {
			return ResultMessage.getWrong("У��ʧ�ܣ�¼���˰ǰ�������ϸ��һ�£�");
		}
		if (CompareTool.compareNumber(payTradeModel.getTax_amt(), staticPayNoteData.getTax_amt()) != 0) {
			return ResultMessage.getWrong("У��ʧ�ܣ�¼���Ӧ��˰������ϸ��һ�£�");
		}
		if (CompareTool.compareNumber(payTradeModel.getAmt_af_tax(), staticPayNoteData.getPay_af_tax()) != 0) {
			return ResultMessage.getWrong("У��ʧ�ܣ�¼���˰��Ӧ���������ϸ��һ�£�");
		}
		if(CompareTool.compareNumber(payTradeModel.getPay_count(), staticPayNoteData.getPayCount()) != 0){
			return ResultMessage.getWrong("У��ʧ�ܣ�¼���֧����������ϸ��һ�£�");
		}
		return ResultMessage.getRight("֧��������ϢУ��ɹ���");
	}

}
