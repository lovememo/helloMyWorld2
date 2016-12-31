package com.opm.core.app.template.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opm.core.app.dic.TrdState;
import com.opm.core.app.model.AppTrdRelModel;
import com.opm.core.app.model.TrdModel;
import com.opm.core.app.service.IAppService;
import com.opm.core.app.service.ITrdService;
import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.core.IAppOpAction.SUMBIT_ACTION;
import com.opm.core.app.template.exception.AppExecuteException;
import com.opm.core.app.template.trade.AbstractTradeTask;
import com.opm.core.app.template.trade.TradeInfo;

/**
 * trade��preparetask֮ǰ������һ��trade��Ϣ
 * @author kfzx-zhengyy1
 *
 */
@Component
public class AppTradeBeforePrepareTask extends AbstractTradeTask {
	@Autowired
	IAppService appService;

	@Autowired
	ITrdService tradeService;

	@Override
	public boolean compensable() {
		return true;
	}

	@Override
	protected ResultMessage doTask(AppParam appParam) {
		TradeInfo tradeInfo = (TradeInfo)appParam.getSelfParam();
		String tradeName = tradeInfo.getTrade().getTradeName();
		SUMBIT_ACTION submitAction = appParam.getSubmitAction();
		String tradeNo=null;
		if(submitAction == SUMBIT_ACTION.SUBMIT || submitAction == SUMBIT_ACTION.ASYNC_SUBMIT){
			//�ύ�ͮ����ύ����Ҫ�����µĽ��ס�
			//����һ��trade
			TrdModel trdModel = new TrdModel();
			trdModel.setTrdType(tradeName);
			trdModel.setTrdState(TrdState.PREPARED.toString());
			tradeService.saveTrd(trdModel);
			//����һ��trade��app�Ĺ���
			AppTrdRelModel appTrdRelModel = new AppTrdRelModel();
			appTrdRelModel.setAppNo(appParam.getAppModel().getAppNo());
			appTrdRelModel.setTrdNo(trdModel.getTrdNo());
			appTrdRelModel.setTrdType(tradeName);
			appService.relTrdApp(appTrdRelModel);
			tradeNo = trdModel.getTrdNo();

		}else if(submitAction == SUMBIT_ACTION.ASYNC_CALLBACK){
			//2����ȡ���׺�
			AppTrdRelModel appTrdRelModel = new AppTrdRelModel();
			appTrdRelModel.setAppNo(appParam.getAppModel().getAppNo());
			appTrdRelModel.setTrdOrder(tradeInfo.getTradeOrder());
			AppTrdRelModel qryTrdAppRef = appService.qryTrdAppRef(appTrdRelModel);
			if(qryTrdAppRef == null){
				throw new AppExecuteException("ִ�������첽�ύ�ص�ʱ����ȡ���׺�ʧ�ܣ�");
			}
			tradeNo = qryTrdAppRef.getTrdNo();
		}
		appParam.setTradeNo(tradeNo);

		return ResultMessage.getRight("�������ݲ���ɹ���");
	}

	@Override
	protected ResultMessage doCompensate(AppParam appParam) {
		SUMBIT_ACTION submitAction = appParam.getSubmitAction();
		if(submitAction == SUMBIT_ACTION.SUBMIT || submitAction == SUMBIT_ACTION.ASYNC_SUBMIT){
			TrdModel trdModel = new TrdModel();
			trdModel.setTrdState(TrdState.COMPENSATED.toString());
			//ȡ���ǿ���ô�죿
			trdModel.setTrdNo(appParam.getTradeNo());
			tradeService.modTrd(trdModel);
		}
		
		return ResultMessage.getRight("");
	}

	@Override
	protected ResultMessage check(AppParam appkParam) {
		return ResultMessage.getRight("");
	}
}
