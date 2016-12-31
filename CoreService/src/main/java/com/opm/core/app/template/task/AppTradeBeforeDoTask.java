package com.opm.core.app.template.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opm.core.app.model.AppModel;
import com.opm.core.app.model.AppTrdRelModel;
import com.opm.core.app.service.IAppService;
import com.opm.core.app.service.ITrdService;
import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.exception.AppExecuteException;
import com.opm.core.app.template.trade.AbstractTradeTask;
import com.opm.core.app.template.trade.TradeInfo;

/**
 * trade��dotask֮ǰ����ȡ�������trade_no
 * @author kfzx-zhengyy1
 *
 */
@Component
public class AppTradeBeforeDoTask extends AbstractTradeTask {
	@Autowired
	IAppService appService;

	@Autowired
	ITrdService tradeService;

	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	protected ResultMessage doTask(AppParam appParam) {
		TradeInfo tradeInfo = (TradeInfo)appParam.getSelfParam();
		String tradeName = tradeInfo.getTrade().getTradeName();
		AppModel appModel = appParam.getAppModel();
//		String tradeNo = appService.qryTrdNo(appModel.getAppNo(), tradeName);
		//��ȡ���׺ŵ���Ϣ
		String appNo = appModel.getAppNo();
		assert(appNo != null);
		AppTrdRelModel appTrdRelModel = new AppTrdRelModel();
		appTrdRelModel.setAppNo(appModel.getAppNo());
		appTrdRelModel.setTrdOrder(tradeInfo.getTradeOrder());
		AppTrdRelModel qryTrdAppRef = appService.qryTrdAppRef(appTrdRelModel);
		if(qryTrdAppRef == null){
			throw new AppExecuteException("ִ��������Чʱ����ȡ���׺�ʧ�ܣ�");
		}
		appParam.setTradeNo(qryTrdAppRef.getTrdNo());
		return null;
	}

	@Override
	protected ResultMessage doCompensate(AppParam appParam) {
		return null;
	}

	@Override
	protected ResultMessage check(AppParam appParam) {
		return null;
	}
}
