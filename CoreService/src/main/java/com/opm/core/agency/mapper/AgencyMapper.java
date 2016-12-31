package com.opm.core.agency.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.core.agency.model.AgencyModel;

/**
 * Created by kfzx-jinjf on 2016/12/5.
 */
@Mapper
public interface AgencyMapper {
    /**
     * 获取外部机构列表信息
     *
     * @param id         机构编号
     * @param instuType  机构类型
     * @param agencyName 机构名称
     * @return 机构列表
     */
    public List<AgencyModel> getAgencyList(@Param("id") String id,
                                           @Param("instuType") String instuType,
                                           @Param("agencyName") String agencyName);



    /**
     * 交易insertAgencyTrd
     * 插入机构申请信息
     */
    public Integer insertAgencyTrd(AgencyModel model);

    /**
     * 交易selectAgencyTrd
     * 查询机构申请信息
     */
    public AgencyModel selectAgencyTrd( String trdNo);
    /**
     * 生效
     * 插入机构信息表
     */
    public Integer insertAgencyInf(String trdNo);



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
     * 查询机构列表Count数
     */
    public List<AgencyModel> qryAgencyList(String id,
                                 String instuType,
                                 String agencyName,
                                 String begNum,
                                 String fetchNum);





}
