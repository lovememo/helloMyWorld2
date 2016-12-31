package com.opm.common.business.channel;

import com.opm.common.business.task.ITask;
import com.opm.common.business.param.TaskParam;

/**
 * Created by kfzx-liuyz1 on 2016/12/9.
 */
public class DefaultChannel implements IChannel {


    private Class<? extends ITask> taskClass;

    public DefaultChannel(){}

    public void setTaskClass(Class<? extends ITask> taskClass) {
        this.taskClass = taskClass;
    }

    @Override
    public Class<? extends ITask> doSwitch(TaskParam taskParam) {
        return this.taskClass;
    }
}
