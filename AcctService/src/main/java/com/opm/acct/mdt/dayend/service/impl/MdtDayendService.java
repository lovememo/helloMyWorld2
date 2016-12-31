package com.opm.acct.mdt.dayend.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.acct.common.CommonConstant;
import com.opm.acct.common.CommonUtils;
import com.opm.acct.common.CurrencyUtils;
import com.opm.acct.mdt.act.inf.service.IMdtAcctMdtActInfService;
import com.opm.acct.mdt.dao.IMdtAcctdetHisDao;
import com.opm.acct.mdt.dao.IMdtActMoRptDao;
import com.opm.acct.mdt.dao.IMdtDayendInfDao;
import com.opm.acct.mdt.dao.IMdtDayendTrdDao;
import com.opm.acct.mdt.dao.impl.MdtAcctdetInfDao;
import com.opm.acct.mdt.dao.impl.MdtActRelDao;
import com.opm.acct.mdt.dao.impl.MdtDetDao;
import com.opm.acct.mdt.dao.impl.MdtDetTrdDao;
import com.opm.acct.mdt.dayend.service.IMdtDayendService;
import com.opm.acct.mdt.model.MdtActInfModel;
import com.opm.acct.mdt.model.MdtActMoRptModel;
import com.opm.acct.mdt.model.MdtActRelModel;
import com.opm.acct.mdt.model.MdtDayendTrdModel;
import com.opm.acct.mdt.model.MdtDetTrdModel;
import com.opm.acct.record.service.IMdtAcctRecordService;
import com.opm.common.business.param.RetResult;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-wanghong01 on 2016/12/8.
 */

@Service("MdtDayendService")
public class MdtDayendService implements IMdtDayendService {

    @SuppressWarnings("unused")
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtDayendService.class);

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
	private MdtDetDao mdtDetDao;
    
    @Autowired
	private MdtActRelDao mdtActRelDao;
    
	@Override
	public List<Object> doPrepare(String planId, String beginDate, String endDate) {
		//???????????��ȡ����Ϊ��ʱ��ע���ָ��
		MdtActInfModel actInfo = mdtAcctMdtActInfService.getActInf(planId);
		//�ս�����У��
		String retMsg = checkMdtDayend (actInfo, beginDate, endDate);
		if (!CommonConstant.RETMSG_SUCCESS.equals(retMsg)) {
			return null;//retMsg;
		}
		//���뽻�ױ�???????? ����ɹ��򲻳ɹ�
		String trdNo = "";
		mdtDayendTrdDao.insertMdtDayendTrd(new MdtDayendTrdModel(trdNo, beginDate, endDate,"1"));
		return null;
	}

	@Override
	public List<Object> doTrade(String trdNo, MdtActInfModel actInfo) {
		//��ȡ�ս����뽻����Ϣ
		MdtDayendTrdModel dayendModel = mdtDayendTrdDao.getMdtDayendTrd(trdNo);
		//�жϽ��������Ƿ������һ��,��������ĩΪ��ĩ������Ҫ�ȼ�Ϣ�������ռ����ʲ���ֵ���������зѣ��йܷѣ���Ϊ��Ϣ�Ļ���Ӱ���ʲ���ֵ
		if (CommonUtils.isLastDayOfMon(dayendModel.getEndDate())) {
			//��ȡ��Ϣ��ʼ����
			String beginDateDraw = mdtAcctdetInfDao.getBeginDateCalDraw(actInfo.getActId(), dayendModel.getEndDate(), actInfo.getActDate());
		    //��Ϣ?????????ʧ�ܴ���
			this.calMdtDailyIntDraw(beginDateDraw, dayendModel.getEndDate(), actInfo.getActId(), trdNo);
			//����???????ʧ�ܴ���  �����м��˴���
			//mdtAcctRecordService.doTrade(trdNo, actInfo.getActId());
		}
		//���ռ����ʲ���ֵ�����ռ������зѡ��йܷѡ�����ú���Ҫ����pim_mdt_det����Ϊ��Ӱ���һ���ʲ���ֵ�ļ��㡣
		int inerval = Integer.parseInt(CommonUtils.getDaysBetween2Date(dayendModel.getBeginDate(), dayendModel.getEndDate()));
		for (int i=0; i<=inerval; i++) {
			this.dealMdtDayend(CommonUtils.addDays(dayendModel.getBeginDate(), i), actInfo, trdNo);
		}
		//�½�
		for (int i=0; i<=inerval; i++) {
			if (CommonUtils.isLastDayOfMon(CommonUtils.addDays(dayendModel.getBeginDate(), i))){
				//��ĩ�����ת
				this.dayendMonAcct(CommonUtils.addDays(dayendModel.getBeginDate(), i), actInfo, trdNo);
				//����????????�����м��˴���
				//mdtAcctRecordService.doTrade(trdNo, actInfo.getActId());
				//��ĩ����
				this.insertMdtActMoRpt(CommonUtils.addDays(dayendModel.getBeginDate(), i), actInfo);
			}
		}
		//����??????�����
		mdtAcctRecordService.doTrade(trdNo, actInfo.getActId());
		//�ս���Ϣ��ʷ�����
		mdtAcctdetHisDao.insertMdtAcctDetHis(trdNo);
		return null;
	}
	
	public List<Object> insertMdtActMoRpt (String date, MdtActInfModel actInfo) {
		String amt = "";
		List<String> mdtSubjectTypeList = new ArrayList<String>();
		List<MdtActMoRptModel> list = new ArrayList<MdtActMoRptModel>();
		//SELECT t.code mdt_subject_type FROM oam_dic_common t WHERE t.type = 'MDT_SUBJECT_TYPE'
		for (String tmp : mdtSubjectTypeList) {
			amt = mdtActMoRptDao.getAmtByDate(actInfo, tmp, date);
			list.add(new MdtActMoRptModel(actInfo.getActId(),date,tmp,amt));
		}
		mdtActMoRptDao.insertMdtActMoRpt(list);
		return null;
	}
	/**
	 * �ս���ĩ����
	 * @param date
	 * @param actInfo
	 * @param trdNo
	 * @return
	 */
	public List<Object> dayendMonAcct (String date,MdtActInfModel actInfo, String trdNo){
		//�����л���Ƽ��˽��ױ� opm_mdt_det_trd
		List<MdtDetTrdModel> list = new ArrayList<MdtDetTrdModel>();
		//���ǡ��йܷѡ�
		String trustfeeAmt = mdtActMoRptDao.getAmtByDate(actInfo, "6404", date);
		list.add(new MdtDetTrdModel(date,"6404","2",trustfeeAmt,trdNo,"�����ת"));
		//���ǡ����зѡ�
		String mdtfeeAmt = mdtActMoRptDao.getAmtByDate(actInfo, "6405", date);
		list.add(new MdtDetTrdModel(date,"6405","2",mdtfeeAmt,trdNo,"�����ת"));
		//���ǡ��������á�
		String otherfeeAmt = mdtActMoRptDao.getAmtByDate(actInfo, "6605", date);
		list.add(new MdtDetTrdModel(date,"6605","2",mdtfeeAmt,trdNo,"�����ת"));
		//��ǡ������Ϣ���롱
		String incomeAmt = mdtActMoRptDao.getAmtByDate(actInfo, "6011", date);
		list.add(new MdtDetTrdModel(date,"6011","2",mdtfeeAmt,trdNo,"�����ת"));
		String tmpAmt1 = CurrencyUtils.sumDouble(trustfeeAmt, mdtfeeAmt)+"";
		String tmpAmt2 = CurrencyUtils.sumDouble(tmpAmt1, otherfeeAmt)+"";
		String amt = CurrencyUtils.subDouble(incomeAmt, tmpAmt2)+"";
		int comResult= new BigDecimal(0).compareTo(new BigDecimal(amt));
		if (0 != comResult) {
			//�跽�����ڴ������ʱ�������ǡ��������󡱣����������ڽ跽���ʱ������ǡ���������
			list.add(new MdtDetTrdModel(date,"4103",comResult >0 ? "2" : "1",Math.abs(Double.valueOf(amt))+"",trdNo,"�����ת"));
			//��������������ڽ跽�����ǡ�δ�������󡱡����ǡ���������,��������������ڴ��������ǡ��������󡱡����ǡ�δ�������󡱣�
			list.add(new MdtDetTrdModel(date,"4103",comResult >0 ? "1" : "2",Math.abs(Double.valueOf(amt))+"",trdNo,"�����ת"));
			list.add(new MdtDetTrdModel(date,"4103",comResult >0 ? "2" : "1",Math.abs(Double.valueOf(amt))+"",trdNo,"�����ת"));
		}
		mdtDetTrdDao.insertMdtDetTrd(list);
		return null;
	}
	/**
	 * �սᴦ��
	 * 1.����i_date�ʲ���ֵ����
     * 2.����i_date+1�йܷѼ���
     * 3.����i_date+1���зѼ���  
	 * @param date
	 * @param actId
	 * @param trdNo
	 * @return
	 */
	public List<Object> dealMdtDayend (String date,MdtActInfModel actInfo, String trdNo) {
		//�ʲ���ֵ����
		this.updateMdtAssetCal(date,actInfo, trdNo);
		//���зѼ��ᡢ�йܷѼ����һ�յġ������ڲ�������v_date+1����
		//���зѼ���
		this.drawMdtfee(date, actInfo, trdNo);
		//�йܷѼ���
		this.drawTrustfee(date, actInfo, trdNo);
		//����???????????�����м��˴���
		//mdtAcctRecordService.doTrade(trdNo, actInfo.getActId());
		return null;
	}
	public List<Object> drawTrustfee (String date,MdtActInfModel actInfo, String trdNo) {
		//���㵱ǰ���ں�һ��
		String  aftDate = CommonUtils.addDays(date, 1);
		//�йܷѼ���
		String amt = this.calTrustfee(aftDate, actInfo);
		//�����л���Ƽ��˽��ױ� opm_mdt_det_trd
		List<MdtDetTrdModel> list = new ArrayList<MdtDetTrdModel>();
		list.add(new MdtDetTrdModel(date,"6404","1",amt,trdNo,"�йܷѼ���"));
		list.add(new MdtDetTrdModel(date,"2207","2",amt,trdNo,"�йܷѼ���"));
		mdtDetTrdDao.insertMdtDetTrd(list);
		return null;
	}
	/**
	 * �йܷѼ���
	 * @param date
	 * @param actInfo
	 * @return
	 */
	public String calTrustfee (String date,MdtActInfModel actInfo){
		//���㵱ǰ����ǰһ��
		String  preDate = CommonUtils.addDays(date, -1);
		String amt = this.calAsset(preDate, actInfo.getActId());
		String rate = actInfo.getTrustfeeRate();
		String days = CommonUtils.getDaysInYear(date)+"";
		String tmp1 = CurrencyUtils.mulDouble(amt, rate)+"";
		String tmp2 = CurrencyUtils.divDouble(tmp1, days, 2)+"";//�������뱣����λС��
		String trustFee =CurrencyUtils.divDouble(tmp2, 100+"", 2)+"" ;
		return trustFee;
	}
	/**
	 * ���зѼ���
	 * @param date
	 * @param actInfo
	 * @param trdNo
	 * @return
	 */
	public List<Object> drawMdtfee (String date,MdtActInfModel actInfo, String trdNo){
		//���㵱ǰ���ں�һ��
		String  aftDate = CommonUtils.addDays(date, 1);
		//���зѼ���
		String amt = this.calMdtfee(aftDate, actInfo);
		//�����л���Ƽ��˽��ױ� opm_mdt_det_trd
		List<MdtDetTrdModel> list = new ArrayList<MdtDetTrdModel>();
		list.add(new MdtDetTrdModel(date,"6405","1",amt,trdNo,"���зѼ���"));
		list.add(new MdtDetTrdModel(date,"2210","2",amt,trdNo,"���зѼ���"));
		mdtDetTrdDao.insertMdtDetTrd(list);
		return null;
	}
	
	/**
	 * ���зѼ���
	 * @param date
	 * @param actInfo
	 * @return
	 */
	public String calMdtfee (String date,MdtActInfModel actInfo){
		//���㵱ǰ����ǰһ��
		String  preDate = CommonUtils.addDays(date, -1);
		String amt = this.calAsset(preDate, actInfo.getActId());
		String rate = actInfo.getMdtfeeRate();
		String days = CommonUtils.getDaysInYear(date)+"";
		String tmp1 = CurrencyUtils.mulDouble(amt, rate)+"";
		String tmp2 = CurrencyUtils.divDouble(tmp1, days, 2)+"";//�������뱣����λС��
		String mdtFee =CurrencyUtils.divDouble(tmp2, 100+"", 2)+"" ;
		return mdtFee;
	}
	/**
	 * �����ʲ���ֵ
	 * @param date
	 * @param actInfo
	 * @param trdNo
	 * @return
	 */
	public List<Object> updateMdtAssetCal(String date,MdtActInfModel actInfo, String trdNo) {
		//�ʲ���ֵ����
		String amt = this.calAsset ( date, actInfo.getActId());
		String preActDate = CommonUtils.addDays(actInfo.getActDate(), -1);
		String isFinished = "0";
		if (CommonConstant.COMPARE_EQUAL.equals(CommonUtils.compareDate(preActDate, date))){
			isFinished = "1";
		}
		//�������л��ʲ���ֵ��д��ÿ���ʲ���ֵ��
		mdtAcctdetInfDao.updOrInsertAmt(actInfo.getActId(), date, isFinished, trdNo, amt);
		return null;
	}
	/**
	 * ÿ���ʲ���ֵ����
	 * @param date
	 * @param actId
	 * @return
	 */
	public String calAsset (String date,String actId) {
		//��ȡǰһ������
		String preDate = CommonUtils.addDays(date, -1);
		//��ȡ������Ϣ��ǰһ�����
		String preAmt = mdtDayendInfDao.getAmtByDate(actId, preDate);
		//��ȡ���л���ϸ�˱����
		String amt = mdtDetDao.getAmtByDate(actId, date);
		//����
		double amtSum = CurrencyUtils.sumDouble(StringUtils.isEmpty(preAmt) ?  0+"" : preAmt, amt);
		
		return amtSum+"";
	}
	
    /**
     * �����½��Ϣ
     * @param beginDate
     * @param endDate
     * @param actId
     * @param trdNo
     * @return
     */
	public List<Object> calMdtDailyIntDraw(String beginDate,String endDate,String actId, String trdNo) {
		int inerval = Integer.parseInt(CommonUtils.getDaysBetween2Date(beginDate, endDate));
		String amt = "";
		//���ռ�Ϣ
		for (int i=0; i<=inerval; i++) {
			amt = mdtAcctdetInfDao.getActLastAmtBefDate(actId, beginDate);
			mdtDetTrdDao.insertDrawForDayend(trdNo, CommonUtils.addDays(beginDate, i), actId, amt);
		}
		return null;
	}
	
	/**
	 * �ս�����У��
	 * @param actInfo
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public String checkMdtDayend (MdtActInfModel actInfo, String beginDate, String endDate) {
		String actId = actInfo.getActId();
		if (StringUtils.isEmpty(beginDate)) {
			return "δά�����л���ʼ�������ڣ��޷��ս�";
		}
		if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(endDate, CommonUtils.getCurrentDate()))) {
			return "ֻ�ܶԵ�ǰ���ڣ�������֮ǰ�����ڽ����ս�";
		}
		if (StringUtils.isEmpty(actInfo.getMdtfeeRate()) || StringUtils.isEmpty(actInfo.getTrustfeeRate())) {
			return "δά�����л����зѻ��йܷѷ��ʣ��޷��ս�";
		}
		String lastDate = CommonUtils.getLastDateOfMonth(beginDate);
		if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(endDate, lastDate))) {
			return "����������ս�";
		}
		if (mdtDayendInfDao.isFinishDayendInterval(actId, beginDate, endDate)) {
			return "������������սᣬ�޷��ύ����";
		}
		//�����������Ϊ�������һ��
		if (CommonUtils.isLastDayOfMon(endDate)) {
			//У�鱾�������Ŀ����Ƿ�Ϊ0 ���������Ŀ��������˺��ٽ�����ĩ�ս�
			String amt = mdtActMoRptDao.getAmtByDate(actInfo,  "4103", endDate);
			if (0 != new BigDecimal(0).compareTo(new BigDecimal(amt))) {
				return "���������Ŀ��������˺��ٽ�����ĩ�ս�";
			}
			//���д���Ŀ��ĩ���������л���ϸ��ĩ���������ύ��ĩ���һ���ս�����
			String bankDeposit = mdtActMoRptDao.getAmtByDate(actInfo, "1002", endDate);
			String acctdetAmt = mdtAcctdetInfDao.getActLastAmtBefDate(actId, endDate);
			if (0 != new BigDecimal(bankDeposit).compareTo(new BigDecimal(acctdetAmt))) {
				return "���д���Ŀ��ĩ���������л���ϸ��ĩ���";
			}
		} else {
			//�ж��Ƿ��Ϣ
			if (!mdtAcctdetInfDao.isCalDrawBtwDateAndLastest(actId, endDate)) {
				//��ȡδ��Ϣ��ʼ����
				String beginDateUnCalDraw = mdtAcctdetInfDao.getBeginDateCalDraw(actId, endDate, actInfo.getActDate());
				return  beginDateUnCalDraw + "���ڿ�ʼδ������Ϣ���޷��ս�";
				
			}
		}
		
		return CommonConstant.RETMSG_SUCCESS;
	} 
	
	@Override
	public String getFirstDayNoEnd(String planId) {
		String actId = mdtActRelDao.getMdtActRel(planId).getActId();
		String dayendDateFinished = mdtDayendInfDao.getDayendDateFinished(actId);
		return CommonUtils.addDays(dayendDateFinished, 1);
	}

	@Override
	public MdtDayendTrdModel qryMdtDayendApp(String trdNo) {
		return mdtDayendTrdDao.getMdtDayendTrd(trdNo);
	}
	
	@Override
	public RetResult isFinishMonend(String planId, String date) {
		MdtActRelModel relModel = mdtActRelDao.getMdtActRel(planId);
		RetResult rr = new RetResult();
		Map<String, Object> contextObj = new HashMap<>();
		contextObj.put("planId", planId);
		rr.setContextObj(contextObj);
		rr.setReturnObj(contextObj);
		if (null == relModel) {
			return  new RetResult(false,"�޴˼ƻ����л���Ϣ",rr.getReturnObj());
		}
		boolean isFinish = mdtDayendInfDao.isFinishMonend(relModel.getActId(), date);
		
		contextObj.put("isFinish", isFinish ? "true" : "false" );
		rr.setContextObj(contextObj);
		rr.setReturnObj(contextObj);
		return new RetResult(true,"�ж��Ƿ��½����",rr.getReturnObj());
	}
}
