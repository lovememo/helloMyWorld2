package com.opm.common.business.param;

import com.opm.common.business.task.ITask;

/**
 * Created by kfzx-liuyz1 on 2016/12/2.
 */
public class CompensateTask {

    private Class<? extends ITask> clazz;
    private TaskParam taskParam;

    public CompensateTask(){

    }

    public CompensateTask(Class<? extends ITask> clazz, TaskParam taskParam) {
        this.clazz = clazz;
        this.taskParam = taskParam;
    }


    public TaskParam getTaskParam() {
        return taskParam;
    }

    public void setTaskParam(TaskParam taskParam) {
        this.taskParam = taskParam;
    }

    public Class<? extends ITask> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends ITask> clazz) {
        this.clazz = clazz;
    }



}
