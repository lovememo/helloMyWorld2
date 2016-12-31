package com.opm.core.workflow.trade;

import ch.qos.logback.classic.Logger;
import com.opm.common.business.task.ITask;
import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by kfzx-liuyz1 on 2016/11/30.
 */

@Service("TestTask3")
public class TestTask3 implements ITask {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestTask3.class);


    @Override
    public RetResult check(TaskParam checkParam) {
        LOGGER.info("TestTask3 check");
        return new RetResult(true,"check ok");
    }

    @Override
    public boolean compensable() {
        return true;
    }

    @Override
    public RetResult doTask(TaskParam taskParam) {
        LOGGER.info("TestTask3 doTask");
        LOGGER.info(taskParam.getPrevParam().toString());
        String context = taskParam.getContextParam().get("context").toString();
        LOGGER.info(context);

        return new RetResult(true,"doTask ok");
    }

    @Override
    public RetResult doCompensate(TaskParam compensateParam) {
        LOGGER.info("TestTask3 doCompensate");
        return new RetResult(true,"doCompensate ok");
    }
}
