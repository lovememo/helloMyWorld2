package com.opm.common.business.channel;

import com.opm.common.business.task.ITask;
import com.opm.common.business.param.TaskParam;

/**
 * Created by kfzx-liuyz1 on 2016/12/9.
 */
public interface IChannel {
    public Class<? extends ITask> doSwitch(TaskParam taskParam);
}
