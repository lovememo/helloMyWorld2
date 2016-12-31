package com.opm.core.workflow.trade;

import ch.qos.logback.classic.Logger;


import com.opm.common.business.task.ITask;
import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/11/30.
 */

@Service("TestTask")
public class TestTask implements ITask {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestTask.class);


    @Override
    public RetResult check(TaskParam checkParam) {
        LOGGER.info("TestTask check");
        return new RetResult(true, "check ok");
    }

    @Override
    public boolean compensable() {
        return true;
    }

    @Override
    public RetResult doTask(TaskParam taskParam) {
        LOGGER.info("TestTask doTask");
        RetResult retResult = new RetResult(true, "doTask ok");
        retResult.setReturnObj(new StringBuffer("TestTask Return param"));
        Map<String, Object> contextMap = new HashMap<String, Object>();
        contextMap.put("context", new StringBuffer("TestTask context"));
        retResult.setContextObj(contextMap);
        long sleep = 1000;
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return retResult;
    }

    @Override
    public RetResult doCompensate(TaskParam compensateParam) {
        LOGGER.info("TestTask doCompensate");
        return new RetResult(true, "doCompensate ok");
    }
}
