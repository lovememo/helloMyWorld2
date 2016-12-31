package com.opm.acct.mdt.reverse.act.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.acct.common.CommonConstant;
import com.opm.acct.common.CommonUtils;
import com.opm.acct.common.CurrencyUtils;
import com.opm.acct.mdt.dao.IMdtActRelDao;
import com.opm.acct.mdt.dao.IMdtAdjActTrdDao;
import com.opm.acct.mdt.dao.IMdtDayendInfDao;
import com.opm.acct.mdt.dao.IMdtDetDao;
import com.opm.acct.mdt.dao.IMdtDetTrdDao;
import com.opm.acct.mdt.model.MdtAdjActTrdModel;
import com.opm.acct.mdt.model.MdtDetModel;
import com.opm.acct.mdt.reverse.act.service.IMdtReverseActService;
import com.opm.acct.record.service.IMdtAcctRecordService;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-wanghong01 on 2016/12/8.
 */

@Service("MdtReverseActService")
public class MdtReverseActService implements IMdtReverseActService {

    @SuppressWarnings("unused")
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtReverseActService.class);

    @Autowired
    private IMdtDetTrdDao mdtDetTrdDao;
    
    @Autowired
	private IMdtAcctRecordService mdtAcctRecordService;
    
    @Autowired
    private IMdtDayendInfDao mdtDayendInfDao;
    
    @Autowired
	private IMdtAdjActTrdDao mdtAdjActTrdDao;
    
    @Autowired
   	private IMdtActRelDao mdtActRelDao;
    
    @Autowired
    private IMdtDetDao mdtDetDao;

	@Override
	public List<Object> doPrepare(String planId, String seqIdStr) {

		List<String> seqIdList = CommonUtils.getArrayFromStr(seqIdStr, CommonConstant.SEPARATOR);
		if (null == seqIdList || 0 == seqIdList.size()) {
			return null;
		}
		List<MdtAdjActTrdModel> list = new ArrayList<MdtAdjActTrdModel>();
		//���뽻�ױ�???????? ����ɹ��򲻳ɹ�
		String trdNo = "";
		StringBuilder seqIdWhere = new StringBuilder();
		for (String seqId : seqIdList) {
			list.add(new MdtAdjActTrdModel (seqId,trdNo));
			seqIdWhere.append("'"+seqId+"',");
		}
		mdtAdjActTrdDao.insertMdtAdjActTrd(list);
		String actId = mdtActRelDao.getMdtActRel(planId).getActId();
		String retMsg = checkMdtReverseAct (actId,trdNo, seqIdWhere.substring(0, seqIdWhere.length()).toString());
		if (!CommonConstant.RETMSG_SUCCESS.equals(retMsg)) {
			return null;//retMsg;
		}
		
		return null;
	}

	@Override
	public List<Object> doTrade(String trdNo,String planId) {
		String actId = mdtActRelDao.getMdtActRel(planId).getActId();
		mdtDetTrdDao.insertMdtDetTrdForReverAct(trdNo, actId);
		mdtAcctRecordService.doTrade(trdNo, planId);
		return null;
	}
	
	/**
	 * ��������У��
	 * @param actInfo
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public String checkMdtReverseAct (String actId,String trdNo, String seqIdStr) {
		// ��ѯ max(trdNoApp) from v_oam_app ����appType="034601"//���л�����, trdNo, app_state IN (2, 4, 14),seqId in seqIdStr
		//����;�����ύ�˸ñʻ����ϸ�ĳ��ˡ������Ϊ��' || trdNoApp
		String trdNoApp = "";
		if (StringUtils.isNotEmpty(trdNoApp)) {
			return "����;�����ύ�˸ñʻ����ϸ�ĳ��ˡ�������ˮ��Ϊ��"+trdNoApp;
		}
		List<MdtAdjActTrdModel> list = mdtAdjActTrdDao.getMdtAdjActTrd(trdNo);
		if (null == list || 0 == list.size()) {
			return null;
		}
		//У�����Ƿ����
		double debitAmt = 0;
		double creditAmt = 0;
		for (MdtAdjActTrdModel tmp : list) {
			if ("1".equals(tmp.getDebitCreditFlag())) {
				debitAmt = CurrencyUtils.sumDouble(debitAmt+"", tmp.getTradeAmt());
			} else if ("2".equals(tmp.getDebitCreditFlag())){
				creditAmt = CurrencyUtils.sumDouble(creditAmt+"", tmp.getTradeAmt());
			}
		}
		double subAmt = CurrencyUtils.subDouble(debitAmt+"", creditAmt+"");
		if (0 != new BigDecimal(0).compareTo(new BigDecimal(subAmt))) {
			return "�������";
		}
		//������˫���Ŀ�Ŀ�����ʲ��͸�ծʱ��������Ȩ���ǲ���ģ�ֻҪ�κ�һ����һ�������ʲ���ծ������Ϊ������Ȩ���Ǳ��˵ģ�ʵ��Ҳ���ܲ��䣩����Ҫȡ���սᡣ
		String opDateMax = mdtDetDao.getMaxOpDateForReverseAct(trdNo);
		int count = mdtDetDao.getCountForReverseAct(trdNo);
		if (0 != count) {
			if (mdtDayendInfDao.isFinishDayend(actId, opDateMax)) {
				return opDateMax+"���սᣬ��ȡ�������ս���ٴ�������";
			}
			
		}
		//�ж��Ƿ����½�
		if (mdtDayendInfDao.isFinishMonend(actId, opDateMax)) {
			return "�������һ�����սᣬ��Ҫ���������һ��ȡ���ս����ύ���롣";
		}
		
		return CommonConstant.RETMSG_SUCCESS;
	}

	@Override
	public List<MdtDetModel> qryMdtDetList(String planId, String date, String amt, String beginNum, String fetchNum) {
		String actId = mdtActRelDao.getMdtActRel(planId).getActId();
		return mdtDetDao.qryMdtDetList(actId, date, amt, beginNum, fetchNum);
	}

	@Override
	public List<MdtDetModel> qryReverseActApp(String trdNo) {
		return mdtDetDao.qryReverseActApp(trdNo);
	} 
}
