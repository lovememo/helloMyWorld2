package com.opm.acct.common.remote;

import com.opm.common.business.api.CoreBusinessApiFactory;
import com.opm.common.business.api.IBusinessApiFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opm.common.business.task.TaskAdapter;

/**
 * Created by kfzx-wanghong01 on 2016/12/12.
 */
@Service("TrdTask")
public class TrdTask extends TaskAdapter {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TrdTask.class);


	@Override
	public IBusinessApiFactory getBusinessApiFactory() {
		return new CoreBusinessApiFactory();
	}

	@Override
	public String getRemoteTradeClassName() {
		return "TrdTask";
	}
	


}
