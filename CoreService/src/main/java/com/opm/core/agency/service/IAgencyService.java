package com.opm.core.agency.service;

import com.opm.core.agency.model.AgencyModel;

import java.util.List;
import java.util.Map;

/**
 * @author kfzx-jinjf
 */
public interface IAgencyService {



    /**
     * ��ȡ�ⲿ�����б���Ϣ ��count
     *
     * @param id         �������
     * @param instuType  ��������
     * @param agencyName ��������
     * @return �����б�
     */
    public String  qryAgencyCount(String id,
                                                    String instuType,
                                                    String agencyName,
                                                    String begNum,
                                                    String fetchNum);

    /**
     * ��ȡ�ⲿ�����б���Ϣ
     *
     * @param id         �������
     * @param instuType  ��������
     * @param agencyName ��������
     * @return �����б�
     */
    public List<AgencyModel>  qryAgencyList(String id,
                                            String instuType,
                                            String agencyName,
                                            String begNum,
                                            String fetchNum);
    /**
     * app �������������ύ
     */
    public Integer agencyAddTrdSubmit(AgencyModel model);

    /**
     * ���� ��ѯ����������Ϣ
     */
    public AgencyModel qryAgencyTrd(String trdNo);

    /**
     * ��Ч
     * ���������Ϣ
     */
    public Integer agencyAddAgree(String trdNo);
}