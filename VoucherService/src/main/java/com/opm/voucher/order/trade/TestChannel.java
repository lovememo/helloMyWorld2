package com.opm.voucher.order.trade;

import com.opm.common.business.channel.IChannel;
import com.opm.common.business.task.ITask;
import com.opm.common.business.param.TaskParam;

/**
 * Created by kfzx-liuyz1 on 2016/12/9.
 */
public class TestChannel implements IChannel {
    @Override
    public Class<? extends ITask> doSwitch(TaskParam taskParam) {
        return TestTask2.class;
    }
}
