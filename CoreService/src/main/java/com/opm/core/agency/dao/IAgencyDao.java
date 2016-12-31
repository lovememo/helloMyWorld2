package com.opm.core.agency.dao;

import com.opm.core.agency.model.AgencyModel;

import java.util.List;

/**
 * Created by kfzx-chencan on 2016/12/26.
 */
public interface IAgencyDao {


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
     * ��ѯ�����б�
     */
    public List<AgencyModel> qryAgencyList(String id,
                                String instuType,
                                String agencyName,
                                String begNum,
                                String fetchNum);



    /**
     *
     * �������������ϸ��Ϣ
     */
    public Integer insertAgencyTrd(AgencyModel model);

    /**
     * ����
     * ��ѯ����������ϸ
     */
    public AgencyModel selectAgencyTrd(String trdNo);

    /**
     * ��Ч
     * ���������Ϣ��
     */
    public Integer insertAgencyInf(String trdNo);





}
