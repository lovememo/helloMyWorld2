package com.opm.common.business.business;

import ch.qos.logback.classic.Logger;
import com.opm.common.business.channel.DefaultChannel;
import com.opm.common.business.exception.ChannelException;
import com.opm.common.business.task.EndTask;
import com.opm.common.business.channel.IChannel;
import com.opm.common.business.task.ITask;
import com.opm.common.business.param.CompensateTask;
import com.opm.common.business.param.RegisiterTask;
import com.opm.common.business.param.RetResult;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by kfzx-liuyz1 on 2016/12/9.
 */
public abstract class AbstractBusiness implements IBusiness {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CommonBusiness.class);

    protected Map<String,RegisiterTask> taskMap = new HashMap<String,RegisiterTask>();
    protected List<RegisiterTask> taskClassList = new ArrayList<RegisiterTask>();
    protected Stack<CompensateTask> taskClassStack = new Stack<CompensateTask>();

    protected abstract void doCompensate();

    protected abstract void setDefaultChannel();

    protected abstract RetResult run();

    @Override
    public RetResult doBusiness() {
        this.setDefaultChannel();
        this.register(EndTask.class,new StringBuffer("end"));
        RetResult retResult = this.run();

        if(retResult.getResult() != true){
            LOGGER.error("error occurs : " + retResult.getReason());
            this.doCompensate();
        }

        return retResult;
    }

    @Override
    public IBusiness register(Class<? extends ITask> tradeClass, Object tradeParam) {
        RegisiterTask regisiterTask = new RegisiterTask(tradeClass,tradeParam,new DefaultChannel());
        this.taskClassList.add(regisiterTask);
        this.taskMap.put(tradeClass.getName(), regisiterTask);
        return this;
    }

    @Override
    public IBusiness register(Class<? extends ITask> tradeClass, Object tradeParam, Class<? extends IChannel> channel) {
        RegisiterTask regisiterTask = new RegisiterTask(tradeClass,tradeParam,channel);
        this.taskClassList.add(regisiterTask);
        this.taskMap.put(tradeClass.getName(), regisiterTask);
        return this;
    }

    @Override
    public IBusiness register(Class<? extends ITask> tradeClass, Object tradeParam, IChannel channel){
        if(channel == null){
            throw new ChannelException("Channel is null");
        }
        else{
            RegisiterTask regisiterTask = new RegisiterTask(tradeClass,tradeParam,channel);
            this.taskClassList.add(regisiterTask);
            this.taskMap.put(tradeClass.getName(), regisiterTask);
            return this;
        }

    }
}
