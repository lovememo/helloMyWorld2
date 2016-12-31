package com.opm.core.agency.service;

import com.opm.core.agency.model.AgencyModel;

import java.util.List;
import java.util.Map;

/**
 * @author kfzx-jinjf
 */
public interface IAgencyService {



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
                                                    String fetchNum);

    /**
     * 获取外部机构列表信息
     *
     * @param id         机构编号
     * @param instuType  机构类型
     * @param agencyName 机构名称
     * @return 机构列表
     */
    public List<AgencyModel>  qryAgencyList(String id,
                                            String instuType,
                                            String agencyName,
                                            String begNum,
                                            String fetchNum);
    /**
     * app 机构新增申请提交
     */
    public Integer agencyAddTrdSubmit(AgencyModel model);

    /**
     * 交易 查询机构交易信息
     */
    public AgencyModel qryAgencyTrd(String trdNo);

    /**
     * 生效
     * 保存机构信息
     */
    public Integer agencyAddAgree(String trdNo);
}