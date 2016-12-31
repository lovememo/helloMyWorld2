package com.opm.acct.mdt.dayend.cancel.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.acct.common.CommonConstant;
import com.opm.acct.common.CommonUtils;
import com.opm.acct.mdt.act.inf.service.IMdtAcctMdtActInfService;
import com.opm.acct.mdt.dao.IMdtAcctdetHisDao;
import com.opm.acct.mdt.dao.IMdtActMoRptDao;
import com.opm.acct.mdt.dao.IMdtDayendInfDao;
import com.opm.acct.mdt.dao.IMdtDayendTrdDao;
import com.opm.acct.mdt.dao.impl.MdtAcctdetInfDao;
import com.opm.acct.mdt.dao.impl.MdtActRelDao;
import com.opm.acct.mdt.dao.impl.MdtDetTrdDao;
import com.opm.acct.mdt.dayend.cancel.service.IMdtDayendCancelService;
import com.opm.acct.mdt.model.MdtActInfModel;
import com.opm.acct.mdt.model.MdtDayendTrdModel;
import com.opm.acct.record.service.IMdtAcctRecordService;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-wanghong01 on 2016/12/8.
 */

@Service("MdtDayendCancelService")
public class MdtDayendCancelService implements IMdtDayendCancelService {

    @SuppressWarnings("unused")
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtDayendCancelService.class);

    @Autowired
    private MdtAcctdetInfDao mdtAcctdetInfDao;
    
    @Autowired
	private IMdtAcctMdtActInfService mdtAcctMdtActInfService;
    
    @Autowired
    private MdtDetTrdDao mdtDetTrdDao;
    
    @Autowired
	private IMdtAcctRecordService mdtAcctRecordService;
    
    @Autowired
    private IMdtAcctdetHisDao mdtAcctdetHisDao;
    
    @Autowired
    private IMdtDayendInfDao mdtDayendInfDao;
    
    @Autowired
    private IMdtActMoRptDao mdtActMoRptDao;
    
    @Autowired
    private IMdtDayendTrdDao mdtDayendTrdDao;
    
    @Autowired
	private MdtActRelDao mdtActRelDao;

	@Override
	public List<Object> doPrepare(String planId, String beginDate, String endDate) {
		//???????????��ȡ����Ϊ��ʱ��ע���ָ��
		MdtActInfModel actInfo = mdtAcctMdtActInfService.getActInf(planId);
		//�ս�����У��
		String retMsg = checkMdtDayendCancel (actInfo, beginDate, endDate);
		if (!CommonConstant.RETMSG_SUCCESS.equals(retMsg)) {
			return null;//retMsg;
		}
		//���뽻�ױ�???????? ����ɹ��򲻳ɹ�
		String trdNo = "";
		mdtDayendTrdDao.insertMdtDayendTrd(new MdtDayendTrdModel(trdNo, beginDate, endDate,"2"));
		return null;
	}

	@Override
	public List<Object> doTrade(String trdNo, MdtActInfModel actInfo) {
		//��ȡ�ս����뽻����Ϣ
		MdtDayendTrdModel dayendModel = mdtDayendTrdDao.getMdtDayendTrd(trdNo);
		
		int inerval = Integer.parseInt(CommonUtils.getDaysBetween2Date(dayendModel.getBeginDate(), dayendModel.getEndDate()));
		String dateTmp = "";
		String trdNoDayend = "";
		for (int i=0; i<=inerval; i++) {
			dateTmp = CommonUtils.addDays(dayendModel.getBeginDate(), i);
			//��ȡ��Ӧ�ս�Ľ�����ˮ�� 
			trdNoDayend = mdtDayendInfDao.getMdtDayendInf(actInfo.getActId(), dateTmp).getTrdNo();
			//��صڶ������л����й���Ѻ��йܷѵ�������
			mdtDetTrdDao.reverseMdtAndTrustFee(actInfo.getActId(), dateTmp, trdNoDayend);
			//��Ϊ�������һ��
			if (CommonUtils.isLastDayOfMon(dateTmp)) {
				//��Ϣ��ʼ����
				String beginDateDraw = mdtAcctdetInfDao.getBeginDateCalDraw(actInfo.getActId(), 
						dateTmp, actInfo.getActDate());
				//��� �����ת
				mdtDetTrdDao.reverseMdtMonAcct(actInfo.getActId(), trdNoDayend, dateTmp, beginDateDraw);
				//��ĩ ȡ����ĩ����
				mdtActMoRptDao.deleteMdtActMoRpt(actInfo.getActId(), dateTmp);
			}
			//�����ս���Ϣ��Ϊδ�ս�
			mdtDayendInfDao.updForDayendCancel(actInfo.getActId(), dateTmp, trdNoDayend);
			
		}
		//����?????? 
		mdtAcctRecordService.doTrade(trdNo, actInfo.getActId());
		//�ս���Ϣ��ʷ�����
		mdtAcctdetHisDao.insertMdtAcctDetHis(trdNo);
		return null;
	}
	
	
	/**
	 * ȡ���ս�����У��
	 * @param actInfo
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public String checkMdtDayendCancel (MdtActInfModel actInfo, String beginDate, String endDate) {
		String actId = actInfo.getActId();
		if (StringUtils.isEmpty(endDate)) {
			return "û�����ս����ڣ��޷�ȡ���ս�";
		}
		if (StringUtils.isEmpty(actInfo.getMdtfeeRate()) || StringUtils.isEmpty(actInfo.getTrustfeeRate())) {
			return "δά�����л����зѻ��йܷѷ��ʣ��޷�ȡ���ս�";
		}
		if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(endDate, CommonUtils.getLastDateOfMonth(beginDate)))) {
			return "���������ȡ���ս�";
		}
		if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(actInfo.getActDate(), beginDate))) {
			return "ȡ���ս����ʼʱ�䲻ӦС�����л���ʼ��������";
		}
		//�����������Ϊ�������һ��
		if (CommonUtils.isLastDayOfMon(endDate)) {
			//��Ϣ��ʼ����
			String startDateDraw = mdtAcctdetInfDao.getBeginDateCalDraw(actId, endDate, actInfo.getActDate());
			if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(beginDate, startDateDraw))) {
				return "ȡ����ĩ�ս����"+startDateDraw +"�������Ϣ��ȡ������Ӹ��տ�ʼȡ���սᡣ";
			}
		} 
		
		return CommonConstant.RETMSG_SUCCESS;
	} 
	
	@Override
	public String getLastDayendDate(String planId) {
		String actId = mdtActRelDao.getMdtActRel(planId).getActId();
		return mdtDayendInfDao.getLastDayendDate(actId);
	}
}
