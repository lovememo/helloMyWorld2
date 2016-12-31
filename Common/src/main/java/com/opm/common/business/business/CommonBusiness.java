package com.opm.common.business.business;

import org.slf4j.LoggerFactory;

import com.opm.common.business.channel.DefaultChannel;
import com.opm.common.business.context.BusinessContext;
import com.opm.common.business.param.CompensateTask;
import com.opm.common.business.param.RegisiterTask;
import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.EndTask;
import com.opm.common.business.task.ITask;
import com.opm.common.support.SpringContextHolder;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-liuyz1 on 2016/11/30.
 */
public class CommonBusiness extends AbstractBusiness {


    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CommonBusiness.class);

    @Override
    protected void doCompensate() {
        while(!this.taskClassStack.isEmpty()){
            CompensateTask compensateTask = this.taskClassStack.pop();
            ITask task = SpringContextHolder.getBean(compensateTask.getClazz());
            if(task.compensable() == true){
                task.doCompensate(compensateTask.getTaskParam());
            }
        }
    }

    @Override
    protected void setDefaultChannel(){
        for(int i = 0; i < this.taskClassList.size(); ++i){
            int count = this.taskClassList.size();
            RegisiterTask regisiterTask = this.taskClassList.get(i);
            if(regisiterTask.getChannel() instanceof DefaultChannel){
                if(i < count - 1){
                    ((DefaultChannel) regisiterTask.getChannel()).setTaskClass(this.taskClassList.get(i + 1).getClazz());
                }
                if(i == count - 1){
                    ((DefaultChannel) regisiterTask.getChannel()).setTaskClass(EndTask.class);
                }
            }
        }

    }

    private RetResult runNext(RegisiterTask regisiterTask,Object selfParam,Object prevParam){
        Class<? extends ITask> nextTaskClass = regisiterTask.getChannel().doSwitch(new TaskParam(null,selfParam));
        RegisiterTask nextRegisiterTask = this.taskMap.get(nextTaskClass.getName());
        TaskParam nextTaskParam = new TaskParam(prevParam, nextRegisiterTask.getSelfParam(), BusinessContext.getAll());
        return doRun(nextRegisiterTask, nextTaskParam);
    }

    private RetResult doRun(RegisiterTask regisiterTask, TaskParam taskParam){
        Class<? extends ITask> taskClass = regisiterTask.getClazz();
        LOGGER.info("invoke task : " + taskClass.getName());
        ITask task = SpringContextHolder.getBean(taskClass);
        Object selfParam = regisiterTask.getSelfParam();
        RetResult taskResult = null;
        try{
            //invoke do check
            LOGGER.info("invoke task : " + taskClass.getName() + " doCheck ");

            RetResult checkResult = task.check(taskParam);
            if(checkResult == null){
                return new RetResult(false,"Task " + taskClass.toString() + " check failed");
            }
            else{
                if(checkResult.getResult() == false){
                	return checkResult;
                }
                if(checkResult.getResult() == true){
                    //invoke do task
                    LOGGER.info("invoke task : " + taskClass.getName() + " doTask ");
                    taskResult = task.doTask(taskParam);
                    if(task instanceof EndTask){
                        return taskResult;
                    }
                    if(taskResult == null){
                       return new RetResult(false,"Task " + taskClass.toString() + " doTask return null");
                    }
                    else{
                        BusinessContext.putAll(taskResult.getContextObj());
                        if(taskResult.getResult() == false){
//                        	taskResult = this.runNext(regisiterTask,selfParam,taskResult.getReturnObj());
                        	return taskResult;
                        }
                        else{
                        	this.taskClassStack.push(new CompensateTask(taskClass, taskParam));
                            taskResult = this.runNext(regisiterTask,selfParam,taskResult.getReturnObj());
                        }
                    }
                }
            }

        }
        catch (Exception e){
        	e.printStackTrace();
            LOGGER.error(e.getMessage());
            if(task.compensable() == false){
                taskResult = this.runNext(regisiterTask,selfParam,null);
            }else{
                return new RetResult(false,"Task " + taskClass.toString() + " " + e.getMessage());
            }
        }

        return taskResult;
    }

    @Override
    protected RetResult run() {

        RegisiterTask regisiterTask = null;
//        RetResult retResult = null;
//        TaskParam taskParam = null;
        if(this.taskClassList.size() > 0){
            regisiterTask = this.taskClassList.get(0);
            return this.doRun(regisiterTask,new TaskParam(null, regisiterTask.getSelfParam()));
        }
        else{
            return new RetResult(false,"No Task registered");
        }
    }

}
