package com.opm.core.agency.dao;

import com.opm.core.agency.model.AgencyModel;

import java.util.List;

/**
 * Created by kfzx-chencan on 2016/12/26.
 */
public interface IAgencyDao {


    /**
     * 查询
     * 查询机构列表Count数
     */
    public String qryAgencyCount(String id,
                                 String instuType,
                                 String agencyName,
                                 String begNum,
                                 String fetchNum);

    /**
     * 查询
     * 查询机构列表
     */
    public List<AgencyModel> qryAgencyList(String id,
                                String instuType,
                                String agencyName,
                                String begNum,
                                String fetchNum);



    /**
     *
     * 插入机构申请明细信息
     */
    public Integer insertAgencyTrd(AgencyModel model);

    /**
     * 交易
     * 查询机构交易明细
     */
    public AgencyModel selectAgencyTrd(String trdNo);

    /**
     * 生效
     * 插入机构信息表
     */
    public Integer insertAgencyInf(String trdNo);





}
