package com.opm.common.business.task;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import com.opm.common.business.api.IBusinessApi;
import com.opm.common.business.api.IBusinessApiFactory;
import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-liuyz1 on 2016/11/30.
 */
public abstract class TaskAdapter implements ITask {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TaskAdapter.class);

    private IBusinessApi businessApi = null;
    private String remoteTradeClassName = null;
    public TaskAdapter() {

    }

    private boolean init(){
        try{
            this.businessApi = this.getBusinessApiFactory().createBusinessApi();
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            return false;
        }
        this.remoteTradeClassName = this.getRemoteTradeClassName();
        if(StringUtils.isEmpty(this.getRemoteTradeClassName())){
            return false;
        }

        return true;
    }

    public abstract IBusinessApiFactory getBusinessApiFactory();

    public abstract String getRemoteTradeClassName();

    @Override
    public boolean compensable(){

        if(!this.init()){
            return false;
        }
        try{
            return this.businessApi.compensable(remoteTradeClassName);
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public RetResult check(TaskParam checkParam) {

        if(!this.init()){
            return new RetResult(false,"TaskAdapter serviceId or remoteTradeClassName is null");
        }
        try{
            return this.businessApi.check(remoteTradeClassName,checkParam);
        }
        catch (Exception e){
            return new RetResult(false,e.getMessage());
        }
    }

    @Override
    public RetResult doTask(TaskParam taskParam) {

        if(!this.init()){
            return new RetResult(false,"TaskAdapter serviceId or remoteTradeClassName is null");
        }
        try{
            return this.businessApi.doTrade(remoteTradeClassName, taskParam);
        }
        catch (Exception e){
            return new RetResult(false,e.getMessage());
        }

    }

    @Override
    public RetResult doCompensate(TaskParam compensateParam) {

        if(!this.init()){
            return new RetResult(false,"TaskAdapter serviceId or remoteTradeClassName is null");
        }
        try{
            return this.businessApi.doCompensate(remoteTradeClassName,compensateParam);
        }
        catch (Exception e){
            return new RetResult(false,e.getMessage());
        }
    }
}
