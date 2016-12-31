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
 *�ⲿ����
 * @author kfzx-jinjf
 */
@Service("AgencyService")
public class AgencyService implements IAgencyService {




    //private static  final  Logger LoGGER =(Logger) LoggerFactory.getLogger(AgencyService.class);
    @Autowired
    private AgencyDao dao;

    /**
     * ��ȡ�ⲿ�����б���Ϣ
     *
     * @param id         �������
     * @param instuType  ��������
     * @param agencyName ��������
     * @return �����б�
     */
    public List<AgencyModel> qryAgencyList(String id,
                                       String instuType,
                                       String agencyName,
                                       String begNum,
                                       String fetchNum) {


        return this.dao.qryAgencyList(id,instuType,agencyName,begNum,fetchNum);

    }

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
                                                     String fetchNum){
        return this.dao.qryAgencyCount(id,instuType,agencyName,begNum,fetchNum);
    }

    /**
     * app �������������ύ
     */
    public Integer agencyAddTrdSubmit(AgencyModel model){
        return this.dao.insertAgencyTrd(model);
    }
    /**
     * ���� ��ѯ����������Ϣ
     */
    public AgencyModel qryAgencyTrd(String trdNo){
        return this.dao.selectAgencyTrd(trdNo);

    }
    /**
     * ��Ч
     * ���������Ϣ
     */
    public Integer agencyAddAgree(String trdNo){
        return this.dao.insertAgencyInf(trdNo);
    }
}