package com.opm.core.app.template.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;

/**
 * 交易的抽象task，用于使用者实现
 * 
 * @author kfzx-zhengyy1
 *
 */
public abstract class AbstractTradeTask implements ITask {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AbstractTradeTask.class);

	// private final String APP_PARAM_OBJ="appParam";

	@Override
	public final RetResult doTask(TaskParam taskParam) {
		LOGGER.info("开始调用自定义TradeTask的dotask :【" + this.getClass().getName() + "】");
		AppParam appParamIn = unpackParams(taskParam);
		ResultMessage doTaskResult = doTask(appParamIn);
		RetResult ret = packParams(appParamIn, doTaskResult);
		 packParams(appParamIn,doTaskResult);
		LOGGER.info("结束调用自定义TradeTask的dotask :【" + this.getClass().getName() + "】");
		return ret;
	}

	/**
	 * do compansate logic
	 * 
	 * @param compensateParam
	 * @return
	 */
	public final RetResult doCompensate(TaskParam compensateParam) {
		LOGGER.info("开始调用自定义TradeTask的doCompensate :【" + this.getClass().getName() + "】");
		AppParam appParamIn = unpackParams(compensateParam);
		ResultMessage doTask = doCompensate(appParamIn);
		RetResult ret = packParams(appParamIn, doTask);
		LOGGER.info("结束调用自定义TradeTask的doCompensate :【" + this.getClass().getName() + "】");
		return ret;
	}

	@Override
	public final RetResult check(TaskParam checkParam) {
		LOGGER.info("开始调用自定义TradeTask的check :【" + this.getClass().getName() + "】");
		AppParam appParamIn = unpackParams(checkParam);
		ResultMessage cr = check(appParamIn);
		if (cr == null) {
			return new RetResult(true, "");
		}
		RetResult ret = packParams(appParamIn, cr);
		LOGGER.info("结束调用自定义TradeTask的check :【" + this.getClass().getName() + "】");
		return ret;
	}

	/**
	 * 获取参数并解析
	 * 
	 * @param taskParam
	 */
	private AppParam unpackParams(TaskParam taskParam) {
		// 从pre或者从self中获取一个参数。
		Object prevParam = taskParam.getPrevParam();
		if (prevParam == null) {
			prevParam = taskParam.getSelfParam();
			assert(prevParam!=null);
			if (prevParam == null) {
				prevParam = new TradeParam(null);
				throw new RuntimeException("此处不应为空！");
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

		// 由于liuyz的逻辑，如果说check失败了，就获取不到prevParam，所以说转化出来的appParam就为null了。NULLEXCEPTION
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
	 * 进行一些输入参数的校验，交易号可从AppParam中获取
	 * @param appParam
	 * @return 返回的ResultMessage的right为true或者返回空都认为校验通过，会调用doTask
	 */
	protected abstract ResultMessage check(AppParam appParam);

	/**
	 * 
	 * @param appParam
	 * @return
	 */
	protected abstract ResultMessage doCompensate(AppParam appParam);

	/**
	 * check校验通过后会调用，进行插入交易表等操作。交易号可从AppParam中获取
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
