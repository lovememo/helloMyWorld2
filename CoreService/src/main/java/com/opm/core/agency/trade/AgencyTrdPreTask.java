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

        // 保存交易主表
        TrdModel trdModel = new TrdModel();
        trdModel.setTrdType(TYPE);
        trdModel.setTrdState("PREPARED");
        this.trdService.saveTrd(trdModel);
        String trdNo = trdModel.getTrdNo();

        //保存交易申请关系表
        AppTrdRelModel appTrdRelModel = new AppTrdRelModel();
        appTrdRelModel.setAppNo(appNo);
        appTrdRelModel.setTrdNo(trdNo);
        appTrdRelModel.setTrdType(TYPE);
        this.appService.relTrdApp(appTrdRelModel);

        //保存交易明细
        AgencyModel model = new AgencyModel();
        model.setAppNo(appNo);
        model.setTrdNo(trdNo);
        // model.setPlanId((String)paramMap.get("planId"));
        //机构类型

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
         * 账户名
         */
        model.setAcctName((String) paramMap.get("acctName"));

        /**
         * 账号
         */
        model.setAcctNo((String) paramMap.get("acctNo"));

        /**
         * 开户行
         */
        model.setAcctBranch((String) paramMap.get("acctBranch"));

        /**
         * 注册资本
         */
        model.setRegPrcpl((String) paramMap.get("regPrcpl"));

        /**
         * 资格期限
         */
        model.setExpiration((String) paramMap.get("expiration"));

        /**
         * 员工人数
         */
        model.setEmployerNum((String) paramMap.get("employerNum"));

        /**
         * 管理人介绍
         */
        model.setIntro((String) paramMap.get("intro"));

        /**
         * 联系电话
         */
        model.setTel((String) paramMap.get("tel"));

        /**
         * 联系人
         */
        model.setBusiMgr((String) paramMap.get("busiMgr"));

        /**
         * 联系人职务
         */
        model.setPosition((String) paramMap.get("position"));

        /**
         * 注册地址
         */
        model.setAddr((String) paramMap.get("addr"));
        LOGGER.info("addr:"+model.getAddr());

        /**
         * 通信地址
         */
        model.setPostAddress((String) paramMap.get("postAddress"));

        /**
         * 网络地址
         */
        model.setNetworkAddress((String) paramMap.get("networkAddress"));

        /**
         * 备注
         */
        model.setMemo((String) paramMap.get("memo"));

        /**
         * 邮编
         */
        model.setZipcode((String) paramMap.get("zipcode"));

        /**
         * 传真
         */
        model.setFax((String) paramMap.get("fax"));

        /**
         * 邮箱
         */
        model.setEmail((String) paramMap.get("email"));

        /**
         * 机构代码
         */
        model.setAgencyCode((String) paramMap.get("agencyCode"));

        /**
         * 机构号
         */
        model.setAgencyNo((String) paramMap.get("agencyNo"));

        /**
         * 是否接口用户
         */
        model.setInterfaceUser((String) paramMap.get("interfaceUser"));

        /**
         * 资格标志仅受托人用1、理事会2、法人
         */
        model.setStatusFlag((String) paramMap.get("statusFlag"));

        /**
         * 合作方接收服务器IP地址
         */

       // model.setSrvip((String) paramMap.get("srvip"));

        /**
         * 合作方接收服务器端口号
         */
       // model.setSrvpOrt((String) paramMap.get("srvpOrt"));

        /**
         * 是否工行(Y、是，N、否)
         */
        model.setInnerFlag((String) paramMap.get("innerFlag"));

        /**
         * 法定代表人
         */
        model.setLegRepresentative((String) paramMap.get("legRepresentative"));

        /**
         * 新增用户所属机构
         */
        model.setNewStru((String) paramMap.get("newStru"));

        /**
         * 开户网点
         */
        model.setOpenBranch((String) paramMap.get("openBranch"));

        /**
         *文件发送加密密码
         */
        model.setZipEnPassword((String) paramMap.get("zipEnPassword"));

        /**
         * 文件发送解密密码
         */
        model.setZipDePassword((String) paramMap.get("zipDePassword"));

        /**
         * 接口状态（0打开；1关闭）
         */
        //model.setInterfaceState((int) paramMap.get("interfaceState"));

        /**
         * 年金基金管理机构代码（1.0使用）
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
