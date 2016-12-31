package com.opm.core.agency.trade;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.core.agency.model.AgencyModel;
import com.opm.core.agency.service.IAgencyService;
import com.opm.core.app.model.AppTrdRelModel;
import com.opm.core.app.model.TrdModel;
import com.opm.core.app.service.IAppService;
import com.opm.core.app.service.ITrdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kfzx-chencan on 2016/12/26.
 */

@Service("AgencyTrdPreTask")
public class AgencyTrdPreTask implements ITask {

    @Autowired
    private IAgencyService agencyService;


    @Autowired
    private ITrdService trdService;

    @Autowired
    private IAppService appService;

    private static final Logger LOGGER =  LoggerFactory.getLogger(AgencyTrdPreTask.class);

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

        // ���潻������
        TrdModel trdModel = new TrdModel();
        trdModel.setTrdType(TYPE);
        trdModel.setTrdState("PREPARED");
        this.trdService.saveTrd(trdModel);
        String trdNo = trdModel.getTrdNo();

        //���潻�������ϵ��
        AppTrdRelModel appTrdRelModel = new AppTrdRelModel();
        appTrdRelModel.setAppNo(appNo);
        appTrdRelModel.setTrdNo(trdNo);
        appTrdRelModel.setTrdType(TYPE);
        this.appService.relTrdApp(appTrdRelModel);

        //���潻����ϸ
        AgencyModel model = new AgencyModel();
        model.setAppNo(appNo);
        model.setTrdNo(trdNo);
        // model.setPlanId((String)paramMap.get("planId"));
        //��������

        model.setId((String) paramMap.get("id"));
        model.setId("1");
        LOGGER.info("appNo:"+appNo);
        LOGGER.info("branchId:"+model.getId());

        model.setInstuType(Integer.parseInt((String)paramMap.get("instuType")) );
        LOGGER.info("instuType:"+model.getInstuType());
        model.setAgencyName((String) paramMap.get("agencyName"));
        model.setAcctBranch((String) paramMap.get("acctBranch"));
        model.setCertId((String) paramMap.get("certId"));

        model.setAgencyName((String) paramMap.get("agencyName"));
        model.setAgencySname((String) paramMap.get("agencySname"));

        /**
         * �˻���
         */
        model.setAcctName((String) paramMap.get("acctName"));

        /**
         * �˺�
         */
        model.setAcctNo((String) paramMap.get("acctNo"));

        /**
         * ������
         */
        model.setAcctBranch((String) paramMap.get("acctBranch"));

        /**
         * ע���ʱ�
         */
        model.setRegPrcpl((String) paramMap.get("regPrcpl"));

        /**
         * �ʸ�����
         */
        model.setExpiration((String) paramMap.get("expiration"));

        /**
         * Ա������
         */
        model.setEmployerNum((String) paramMap.get("employerNum"));

        /**
         * �����˽���
         */
        model.setIntro((String) paramMap.get("intro"));

        /**
         * ��ϵ�绰
         */
        model.setTel((String) paramMap.get("tel"));

        /**
         * ��ϵ��
         */
        model.setBusiMgr((String) paramMap.get("busiMgr"));

        /**
         * ��ϵ��ְ��
         */
        model.setPosition((String) paramMap.get("position"));

        /**
         * ע���ַ
         */
        model.setAddr((String) paramMap.get("addr"));
        LOGGER.info("addr:"+model.getAddr());

        /**
         * ͨ�ŵ�ַ
         */
        model.setPostAddress((String) paramMap.get("postAddress"));

        /**
         * �����ַ
         */
        model.setNetworkAddress((String) paramMap.get("networkAddress"));

        /**
         * ��ע
         */
        model.setMemo((String) paramMap.get("memo"));

        /**
         * �ʱ�
         */
        model.setZipcode((String) paramMap.get("zipcode"));

        /**
         * ����
         */
        model.setFax((String) paramMap.get("fax"));

        /**
         * ����
         */
        model.setEmail((String) paramMap.get("email"));

        /**
         * ��������
         */
        model.setAgencyCode((String) paramMap.get("agencyCode"));

        /**
         * ������
         */
        model.setAgencyNo((String) paramMap.get("agencyNo"));

        /**
         * �Ƿ�ӿ��û�
         */
        model.setInterfaceUser((String) paramMap.get("interfaceUser"));

        /**
         * �ʸ��־����������1�����»�2������
         */
        model.setStatusFlag((String) paramMap.get("statusFlag"));

        /**
         * ���������շ�����IP��ַ
         */

       // model.setSrvip((String) paramMap.get("srvip"));

        /**
         * ���������շ������˿ں�
         */
       // model.setSrvpOrt((String) paramMap.get("srvpOrt"));

        /**
         * �Ƿ���(Y���ǣ�N����)
         */
        model.setInnerFlag((String) paramMap.get("innerFlag"));

        /**
         * ����������
         */
        model.setLegRepresentative((String) paramMap.get("legRepresentative"));

        /**
         * �����û���������
         */
        model.setNewStru((String) paramMap.get("newStru"));

        /**
         * ��������
         */
        model.setOpenBranch((String) paramMap.get("openBranch"));

        /**
         *�ļ����ͼ�������
         */
        model.setZipEnPassword((String) paramMap.get("zipEnPassword"));

        /**
         * �ļ����ͽ�������
         */
        model.setZipDePassword((String) paramMap.get("zipDePassword"));

        /**
         * �ӿ�״̬��0�򿪣�1�رգ�
         */
        //model.setInterfaceState((int) paramMap.get("interfaceState"));

        /**
         * ���������������루1.0ʹ�ã�
         */
        model.setFundCode((String) paramMap.get("fundCode"));




        agencyService.agencyAddTrdSubmit(model);

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
