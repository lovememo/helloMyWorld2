package com.opm.core.investManager.trade.channel;

import com.opm.common.business.channel.IChannel;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.EndTask;
import com.opm.common.business.task.ITask;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.trade.TrdNoByAppNoQryStep;


public class AppChannel implements IChannel {

	@Override
	public Class<? extends ITask> doSwitch(TaskParam taskParam) {
		AppModel appModel = (AppModel)taskParam.getContextParam();
		if ( appModel.getAppState() == "8") {
			return TrdNoByAppNoQryStep.class; //TODO Q：同一个Trade在Business注册多次
		} else {
			return EndTask.class;
		}
	}
	


}
