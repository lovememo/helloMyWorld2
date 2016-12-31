package com.opm.common.business.business;

import com.opm.common.business.channel.IChannel;
import com.opm.common.business.task.ITask;
import com.opm.common.business.param.RetResult;

/**
 * Created by kfzx-liuyz1 on 2016/12/9.
 */
public interface IBusiness {
    public RetResult doBusiness();
    public IBusiness register(Class<? extends ITask> tradeClass, Object tradeParam);
    public IBusiness register(Class<? extends ITask> tradeClass, Object tradeParam, Class<? extends IChannel> channel);
    public IBusiness register(Class<? extends ITask> tradeClass, Object tradeParam, IChannel channel);

}
