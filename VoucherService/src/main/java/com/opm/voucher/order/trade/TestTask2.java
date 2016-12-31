package com.opm.voucher.order.trade;

import ch.qos.logback.classic.Logger;

import com.opm.common.business.task.ITask;
import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by kfzx-liuyz1 on 2016/11/30.
 */

@Service("TestTask2")
public class TestTask2 implements ITask {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestTask2.class);

    @Override
    public RetResult check(TaskParam checkParam) {
        LOGGER.info("TestTask2 check");
        RetResult retResult = new RetResult(true,"check ok");
        retResult.setReturnObj(new StringBuffer("aaa"));
        return retResult;
    }

    @Override
    public boolean compensable() {
        return true;
    }

    @Override
    public RetResult doTask(TaskParam taskParam) {
        LOGGER.info("TestTask2 doTask");
        RetResult retResult =  new RetResult(true,"doTask ok");
        retResult.setReturnObj(new StringBuffer("aaa"));
        return retResult;
    }

    @Override
    public RetResult doCompensate(TaskParam compensateParam) {
        LOGGER.info("TestTask2 doCompensate");
        RetResult retResult =  new RetResult(true,"doCompensate ok");
        retResult.setReturnObj(new StringBuffer("aaa"));
        return retResult;
    }
}
