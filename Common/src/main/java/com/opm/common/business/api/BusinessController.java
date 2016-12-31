package com.opm.common.business.api;

import ch.qos.logback.classic.Logger;
import com.opm.common.business.task.ITask;
import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.support.SpringContextHolder;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kfzx-liuyz1 on 2016/11/30.
 */
@RestController
public class BusinessController {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(BusinessController.class);

    @RequestMapping(value = "/compensable/{tradeClassName}",method = RequestMethod.POST)
    public boolean compensable(@PathVariable("tradeClassName") String tradeClassName){
        LOGGER.info("BusinessController doTask");
        try{
            ITask trade = SpringContextHolder.getBean(tradeClassName);
            return trade.compensable();

        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/do/{tradeClassName}",method = RequestMethod.POST,consumes="application/json")
    public RetResult doTrade(@PathVariable("tradeClassName") String tradeClassName, @RequestBody TaskParam paramObj){
        LOGGER.info("BusinessController doTask : " + tradeClassName);
        try{
            ITask trade = SpringContextHolder.getBean(tradeClassName);
            RetResult retResult = trade.doTask(paramObj);
            LOGGER.info("BusinessController get trade : " + tradeClassName + " return object...");

            return retResult;
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/check/{tradeClassName}",method = RequestMethod.POST,consumes="application/json")
    public RetResult check(@PathVariable("tradeClassName") String tradeClassName, @RequestBody TaskParam paramObj){
        LOGGER.info("BusinessController check");
        try{
            ITask trade = SpringContextHolder.getBean(tradeClassName);
            return trade.check(paramObj);

        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/compensate/{tradeClassName}",method = RequestMethod.POST,consumes="application/json")
    public RetResult doCompensate(@PathVariable("tradeClassName") String tradeClassName, @RequestBody TaskParam paramObj){
        LOGGER.info("BusinessController compensate");
        try{
            ITask trade = SpringContextHolder.getBean(tradeClassName);
            return trade.doCompensate(paramObj);

        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            return null;
        }
    }

}
