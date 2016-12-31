package com.opm.core.app.template.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.param.RetResult;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.task.AppDoEndTask;
import com.opm.core.app.template.task.AppSubmitBeginTask;
import com.opm.core.app.template.task.AppSubmitEndTask;
import com.opm.core.app.template.task.AppTradeBeforeDoTask;
import com.opm.core.app.template.task.AppTradeBeforePrepareTask;
import com.opm.core.app.template.task.EmptyAppTradeTask;
import com.opm.core.app.template.trade.ITrade;
import com.opm.core.app.template.trade.TradeInfo;

/**
 * 用于提供APP的一揽子操作，提供了一些用于重写的方法：<br>
 * businessCheck、illegalCheck、beforeSubmit、afterSubmit、beforeEffect、afterEffect
 * 
 * @author kfzx-zhengyy1
 *
 */

public abstract class AppTemplete implements IAppOpAction {

	// private String appType = null;

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AppTemplete.class);

	private List<TradeInfo> tradeInfos = new ArrayList<TradeInfo>();

	public AppTemplete() {
		// appType = getAppTypeNo();
		initTrades();
	}

	/**
	 * 
	 * @param trade
	 * @param tradeParam
	 * @return
	 */
	public AppTemplete register(ITrade trade, Object tradeParam) {
		tradeInfos.add(new TradeInfo(trade, tradeParam, tradeInfos.size() + 1));
		return this;
	}

	public AppTemplete register(ITrade trade) {
		return register(trade, null);
	}

	@Override
	public final ResultMessage submit(SUMBIT_ACTION action, AppParam params) {
		initSubmitParam(params,action);
		// 1、预处理
		ResultMessage checkResult = beforeSubmit(action, params);
		if (checkResult != null && !checkResult.isRight()) {
			LOGGER.info("执行beforeSubmit失败:" + checkResult.getMsg());
			return checkResult;
		}

		checkResult = legalCheck(params);
		if (checkResult != null && !checkResult.isRight()) {
			LOGGER.info("合法性校验失败:" + checkResult.getMsg());
			return checkResult;
		}
		// 2、加入business处理
		CommonBusiness appSubmitBusiness = new CommonBusiness();
		// TradeParam p = new TradeParam(params);
		// 第一个task参数必须为AppParam
		appSubmitBusiness.register(AppSubmitBeginTask.class, params);
		tradeInfos.stream().forEach(tradeInfo -> {
			appSubmitBusiness.register(AppTradeBeforePrepareTask.class, tradeInfo);
			appSubmitBusiness.register(tradeInfo.getTrade().getPrepareTask(), tradeInfo.getParams());
		});
		appSubmitBusiness.register(AppSubmitEndTask.class, null);
		RetResult businessResult = appSubmitBusiness.doBusiness();
		if (businessResult.getResult() == false) {
			return new ResultMessage(businessResult);
		}

		// 3、收尾
		afterSubmit(action, params);
		return ResultMessage.getRight("申请提交成功！");
	}

	@SuppressWarnings("deprecation")
	private void initSubmitParam(AppParam params,SUMBIT_ACTION action) {
		// params.put(AppType.APPTYPE, getAppTypeNo());
		params.setSubmitAction(action);
		AppModel appModel = params.getAppModel();
		if (appModel == null) {
			appModel = new AppModel();
		}
		// appModel.setAppType(getAppTypeNo());
		params.setAppModel(appModel);
		// params.getAppModel().setAppType(getAppTypeNo());
	}

	@Override
	public final ResultMessage aud(AUD_ACTION audAction, AppParam params) {
		ResultMessage effect = null;
		if (audAction == AUD_ACTION.AGREE) {
			effect = effect(params);
		}
		// RetResult brr = netValueBusiness
		// .register(AppTrade.class, appModel, AppChannel.class) ;
		return effect == null ? ResultMessage.getRight("ok") : effect;
	}

	@Override
	public ResultMessage effect(AppParam params) {
		initSubmitParam(params,null);
		ResultMessage checkResult = beforeEffect(params);
		if (checkResult != null && !checkResult.isRight()) {
			LOGGER.info("执行beforeEffect失败:" + checkResult.getMsg() + ",AppParam:" + params);
			return checkResult;
		}
		// 1、校验
		checkResult = legalCheck(params);
		if (checkResult != null && !checkResult.isRight()) {
			LOGGER.info("合法性校验失败:" + checkResult.getMsg() + ",AppParam:" + params);
			return checkResult;
		}

		checkResult = businessCheck(params);
		if (checkResult != null && !checkResult.isRight()) {
			LOGGER.info("业务校验失败" + checkResult.getMsg() + ",AppParam:" + params);
			return checkResult;
		}

		// 2、加入business处理
		CommonBusiness appSubmitBusiness = new CommonBusiness();
		// TradeParam p = new TradeParam(params);
		// 需要加入一个，用来传参数的
		// 第一个task参数必须为AppParam
		appSubmitBusiness.register(EmptyAppTradeTask.class, params);
		tradeInfos.stream().forEach(tradeInfo -> {
			appSubmitBusiness.register(AppTradeBeforeDoTask.class, tradeInfo);
			appSubmitBusiness.register(tradeInfo.getTrade().getDoTask(), tradeInfo.getParams());
		});
		appSubmitBusiness.register(AppDoEndTask.class, null);
		RetResult businessResult = appSubmitBusiness.doBusiness();
		if (businessResult.getResult() == false) {
			return new ResultMessage(businessResult);
		}

		// 3、收尾
		afterEffect(params);
		return ResultMessage.getRight("生效成功！");
	}

	/**
	 * 合法性校验，在申请提交、申请生效时都会调用
	 * 
	 * @param params
	 * @return
	 */
	protected ResultMessage businessCheck(AppParam params) {
		return ResultMessage.getRight("");
	}

	/**
	 * 合法性校验，在申请提交、申请生效时都会调用<br>
	 * 进行一些参数的校验？
	 * 
	 * @param params
	 * @return
	 */
	protected ResultMessage legalCheck(AppParam params) {
		return ResultMessage.getRight("");
	}

	/**
	 * 用于重写，在提交、校验前执行<br>
	 * 用于对前台录入的参数进行处理<br>
	 * 【关键】需要通过params.getAppModel()获取对象，并配置提交申请所必须的数据。主要是：userid、planId、defaultRoleId，申请成功后会变成“待审核”，若失败则置为删除。若需要显式指定提交后的状态，需要设置appState
	 * 
	 * @param action
	 *            提交的类型：保存/提交
	 * @param paramsIn
	 *            传入的参数
	 * @return 返回的参数，会传入第一个交易的prepareTask中，通过TaskParam.prevParam获取
	 */
	protected ResultMessage beforeSubmit(SUMBIT_ACTION action, AppParam params) {
		return ResultMessage.getRight("ok");
	}

	/**
	 * 用于重写，在提交后执行
	 */
	protected ResultMessage afterSubmit(SUMBIT_ACTION action, AppParam params) {
		return ResultMessage.getRight("ok");
	}

	/**
	 * 用于重写，在生效前执行
	 */
	protected ResultMessage beforeEffect(AppParam params) {
		return ResultMessage.getRight("ok");
	}

	/**
	 * 用于重写，在生效后执行
	 */
	protected ResultMessage afterEffect(AppParam params) {
		return ResultMessage.getRight("ok");

	}

}
