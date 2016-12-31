package com.opm.acct.record.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.acct.record.service.IMdtAcctRecordService;
import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;

/**
 * Created by kfzx-wanghong01 on 2016/12/12.
 */
@Service("MdtAcctRecordTrdDoTask")
public class MdtAcctRecordTrdDoTask implements ITask {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtAcctRecordTrdDoTask.class);
	
	@Autowired
	private IMdtAcctRecordService mdtAcctRecordService;

	@Override
	public RetResult check(TaskParam checkParam) {
		RetResult rr = new RetResult();
		rr.setResult(true);
		return rr;
	}

	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	public RetResult doTask(TaskParam taskParam) {
		 String trdNo = (String) taskParam.getContextParam().get("trdNo");
	        String planId = (String) taskParam.getContextParam().get("planId");
		mdtAcctRecordService.doTrade(trdNo, planId);
		RetResult rr = new RetResult();
		rr.setResult(true);
		return rr;
	}

	@Override
	public RetResult doCompensate(TaskParam compensateParam) {
		RetResult rr = new RetResult();
		rr.setResult(false);
		return rr;
	}


}
