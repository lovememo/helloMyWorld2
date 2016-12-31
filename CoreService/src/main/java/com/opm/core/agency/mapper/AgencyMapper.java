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
     * ��ȡ�ⲿ�����б���Ϣ
     *
     * @param id         �������
     * @param instuType  ��������
     * @param agencyName ��������
     * @return �����б�
     */
    public List<AgencyModel> getAgencyList(@Param("id") String id,
                                           @Param("instuType") String instuType,
                                           @Param("agencyName") String agencyName);



    /**
     * ����insertAgencyTrd
     * �������������Ϣ
     */
    public Integer insertAgencyTrd(AgencyModel model);

    /**
     * ����selectAgencyTrd
     * ��ѯ����������Ϣ
     */
    public AgencyModel selectAgencyTrd( String trdNo);
    /**
     * ��Ч
     * ���������Ϣ��
     */
    public Integer insertAgencyInf(String trdNo);



    /**
     * ��ѯ
     * ��ѯ�����б�Count��
     */
    public String qryAgencyCount(String id,
                                 String instuType,
                                 String agencyName,
                                 String begNum,
                                 String fetchNum);
    /**
     * ��ѯ
     * ��ѯ�����б�Count��
     */
    public List<AgencyModel> qryAgencyList(String id,
                                 String instuType,
                                 String agencyName,
                                 String begNum,
                                 String fetchNum);





}
