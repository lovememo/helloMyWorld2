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
 * trade的preparetask之前，插入一条trade信息
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
			//提交和異步提交都需要生成新的交易。
			//插入一个trade
			TrdModel trdModel = new TrdModel();
			trdModel.setTrdType(tradeName);
			trdModel.setTrdState(TrdState.PREPARED.toString());
			tradeService.saveTrd(trdModel);
			//插入一个trade和app的关联
			AppTrdRelModel appTrdRelModel = new AppTrdRelModel();
			appTrdRelModel.setAppNo(appParam.getAppModel().getAppNo());
			appTrdRelModel.setTrdNo(trdModel.getTrdNo());
			appTrdRelModel.setTrdType(tradeName);
			appService.relTrdApp(appTrdRelModel);
			tradeNo = trdModel.getTrdNo();

		}else if(submitAction == SUMBIT_ACTION.ASYNC_CALLBACK){
			//2、获取交易号
			AppTrdRelModel appTrdRelModel = new AppTrdRelModel();
			appTrdRelModel.setAppNo(appParam.getAppModel().getAppNo());
			appTrdRelModel.setTrdOrder(tradeInfo.getTradeOrder());
			AppTrdRelModel qryTrdAppRef = appService.qryTrdAppRef(appTrdRelModel);
			if(qryTrdAppRef == null){
				throw new AppExecuteException("执行申请异步提交回调时，获取交易号失败！");
			}
			tradeNo = qryTrdAppRef.getTrdNo();
		}
		appParam.setTradeNo(tradeNo);

		return ResultMessage.getRight("交易数据插入成功！");
	}

	@Override
	protected ResultMessage doCompensate(AppParam appParam) {
		SUMBIT_ACTION submitAction = appParam.getSubmitAction();
		if(submitAction == SUMBIT_ACTION.SUBMIT || submitAction == SUMBIT_ACTION.ASYNC_SUBMIT){
			TrdModel trdModel = new TrdModel();
			trdModel.setTrdState(TrdState.COMPENSATED.toString());
			//取到是空怎么办？
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
