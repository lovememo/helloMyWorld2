package com.opm.voucher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.core.AppBasicInfo;
import com.opm.voucher.query.service.IAppInfoQryService;

/**
 * Created by kfzx-liuyz1 on 2016/11/29.
 */
@RestController
@RequestMapping("/qry")
public class InfoQryController {

    @Autowired
    private IAppInfoQryService appInfoQryService;

    @RequestMapping(value = "/appInfo/{appId}",method = RequestMethod.GET)
    public AppBasicInfo AppInfoQry(@PathVariable  long appId){
        return this.appInfoQryService.getPlanAppInfo(appId);
    }
}
