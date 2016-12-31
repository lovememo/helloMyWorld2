package com.opm.common.business.api;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kfzx-liuyz1 on 2016/12/14.
 */
public interface IBusinessApi {

    @RequestMapping(value = "/compensable/{tradeClassName}",method = RequestMethod.POST)
    public boolean compensable(@PathVariable("tradeClassName") String tradeClassName);

    @RequestMapping(value = "/do/{tradeClassName}",method = RequestMethod.POST,consumes="application/json")
    public RetResult doTrade(@PathVariable("tradeClassName") String tradeClassName, @RequestBody TaskParam paramObj);

    @RequestMapping(value = "/check/{tradeClassName}",method = RequestMethod.POST,consumes="application/json")
    public RetResult check(@PathVariable("tradeClassName") String tradeClassName, @RequestBody TaskParam paramObj);

    @RequestMapping(value = "/compensate/{tradeClassName}",method = RequestMethod.POST,consumes="application/json")
    public RetResult doCompensate(@PathVariable("tradeClassName") String tradeClassName, @RequestBody TaskParam paramObj);

}
