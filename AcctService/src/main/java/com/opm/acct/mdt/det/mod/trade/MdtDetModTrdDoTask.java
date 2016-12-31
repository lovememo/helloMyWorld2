package com.opm.acct.mdt.det.mod.trade;

import ch.qos.logback.classic.Logger;

import com.opm.acct.mdt.det.mod.service.IMdtDetModService;
import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kfzx-wanghong01 on 2016/12/8.
 */

@Service("MdtDetModTrdDoTask")
public class MdtDetModTrdDoTask implements ITask {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtDetModTrdDoTask.class);
    @Autowired
	private IMdtDetModService mdtDetModService;

    @Override
    public RetResult check(TaskParam checkParam) {
        LOGGER.info("TestTrade check");
        return new RetResult(true,"check ok");
    }

    @Override
    public boolean compensable() {
        return true;
    }

    @Override
    public RetResult doTask(TaskParam taskParam) {
        LOGGER.info("TestTrade doTrade");
        String trdNo = (String) taskParam.getContextParam().get("trdNo");
        String planId = (String) taskParam.getContextParam().get("planId");
        mdtDetModService.doTrade(trdNo, planId);
        return new RetResult(true,"doTrade ok");
    }

    @Override
    public RetResult doCompensate(TaskParam compensateParam) {
        LOGGER.info("TestTrade doCompensate");
        return new RetResult(true,"doCompensate ok");
    }

}
