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
 * �����ṩAPP��һ���Ӳ������ṩ��һЩ������д�ķ�����<br>
 * businessCheck��illegalCheck��beforeSubmit��afterSubmit��beforeEffect��afterEffect
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
		// 1��Ԥ����
		ResultMessage checkResult = beforeSubmit(action, params);
		if (checkResult != null && !checkResult.isRight()) {
			LOGGER.info("ִ��beforeSubmitʧ��:" + checkResult.getMsg());
			return checkResult;
		}

		checkResult = legalCheck(params);
		if (checkResult != null && !checkResult.isRight()) {
			LOGGER.info("�Ϸ���У��ʧ��:" + checkResult.getMsg());
			return checkResult;
		}
		// 2������business����
		CommonBusiness appSubmitBusiness = new CommonBusiness();
		// TradeParam p = new TradeParam(params);
		// ��һ��task��������ΪAppParam
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

		// 3����β
		afterSubmit(action, params);
		return ResultMessage.getRight("�����ύ�ɹ���");
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
			LOGGER.info("ִ��beforeEffectʧ��:" + checkResult.getMsg() + ",AppParam:" + params);
			return checkResult;
		}
		// 1��У��
		checkResult = legalCheck(params);
		if (checkResult != null && !checkResult.isRight()) {
			LOGGER.info("�Ϸ���У��ʧ��:" + checkResult.getMsg() + ",AppParam:" + params);
			return checkResult;
		}

		checkResult = businessCheck(params);
		if (checkResult != null && !checkResult.isRight()) {
			LOGGER.info("ҵ��У��ʧ��" + checkResult.getMsg() + ",AppParam:" + params);
			return checkResult;
		}

		// 2������business����
		CommonBusiness appSubmitBusiness = new CommonBusiness();
		// TradeParam p = new TradeParam(params);
		// ��Ҫ����һ����������������
		// ��һ��task��������ΪAppParam
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

		// 3����β
		afterEffect(params);
		return ResultMessage.getRight("��Ч�ɹ���");
	}

	/**
	 * �Ϸ���У�飬�������ύ��������Чʱ�������
	 * 
	 * @param params
	 * @return
	 */
	protected ResultMessage businessCheck(AppParam params) {
		return ResultMessage.getRight("");
	}

	/**
	 * �Ϸ���У�飬�������ύ��������Чʱ�������<br>
	 * ����һЩ������У�飿
	 * 
	 * @param params
	 * @return
	 */
	protected ResultMessage legalCheck(AppParam params) {
		return ResultMessage.getRight("");
	}

	/**
	 * ������д�����ύ��У��ǰִ��<br>
	 * ���ڶ�ǰ̨¼��Ĳ������д���<br>
	 * ���ؼ�����Ҫͨ��params.getAppModel()��ȡ���󣬲������ύ��������������ݡ���Ҫ�ǣ�userid��planId��defaultRoleId������ɹ�����ɡ�����ˡ�����ʧ������Ϊɾ��������Ҫ��ʽָ���ύ���״̬����Ҫ����appState
	 * 
	 * @param action
	 *            �ύ�����ͣ�����/�ύ
	 * @param paramsIn
	 *            ����Ĳ���
	 * @return ���صĲ������ᴫ���һ�����׵�prepareTask�У�ͨ��TaskParam.prevParam��ȡ
	 */
	protected ResultMessage beforeSubmit(SUMBIT_ACTION action, AppParam params) {
		return ResultMessage.getRight("ok");
	}

	/**
	 * ������д�����ύ��ִ��
	 */
	protected ResultMessage afterSubmit(SUMBIT_ACTION action, AppParam params) {
		return ResultMessage.getRight("ok");
	}

	/**
	 * ������д������Чǰִ��
	 */
	protected ResultMessage beforeEffect(AppParam params) {
		return ResultMessage.getRight("ok");
	}

	/**
	 * ������д������Ч��ִ��
	 */
	protected ResultMessage afterEffect(AppParam params) {
		return ResultMessage.getRight("ok");

	}

}
