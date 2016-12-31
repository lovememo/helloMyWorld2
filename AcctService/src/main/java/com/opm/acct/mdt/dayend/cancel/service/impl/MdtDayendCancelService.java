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
		//???????????获取对象为空时，注意空指针
		MdtActInfModel actInfo = mdtAcctMdtActInfService.getActInf(planId);
		//日结申请校验
		String retMsg = checkMdtDayendCancel (actInfo, beginDate, endDate);
		if (!CommonConstant.RETMSG_SUCCESS.equals(retMsg)) {
			return null;//retMsg;
		}
		//插入交易表???????? 插入成功或不成功
		String trdNo = "";
		mdtDayendTrdDao.insertMdtDayendTrd(new MdtDayendTrdModel(trdNo, beginDate, endDate,"2"));
		return null;
	}

	@Override
	public List<Object> doTrade(String trdNo, MdtActInfModel actInfo) {
		//获取日结申请交易信息
		MdtDayendTrdModel dayendModel = mdtDayendTrdDao.getMdtDayendTrd(trdNo);
		
		int inerval = Integer.parseInt(CommonUtils.getDaysBetween2Date(dayendModel.getBeginDate(), dayendModel.getEndDate()));
		String dateTmp = "";
		String trdNoDayend = "";
		for (int i=0; i<=inerval; i++) {
			dateTmp = CommonUtils.addDays(dayendModel.getBeginDate(), i);
			//获取对应日结的交易流水号 
			trdNoDayend = mdtDayendInfDao.getMdtDayendInf(actInfo.getActId(), dateTmp).getTrdNo();
			//冲回第二日受托户受托管理费和托管费的账务处理
			mdtDetTrdDao.reverseMdtAndTrustFee(actInfo.getActId(), dateTmp, trdNoDayend);
			//若为当月最后一日
			if (CommonUtils.isLastDayOfMon(dateTmp)) {
				//计息起始日期
				String beginDateDraw = mdtAcctdetInfDao.getBeginDateCalDraw(actInfo.getActId(), 
						dateTmp, actInfo.getActDate());
				//冲回 损益结转
				mdtDetTrdDao.reverseMdtMonAcct(actInfo.getActId(), trdNoDayend, dateTmp, beginDateDraw);
				//月末 取消月末报表
				mdtActMoRptDao.deleteMdtActMoRpt(actInfo.getActId(), dateTmp);
			}
			//更新日结信息表为未日结
			mdtDayendInfDao.updForDayendCancel(actInfo.getActId(), dateTmp, trdNoDayend);
			
		}
		//记账?????? 
		mdtAcctRecordService.doTrade(trdNo, actInfo.getActId());
		//日结信息历史表更新
		mdtAcctdetHisDao.insertMdtAcctDetHis(trdNo);
		return null;
	}
	
	
	/**
	 * 取消日结申请校验
	 * @param actInfo
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public String checkMdtDayendCancel (MdtActInfModel actInfo, String beginDate, String endDate) {
		String actId = actInfo.getActId();
		if (StringUtils.isEmpty(endDate)) {
			return "没有已日结日期，无法取消日结";
		}
		if (StringUtils.isEmpty(actInfo.getMdtfeeRate()) || StringUtils.isEmpty(actInfo.getTrustfeeRate())) {
			return "未维护受托户受托费或托管费费率，无法取消日结";
		}
		if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(endDate, CommonUtils.getLastDateOfMonth(beginDate)))) {
			return "不允许跨月取消日结";
		}
		if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(actInfo.getActDate(), beginDate))) {
			return "取消日结的起始时间不应小于受托户起始核算日期";
		}
		//如果结束日期为当月最后一天
		if (CommonUtils.isLastDayOfMon(endDate)) {
			//计息起始日期
			String startDateDraw = mdtAcctdetInfDao.getBeginDateCalDraw(actId, endDate, actInfo.getActDate());
			if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(beginDate, startDateDraw))) {
				return "取消月末日结后，自"+startDateDraw +"计提的利息均取消，请从该日开始取消日结。";
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
