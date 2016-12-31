package com.opm.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.enumdict.ApplyStatus;
import com.opm.core.workflow.model.ApplicationModle;
import com.opm.core.workflow.service.IApplicationService;

/**
 * Created by kfzx-liuyz1 on 2016/10/24.
 */
@RestController
@RequestMapping("/workflow")
public class WorkFlowController {

    @Autowired
    private IApplicationService applicationService;

    @RequestMapping(value = "/getPlanApplicationList/{planId}/{page}/{rownum}",method = RequestMethod.GET)
    public ApplicationModle getPlanApplicationList(@PathVariable String planId, @PathVariable int page, @PathVariable int rownum){
        return this.applicationService.getPlanApplicationList(planId,page,rownum,ApplyStatus.ToReview,ApplyStatus.Reviewing);
    }

    @RequestMapping(value = "/applyReview/{applyId}/{result}",method = RequestMethod.GET)
    public boolean applyReview(@PathVariable String applyId,@PathVariable String result){
        try {
            this.applicationService.applyReview(applyId,result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

}
