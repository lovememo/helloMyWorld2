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
		//判断是否有在途的申请提交了该条明细的修改
		MdtAcctdetTrdModel modModel = mdtAcctdetTrdDao.getMdtAcctdetTrdBySeqId(seqId);
		if (null != modModel) {
			//return "有在途申请提交了该条明细的修改。交易流水号为:"+modModel.getTrdNo();
			return null;
		}
		//查询操作日期  若查询不到 会报空指针???????????
		String opDate = mdtAcctdetInfDao.getMdtAcctdetInfBySeqId(seqId).getOpDate();
		String actId = mdtAcctMdtActRelService.getActRelInf(planId).getActId();
		//产生trdNo??????
		String trdNo = "";
		//保存至受托户明细交易表
		MdtAcctdetTrdModel trdModel = new MdtAcctdetTrdModel(seqId, actId, opDate, dealType, memo,trdNo);
		mdtAcctdetTrdDao.insertMdtAcctdetTrd(trdModel);		
		//该条记录的其他字段更新
		mdtAcctdetTrdDao.updMdtAcctdetTrdForMod(trdNo, seqId);
		
		return null;
	}

	@Override
	public List<Object> doTrade(String trdNo, String planId) {
		//入受托户会计记账交易表
		List<MdtDetTrdModel> list = new ArrayList<MdtDetTrdModel>();
		list.add(new MdtDetTrdModel(trdNo, "1002", "1"));
		list.add(new MdtDetTrdModel(trdNo, "224101", "2"));
		MdtActInfModel mdtActInf = mdtAcctMdtActInfService.getActInf(planId);
		mdtDetTrdDao.insertMdtDetTrdForMod(list, trdNo, mdtActInf.getActDate());
		//记账
		mdtAcctRecordService.doTrade(trdNo,planId);
		//更新受托户资金账务信息
		mdtAcctdetInfDao.updMdtAcctdetInf(trdNo);
		//受托户资金账务信息历史表更新
		mdtAcctdetHisDao.insertMdtAcctDetHis(trdNo);
		return null;
	}
}
