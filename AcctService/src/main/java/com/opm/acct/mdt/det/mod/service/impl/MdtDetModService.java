package com.opm.acct.mdt.det.mod.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.acct.mdt.act.inf.service.IMdtAcctMdtActInfService;
import com.opm.acct.mdt.act.rel.service.IMdtAcctMdtActRelService;
import com.opm.acct.mdt.dao.IMdtAcctdetHisDao;
import com.opm.acct.mdt.dao.IMdtAcctdetTrdDao;
import com.opm.acct.mdt.dao.impl.MdtAcctdetInfDao;
import com.opm.acct.mdt.dao.impl.MdtDetTrdDao;
import com.opm.acct.mdt.det.mod.service.IMdtDetModService;
import com.opm.acct.mdt.model.MdtAcctdetTrdModel;
import com.opm.acct.mdt.model.MdtActInfModel;
import com.opm.acct.mdt.model.MdtDetTrdModel;
import com.opm.acct.record.service.IMdtAcctRecordService;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-wanghong01 on 2016/12/8.
 */

@Service("MdtDetModService")
public class MdtDetModService implements IMdtDetModService {

    @SuppressWarnings("unused")
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtDetModService.class);

    @Autowired
    private IMdtAcctdetTrdDao mdtAcctdetTrdDao;
    
    @Autowired
    private MdtAcctdetInfDao mdtAcctdetInfDao;
    
    @Autowired
   	private IMdtAcctMdtActInfService mdtAcctMdtActInfService;
      
    @Autowired
   	private IMdtAcctMdtActRelService mdtAcctMdtActRelService;
      
    @Autowired
    private MdtDetTrdDao mdtDetTrdDao;
    
    @Autowired
	private IMdtAcctRecordService mdtAcctRecordService;
    
    @Autowired
    private IMdtAcctdetHisDao mdtAcctdetHisDao;
    
	@Override
	public List<Object> doPrepare(String planId, String seqId, String dealType, String memo) {
		//�ж��Ƿ�����;�������ύ�˸�����ϸ���޸�
		MdtAcctdetTrdModel modModel = mdtAcctdetTrdDao.getMdtAcctdetTrdBySeqId(seqId);
		if (null != modModel) {
			//return "����;�����ύ�˸�����ϸ���޸ġ�������ˮ��Ϊ:"+modModel.getTrdNo();
			return null;
		}
		//��ѯ��������  ����ѯ���� �ᱨ��ָ��???????????
		String opDate = mdtAcctdetInfDao.getMdtAcctdetInfBySeqId(seqId).getOpDate();
		String actId = mdtAcctMdtActRelService.getActRelInf(planId).getActId();
		//����trdNo??????
		String trdNo = "";
		//���������л���ϸ���ױ�
		MdtAcctdetTrdModel trdModel = new MdtAcctdetTrdModel(seqId, actId, opDate, dealType, memo,trdNo);
		mdtAcctdetTrdDao.insertMdtAcctdetTrd(trdModel);		
		//������¼�������ֶθ���
		mdtAcctdetTrdDao.updMdtAcctdetTrdForMod(trdNo, seqId);
		
		return null;
	}

	@Override
	public List<Object> doTrade(String trdNo, String planId) {
		//�����л���Ƽ��˽��ױ�
		List<MdtDetTrdModel> list = new ArrayList<MdtDetTrdModel>();
		list.add(new MdtDetTrdModel(trdNo, "1002", "1"));
		list.add(new MdtDetTrdModel(trdNo, "224101", "2"));
		MdtActInfModel mdtActInf = mdtAcctMdtActInfService.getActInf(planId);
		mdtDetTrdDao.insertMdtDetTrdForMod(list, trdNo, mdtActInf.getActDate());
		//����
		mdtAcctRecordService.doTrade(trdNo,planId);
		//�������л��ʽ�������Ϣ
		mdtAcctdetInfDao.updMdtAcctdetInf(trdNo);
		//���л��ʽ�������Ϣ��ʷ�����
		mdtAcctdetHisDao.insertMdtAcctDetHis(trdNo);
		return null;
	}
}
