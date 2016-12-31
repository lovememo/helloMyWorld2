package com.opm.common.business.param;

import com.opm.common.business.channel.IChannel;
import com.opm.common.business.task.ITask;

/**
 * Created by kfzx-liuyz1 on 2016/12/2.
 */
public class RegisiterTask {

    private Class<? extends ITask> clazz;
    private IChannel channel;
    private Object selfParam;

    public RegisiterTask(Class<? extends ITask> clazz, Object selfParam) {
        this.clazz = clazz;
        this.selfParam = selfParam;
    }

    public RegisiterTask(Class<? extends ITask> clazz, Object selfParam, Class<? extends IChannel> channelClass) {
        this.clazz = clazz;
        this.selfParam = selfParam;
        try {
            this.channel = channelClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public RegisiterTask(Class<? extends ITask> clazz, Object selfParam, IChannel channel) {
        this.clazz = clazz;
        this.selfParam = selfParam;
        this.channel = channel;
    }


    public Class<? extends ITask> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends ITask> clazz) {
        this.clazz = clazz;
    }

    public Object getSelfParam() {
        return selfParam;
    }

    public void setSelfParam(Object selfParam) {
        this.selfParam = selfParam;
    }

    public IChannel getChannel() {
        return channel;
    }

    public void setChannel(IChannel channel) {
        this.channel = channel;
    }
}
