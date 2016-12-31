package com.opm.core.app.template.trade;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;

public class EmptyTradeTask implements ITask{

	@Override
	public RetResult check(TaskParam checkParam) {
		return null;
	}

	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	public RetResult doTask(TaskParam taskParam) {
		return null;
	}

	@Override
	public RetResult doCompensate(TaskParam compensateParam) {
		return null;
	}

}
