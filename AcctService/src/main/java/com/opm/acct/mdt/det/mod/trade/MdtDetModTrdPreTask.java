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

@Service("MdtDetModTrdPreTask")
public class MdtDetModTrdPreTask implements ITask {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtDetModTrdPreTask.class);
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
        String seqId = (String) taskParam.getContextParam().get("seqId");
        String planId = (String) taskParam.getContextParam().get("planId");
        String dealType = (String) taskParam.getContextParam().get("dealType");
        String memo = (String) taskParam.getContextParam().get("memo");
        mdtDetModService.doPrepare(planId, seqId, dealType,memo);
        return new RetResult(true,"doTrade ok");
    }

    @Override
    public RetResult doCompensate(TaskParam compensateParam) {
        LOGGER.info("TestTrade doCompensate");
        return new RetResult(true,"doCompensate ok");
    }
}
