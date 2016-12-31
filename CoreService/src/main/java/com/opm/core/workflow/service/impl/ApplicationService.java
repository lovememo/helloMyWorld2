package com.opm.core.workflow.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.opm.common.core.AppBasicInfo;
import com.opm.common.enumdict.ApplyStatus;
import com.opm.common.enumdict.OrderType;
import com.opm.core.common.enumdict.ReviewResultType;
import com.opm.core.workflow.dao.IPlanApplicationDao;
import com.opm.core.workflow.model.ApplicationModle;
import com.opm.core.workflow.service.IApplicationService;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */

@Service("ApplicationService")
public class ApplicationService implements IApplicationService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ApplicationService.class);


    @Autowired
    private IPlanApplicationDao planApplicationDao;

//    @Autowired
//    private IOrder orderService;

    @Override
    public ApplicationModle getPlanApplicationList(String planId, int page, int rownum, ApplyStatus... applyStatus) {
        return new ApplicationModle();
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public String applyReview(String applyId, String result){
        LOGGER.info("applyReview");
        if(ReviewResultType.valueOf(result).equals(ReviewResultType.Reject)){
            this.planApplicationDao.updateAppState(Long.parseLong(applyId),ApplyStatus.Reject);
            return null;
        }
        this.planApplicationDao.updateAppState(Long.parseLong(applyId),ApplyStatus.Approve);
        AppBasicInfo appBasicInfo = this.planApplicationDao.getPlanAppInfo(Long.parseLong(applyId));
        appBasicInfo.setOrderType(OrderType.PAY);
        return new Gson().toJson(appBasicInfo);

    }

}
