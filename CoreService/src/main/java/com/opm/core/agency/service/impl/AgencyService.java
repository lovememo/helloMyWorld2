package com.opm.core.agency.service.impl;
import com.opm.core.agency.dao.impl.AgencyDao;
import com.opm.core.agency.model.AgencyModel;
import com.opm.core.agency.service.IAgencyService;

import com.opm.core.app.model.AppModel;
import com.opm.core.plan.service.impl.PlanService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *外部机构
 * @author kfzx-jinjf
 */
@Service("AgencyService")
public class AgencyService implements IAgencyService {




    //private static  final  Logger LoGGER =(Logger) LoggerFactory.getLogger(AgencyService.class);
    @Autowired
    private AgencyDao dao;

    /**
     * 获取外部机构列表信息
     *
     * @param id         机构编号
     * @param instuType  机构类型
     * @param agencyName 机构名称
     * @return 机构列表
     */
    public List<AgencyModel> qryAgencyList(String id,
                                       String instuType,
                                       String agencyName,
                                       String begNum,
                                       String fetchNum) {


        return this.dao.qryAgencyList(id,instuType,agencyName,begNum,fetchNum);

    }

    /**
     * 获取外部机构列表信息 的count
     *
     * @param id         机构编号
     * @param instuType  机构类型
     * @param agencyName 机构名称
     * @return 机构列表
     */
    public String  qryAgencyCount(String id,
                                                     String instuType,
                                                     String agencyName,
                                                     String begNum,
                                                     String fetchNum){
        return this.dao.qryAgencyCount(id,instuType,agencyName,begNum,fetchNum);
    }

    /**
     * app 机构新增申请提交
     */
    public Integer agencyAddTrdSubmit(AgencyModel model){
        return this.dao.insertAgencyTrd(model);
    }
    /**
     * 交易 查询机构交易信息
     */
    public AgencyModel qryAgencyTrd(String trdNo){
        return this.dao.selectAgencyTrd(trdNo);

    }
    /**
     * 生效
     * 保存机构信息
     */
    public Integer agencyAddAgree(String trdNo){
        return this.dao.insertAgencyInf(trdNo);
    }
}