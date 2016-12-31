package com.opm.core.agency.controller;

import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.param.RetResult;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.common.utils.UtilTools;
import com.opm.core.agency.model.AgencyModel;
import com.opm.core.agency.service.IAgencyService;
import com.opm.core.agency.trade.AgencyTrdPreTask;
import com.opm.core.app.model.AppTrdRelModel;
import com.opm.core.app.service.IAppService;
import com.opm.core.app.service.ITrdService;
import com.opm.core.app.task.AppAudTask;
import com.opm.core.app.template.AppParam;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by kfzx-jinjf on 2016/12/5.
 */
@RestController
@RequestMapping("/agency")
public class AgencyController {
    @Autowired
    private IAgencyService agencyService;

    @Autowired
    private ITrdService trdService;

    @Autowired
    private IAppService appService;

    private static final Logger LOGGER =  LoggerFactory.getLogger(AgencyController.class);
    private static final String TYPE = "AGENCY";

    @RequestMapping(value = "/qry/agencyList/{id}/{instuType}/{agencyName}/{begNum}/{fetchNum}", method = { RequestMethod.POST, RequestMethod.GET })
    public  List<Object>  qryAgencyList(@PathVariable String id, @PathVariable String instuType, @PathVariable String agencyName, @PathVariable String begNum, @PathVariable String fetchNum){
        ResponseModel resModel = null;
        String funcName = "��ѯ�����б�";
        LOGGER.info(funcName + "��ʼ");



        return UtilTools.turnPage(agencyService.qryAgencyList(id,instuType,agencyName,begNum,fetchNum), Integer.parseInt(agencyService.qryAgencyCount(id,instuType,agencyName,begNum,fetchNum)));



    }


   @RequestMapping(value = "/app/submit", method = { RequestMethod.POST, RequestMethod.GET })

    public ResponseModel appSubmit(RequestModel reqModel) {

        CommonBusiness business = new CommonBusiness();
        ResponseModel resModel = null;
        String funcName = "�ύ������Ϣ";
        try{

            RetResult ret = business.register(AppAudTask.class, reqModel.getPrivateCtx()) // ��������
                    .register(AgencyTrdPreTask.class, reqModel.getPrivateCtx()) // ���潻��
                    .doBusiness();

            resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", ret.getContextObj());

            LOGGER.info(funcName + "�ɹ�");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
        }
        return resModel;
    }

    @RequestMapping(value = "/qry/appDet", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseModel qryAppDet(RequestModel reqModel) {

        ResponseModel resModel = null;
        String funcName = "��ȡ����������ϸ��Ϣ";
        try {
            String appNo = reqModel.getStringValue("appNo");
            //��������Ż�ȡtrdNo
            AppTrdRelModel appTrdRelModel = new AppTrdRelModel();
            appTrdRelModel.setAppNo(appNo);
            appTrdRelModel.setTrdType(TYPE);;
            String trdNo=this.appService.qryTrdAppRef(appTrdRelModel).getTrdNo().toString();
            AgencyModel model = agencyService.qryAgencyTrd(trdNo);



            resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", model);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
        }
        return resModel;
    }

    @RequestMapping(value = "/add/agree", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseModel addAgree(RequestModel reqModel) {
        CommonBusiness business = new CommonBusiness();
        ResponseModel resModel = null;
        String funcName = "�����������븴��ͬ����Ч";
        try {
            String appNo = reqModel.getStringValue("appNo");

            AppParam appParam = new AppParam();
            appParam.put("requestModel", reqModel);

            RetResult ret = business.register(AppAudTask.class, reqModel.getPrivateCtx()) // ����������Ч��������
                    .register(AgencyTrdPreTask.class, reqModel.getPrivateCtx()) // ������Ч
                    .doBusiness();

            resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "�ɹ�", ret.getContextObj());

            LOGGER.info(funcName + "�ɹ�");

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "ʧ��", null);
        }
        return resModel;
    }


}
