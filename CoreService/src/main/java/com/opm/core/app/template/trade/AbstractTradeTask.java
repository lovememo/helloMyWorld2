package com.opm.core.app.template.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;

/**
 * ���׵ĳ���task������ʹ����ʵ��
 * 
 * @author kfzx-zhengyy1
 *
 */
public abstract class AbstractTradeTask implements ITask {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AbstractTradeTask.class);

	// private final String APP_PARAM_OBJ="appParam";

	@Override
	public final RetResult doTask(TaskParam taskParam) {
		LOGGER.info("��ʼ�����Զ���TradeTask��dotask :��" + this.getClass().getName() + "��");
		AppParam appParamIn = unpackParams(taskParam);
		ResultMessage doTaskResult = doTask(appParamIn);
		RetResult ret = packParams(appParamIn, doTaskResult);
		 packParams(appParamIn,doTaskResult);
		LOGGER.info("���������Զ���TradeTask��dotask :��" + this.getClass().getName() + "��");
		return ret;
	}

	/**
	 * do compansate logic
	 * 
	 * @param compensateParam
	 * @return
	 */
	public final RetResult doCompensate(TaskParam compensateParam) {
		LOGGER.info("��ʼ�����Զ���TradeTask��doCompensate :��" + this.getClass().getName() + "��");
		AppParam appParamIn = unpackParams(compensateParam);
		ResultMessage doTask = doCompensate(appParamIn);
		RetResult ret = packParams(appParamIn, doTask);
		LOGGER.info("���������Զ���TradeTask��doCompensate :��" + this.getClass().getName() + "��");
		return ret;
	}

	@Override
	public final RetResult check(TaskParam checkParam) {
		LOGGER.info("��ʼ�����Զ���TradeTask��check :��" + this.getClass().getName() + "��");
		AppParam appParamIn = unpackParams(checkParam);
		ResultMessage cr = check(appParamIn);
		if (cr == null) {
			return new RetResult(true, "");
		}
		RetResult ret = packParams(appParamIn, cr);
		LOGGER.info("���������Զ���TradeTask��check :��" + this.getClass().getName() + "��");
		return ret;
	}

	/**
	 * ��ȡ����������
	 * 
	 * @param taskParam
	 */
	private AppParam unpackParams(TaskParam taskParam) {
		// ��pre���ߴ�self�л�ȡһ��������
		Object prevParam = taskParam.getPrevParam();
		if (prevParam == null) {
			prevParam = taskParam.getSelfParam();
			assert(prevParam!=null);
			if (prevParam == null) {
				prevParam = new TradeParam(null);
				throw new RuntimeException("�˴���ӦΪ�գ�");
			}
		}
		AppParam appParam;
		if (prevParam instanceof TradeParam) {
			appParam = ((TradeParam) prevParam).getAppParam();
		} else if (prevParam instanceof AppParam) {
			appParam = (AppParam) prevParam;
		} else {
			appParam = new AppParam(prevParam);
		}
		assert (prevParam != null);

		// ����liuyz���߼������˵checkʧ���ˣ��ͻ�ȡ����prevParam������˵ת��������appParam��Ϊnull�ˡ�NULLEXCEPTION
		appParam.setSelfParam(taskParam.getSelfParam());

		return appParam;
	}

	private RetResult packParams(AppParam ret, ResultMessage resultMessage) {
		RetResult result;
		if (resultMessage == null) {
			result = new RetResult(true, null);
		} else {
			result = new RetResult(resultMessage.isRight(), resultMessage.getMsg());
		}
		result.setReturnObj(new TradeParam(ret));
		return result;
	}

	/**
	 * ����һЩ���������У�飬���׺ſɴ�AppParam�л�ȡ
	 * @param appParam
	 * @return ���ص�ResultMessage��rightΪtrue���߷��ؿն���ΪУ��ͨ���������doTask
	 */
	protected abstract ResultMessage check(AppParam appParam);

	/**
	 * 
	 * @param appParam
	 * @return
	 */
	protected abstract ResultMessage doCompensate(AppParam appParam);

	/**
	 * checkУ��ͨ�������ã����в��뽻�ױ�Ȳ��������׺ſɴ�AppParam�л�ȡ
	 * 
	 * @return ResultMessage
	 */
	protected abstract ResultMessage doTask(AppParam appParam);

	class TradeParam {

		private AppParam appParam;

		public TradeParam(AppParam userParam) {
			super();
			this.appParam = userParam;
		}

		public AppParam getAppParam() {
			return appParam;
		}

		public void setAppParam(AppParam appParam) {
			this.appParam = appParam;
		}

	}

}
