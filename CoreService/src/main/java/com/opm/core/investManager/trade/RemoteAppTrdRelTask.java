package com.opm.core.investManager.trade;

import org.springframework.stereotype.Service;

import com.opm.common.business.api.CoreBusinessApiFactory;
import com.opm.common.business.api.IBusinessApiFactory;
import com.opm.common.business.task.TaskAdapter;

@Service("RemoteAppTrdRelTask")
public class RemoteAppTrdRelTask extends TaskAdapter {

	@Override
	public IBusinessApiFactory getBusinessApiFactory() {
		return new CoreBusinessApiFactory();
	}

	@Override
	public String getRemoteTradeClassName() {
		return "AppTrdRelTask";
	}

}
