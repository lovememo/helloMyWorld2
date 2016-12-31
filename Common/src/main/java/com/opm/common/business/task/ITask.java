package com.opm.common.business.task;


import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;

/**
 * Created by kfzx-liuyz1 on 2016/11/30.
 */
public interface ITask {

    /**
     * check the validation of preparation
     * @param checkParam
     * @return
     */
    public RetResult check(TaskParam checkParam);

    /**
     * need to do compansate or not
     * @return
     */
    public boolean compensable();

    /**
     * do task logic
     * @param taskParam
     * @return
     */
    public RetResult doTask(TaskParam taskParam);

    /**
     * do compansate logic
     * @param compensateParam
     * @return
     */
    public RetResult doCompensate(TaskParam compensateParam);
}
