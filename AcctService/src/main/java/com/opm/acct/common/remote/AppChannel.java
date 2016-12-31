package com.opm.acct.common.remote;

import org.springframework.stereotype.Service;

import com.opm.acct.record.trade.MdtAcctRecordTrdDoTask;
import com.opm.common.business.channel.IChannel;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.EndTask;
import com.opm.common.business.task.ITask;

/**
 * Created by kfzx-wanghong01 on 2016/12/12.
 */
@Service("AppChannel")
public class AppChannel implements IChannel {

	@Override
	public Class<? extends ITask> doSwitch(TaskParam taskParam) {
		
		AppModel appModel = (AppModel)taskParam.getContextParam();
		if ( appModel.getAppState() == "8") {
			return MdtAcctRecordTrdDoTask.class; 
		} else {
			return EndTask.class;
		}
	}
	
	


}
