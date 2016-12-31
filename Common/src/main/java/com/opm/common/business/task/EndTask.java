package com.opm.common.business.task;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-liuyz1 on 2016/12/9.
 */
@Service("EndTask")
public class EndTask implements ITask {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(EndTask.class);

    @Override
    public RetResult check(TaskParam checkParam) {
        LOGGER.info("EndTask check");
        return new RetResult(true,"check ok");
    }

    @Override
    public boolean compensable() {
        return false;
    }

    @Override
    public RetResult doTask(TaskParam taskParam) {
        LOGGER.info("EndTask doTask");
        Object prevParam = taskParam.getPrevParam();
        return new RetResult(true,"End Task ok",prevParam);
    }

    @Override
    public RetResult doCompensate(TaskParam compensateParam) {
        LOGGER.info("EndTask doTask");
        return  new RetResult(true,"doCompensate ok");
    }
}
