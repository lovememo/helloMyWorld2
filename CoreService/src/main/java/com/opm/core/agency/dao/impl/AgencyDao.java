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
     * ����
     * �������������ϸ��Ϣ
     */
    public Integer insertAgencyTrd(AgencyModel model){
        return this.agencyMapper.insertAgencyTrd(model);
    }
    /**
     * ����
     * ��ѯ����������ϸ
     */
    public AgencyModel selectAgencyTrd(String trdNo){return this.agencyMapper.selectAgencyTrd(trdNo);}

    /**
     * ��Ч
     * ���������Ϣ��
     */
    public Integer insertAgencyInf(String trdNo){return this.agencyMapper.insertAgencyInf(trdNo);}

    /**
     * ��ѯ
     * ��ѯ�����б�Count��
     */
    public String qryAgencyCount(String id,
                                 String instuType,
                                 String agencyName,
                                 String begNum,
                                 String fetchNum){
        return this.agencyMapper.qryAgencyCount(id,instuType,agencyName,begNum,fetchNum);

    }
    /**
     * ��ѯ
     * ��ѯ�����б�
     */
    public List<AgencyModel> qryAgencyList(String id,
                                           String instuType,
                                           String agencyName,
                                           String begNum,
                                           String fetchNum){
        return this.agencyMapper.qryAgencyList(id,instuType,agencyName,begNum,fetchNum);
    }
}
