package com.opm.core.agency.trade;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.core.agency.service.IAgencyService;
import com.opm.core.app.model.AppTrdRelModel;
import com.opm.core.app.service.IAppService;
import com.opm.core.app.service.ITrdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kfzx-chencan on 2016/12/29.
 */
public class AgencyAddAgreeTask  implements ITask {

    @Autowired
    private IAgencyService agencyService;


    @Autowired
    private ITrdService trdService;

    @Autowired
    private IAppService appService;

    private static final Logger LOGGER =  LoggerFactory.getLogger(AgencyTrdPreTask.class);
    private static final String STATE = "PREPARED";
    private static final String TYPE = "AGENCY";

    @Override
    public boolean compensable() {
        return false;
    }

    @Override
    public RetResult check(TaskParam checkParam) {
        RetResult rr = new RetResult();
        rr.setResult(true);
        return rr;
    }

    @Override
    @Transactional
    public RetResult doTask(TaskParam taskParam) {
        Map<String, Object> paramMap = (Map<String, Object>) taskParam.getSelfParam();
        String appNo = (String) taskParam.getContextParam().get("appNo");

        //根据申请号获取trdNo
        AppTrdRelModel appTrdRelModel = new AppTrdRelModel();
        appTrdRelModel.setAppNo(appNo);
        appTrdRelModel.setTrdType(TYPE);;
        String trdNo=this.appService.qryTrdAppRef(appTrdRelModel).getTrdNo().toString();

        agencyService.agencyAddAgree(trdNo);

        RetResult rr = new RetResult();
        Map<String, Object> contextObj = new HashMap<>();
        contextObj.put("appNo", appNo);

        rr.setContextObj(contextObj);
        rr.setReturnObj(contextObj);

        rr.setResult(true);
        return rr;
    }

    @Override
    public RetResult doCompensate(TaskParam compensateParam) {
        RetResult rr = new RetResult();
        rr.setResult(false);
        return rr;
    }

}

