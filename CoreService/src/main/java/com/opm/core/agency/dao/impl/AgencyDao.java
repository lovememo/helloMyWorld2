package com.opm.core.agency.dao.impl;
import com.opm.core.agency.dao.IAgencyDao;
import com.opm.core.agency.mapper.AgencyMapper;
import com.opm.core.agency.model.AgencyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kfzx-chencan on 2016/12/29.
 */
@Repository("AgencyDao")
public class AgencyDao {

    @Autowired
    private AgencyMapper agencyMapper;
    /**
     * 交易
     * 插入机构申请明细信息
     */
    public Integer insertAgencyTrd(AgencyModel model){
        return this.agencyMapper.insertAgencyTrd(model);
    }
    /**
     * 交易
     * 查询机构交易明细
     */
    public AgencyModel selectAgencyTrd(String trdNo){return this.agencyMapper.selectAgencyTrd(trdNo);}

    /**
     * 生效
     * 插入机构信息表
     */
    public Integer insertAgencyInf(String trdNo){return this.agencyMapper.insertAgencyInf(trdNo);}

    /**
     * 查询
     * 查询机构列表Count数
     */
    public String qryAgencyCount(String id,
                                 String instuType,
                                 String agencyName,
                                 String begNum,
                                 String fetchNum){
        return this.agencyMapper.qryAgencyCount(id,instuType,agencyName,begNum,fetchNum);

    }
    /**
     * 查询
     * 查询机构列表
     */
    public List<AgencyModel> qryAgencyList(String id,
                                           String instuType,
                                           String agencyName,
                                           String begNum,
                                           String fetchNum){
        return this.agencyMapper.qryAgencyList(id,instuType,agencyName,begNum,fetchNum);
    }
}
