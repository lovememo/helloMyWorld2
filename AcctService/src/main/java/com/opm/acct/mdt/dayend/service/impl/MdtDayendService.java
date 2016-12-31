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
		//???????????获取对象为空时，注意空指针
		MdtActInfModel actInfo = mdtAcctMdtActInfService.getActInf(planId);
		//日结申请校验
		String retMsg = checkMdtDayend (actInfo, beginDate, endDate);
		if (!CommonConstant.RETMSG_SUCCESS.equals(retMsg)) {
			return null;//retMsg;
		}
		//插入交易表???????? 插入成功或不成功
		String trdNo = "";
		mdtDayendTrdDao.insertMdtDayendTrd(new MdtDayendTrdModel(trdNo, beginDate, endDate,"1"));
		return null;
	}

	@Override
	public List<Object> doTrade(String trdNo, MdtActInfModel actInfo) {
		//获取日结申请交易信息
		MdtDayendTrdModel dayendModel = mdtDayendTrdDao.getMdtDayendTrd(trdNo);
		//判断结束日期是否当月最后一天,若结账期末为月末，则需要先计息后再逐日计算资产净值，计提手托费，托管费，因为计息的话会影响资产净值
		if (CommonUtils.isLastDayOfMon(dayendModel.getEndDate())) {
			//获取计息起始日期
			String beginDateDraw = mdtAcctdetInfDao.getBeginDateCalDraw(actInfo.getActId(), dayendModel.getEndDate(), actInfo.getActDate());
		    //计息?????????失败处理
			this.calMdtDailyIntDraw(beginDateDraw, dayendModel.getEndDate(), actInfo.getActId(), trdNo);
			//记账???????失败处理  最后进行记账处理
			//mdtAcctRecordService.doTrade(trdNo, actInfo.getActId());
		}
		//逐日计算资产净值，逐日计提受托费、托管费、计提好后需要插入pim_mdt_det表，因为会影响后一日资产净值的计算。
		int inerval = Integer.parseInt(CommonUtils.getDaysBetween2Date(dayendModel.getBeginDate(), dayendModel.getEndDate()));
		for (int i=0; i<=inerval; i++) {
			this.dealMdtDayend(CommonUtils.addDays(dayendModel.getBeginDate(), i), actInfo, trdNo);
		}
		//月结
		for (int i=0; i<=inerval; i++) {
			if (CommonUtils.isLastDayOfMon(CommonUtils.addDays(dayendModel.getBeginDate(), i))){
				//月末损益结转
				this.dayendMonAcct(CommonUtils.addDays(dayendModel.getBeginDate(), i), actInfo, trdNo);
				//记账????????最后进行记账处理
				//mdtAcctRecordService.doTrade(trdNo, actInfo.getActId());
				//月末报表
				this.insertMdtActMoRpt(CommonUtils.addDays(dayendModel.getBeginDate(), i), actInfo);
			}
		}
		//记账??????最后处理
		mdtAcctRecordService.doTrade(trdNo, actInfo.getActId());
		//日结信息历史表更新
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
	 * 日结月末处理
	 * @param date
	 * @param actInfo
	 * @param trdNo
	 * @return
	 */
	public List<Object> dayendMonAcct (String date,MdtActInfModel actInfo, String trdNo){
		//入受托户会计记账交易表 opm_mdt_det_trd
		List<MdtDetTrdModel> list = new ArrayList<MdtDetTrdModel>();
		//贷记“托管费”
		String trustfeeAmt = mdtActMoRptDao.getAmtByDate(actInfo, "6404", date);
		list.add(new MdtDetTrdModel(date,"6404","2",trustfeeAmt,trdNo,"损益结转"));
		//贷记“受托费”
		String mdtfeeAmt = mdtActMoRptDao.getAmtByDate(actInfo, "6405", date);
		list.add(new MdtDetTrdModel(date,"6405","2",mdtfeeAmt,trdNo,"损益结转"));
		//贷记“其他费用”
		String otherfeeAmt = mdtActMoRptDao.getAmtByDate(actInfo, "6605", date);
		list.add(new MdtDetTrdModel(date,"6605","2",mdtfeeAmt,trdNo,"损益结转"));
		//借记“存款利息收入”
		String incomeAmt = mdtActMoRptDao.getAmtByDate(actInfo, "6011", date);
		list.add(new MdtDetTrdModel(date,"6011","2",mdtfeeAmt,trdNo,"损益结转"));
		String tmpAmt1 = CurrencyUtils.sumDouble(trustfeeAmt, mdtfeeAmt)+"";
		String tmpAmt2 = CurrencyUtils.sumDouble(tmpAmt1, otherfeeAmt)+"";
		String amt = CurrencyUtils.subDouble(incomeAmt, tmpAmt2)+"";
		int comResult= new BigDecimal(0).compareTo(new BigDecimal(amt));
		if (0 != comResult) {
			//借方金额大于贷方金额时，差额贷记“本期利润”；贷方金额大于借方金额时，差额借记“本期利润”
			list.add(new MdtDetTrdModel(date,"4103",comResult >0 ? "2" : "1",Math.abs(Double.valueOf(amt))+"",trdNo,"损益结转"));
			//若本期利润余额在借方，则借记“未分配利润”、贷记“本期利润”,若本期利润余额在贷方，则借记“本期利润”、贷记“未分配利润”，
			list.add(new MdtDetTrdModel(date,"4103",comResult >0 ? "1" : "2",Math.abs(Double.valueOf(amt))+"",trdNo,"利润结转"));
			list.add(new MdtDetTrdModel(date,"4103",comResult >0 ? "2" : "1",Math.abs(Double.valueOf(amt))+"",trdNo,"利润结转"));
		}
		mdtDetTrdDao.insertMdtDetTrd(list);
		return null;
	}
	/**
	 * 日结处理
	 * 1.当日i_date资产净值更新
     * 2.次日i_date+1托管费计提
     * 3.次日i_date+1受托费计提  
	 * @param date
	 * @param actId
	 * @param trdNo
	 * @return
	 */
	public List<Object> dealMdtDayend (String date,MdtActInfModel actInfo, String trdNo) {
		//资产净值更新
		this.updateMdtAssetCal(date,actInfo, trdNo);
		//受托费计提、托管费计提后一日的。函数内部进行了v_date+1处理
		//受托费计提
		this.drawMdtfee(date, actInfo, trdNo);
		//托管费计提
		this.drawTrustfee(date, actInfo, trdNo);
		//记账???????????最后进行记账处理
		//mdtAcctRecordService.doTrade(trdNo, actInfo.getActId());
		return null;
	}
	public List<Object> drawTrustfee (String date,MdtActInfModel actInfo, String trdNo) {
		//计算当前日期后一日
		String  aftDate = CommonUtils.addDays(date, 1);
		//托管费计提
		String amt = this.calTrustfee(aftDate, actInfo);
		//入受托户会计记账交易表 opm_mdt_det_trd
		List<MdtDetTrdModel> list = new ArrayList<MdtDetTrdModel>();
		list.add(new MdtDetTrdModel(date,"6404","1",amt,trdNo,"托管费计提"));
		list.add(new MdtDetTrdModel(date,"2207","2",amt,trdNo,"托管费计提"));
		mdtDetTrdDao.insertMdtDetTrd(list);
		return null;
	}
	/**
	 * 托管费计提
	 * @param date
	 * @param actInfo
	 * @return
	 */
	public String calTrustfee (String date,MdtActInfModel actInfo){
		//计算当前日期前一日
		String  preDate = CommonUtils.addDays(date, -1);
		String amt = this.calAsset(preDate, actInfo.getActId());
		String rate = actInfo.getTrustfeeRate();
		String days = CommonUtils.getDaysInYear(date)+"";
		String tmp1 = CurrencyUtils.mulDouble(amt, rate)+"";
		String tmp2 = CurrencyUtils.divDouble(tmp1, days, 2)+"";//四舍五入保留两位小数
		String trustFee =CurrencyUtils.divDouble(tmp2, 100+"", 2)+"" ;
		return trustFee;
	}
	/**
	 * 受托费计提
	 * @param date
	 * @param actInfo
	 * @param trdNo
	 * @return
	 */
	public List<Object> drawMdtfee (String date,MdtActInfModel actInfo, String trdNo){
		//计算当前日期后一日
		String  aftDate = CommonUtils.addDays(date, 1);
		//手托费计提
		String amt = this.calMdtfee(aftDate, actInfo);
		//入受托户会计记账交易表 opm_mdt_det_trd
		List<MdtDetTrdModel> list = new ArrayList<MdtDetTrdModel>();
		list.add(new MdtDetTrdModel(date,"6405","1",amt,trdNo,"受托费计提"));
		list.add(new MdtDetTrdModel(date,"2210","2",amt,trdNo,"受托费计提"));
		mdtDetTrdDao.insertMdtDetTrd(list);
		return null;
	}
	
	/**
	 * 受托费计提
	 * @param date
	 * @param actInfo
	 * @return
	 */
	public String calMdtfee (String date,MdtActInfModel actInfo){
		//计算当前日期前一日
		String  preDate = CommonUtils.addDays(date, -1);
		String amt = this.calAsset(preDate, actInfo.getActId());
		String rate = actInfo.getMdtfeeRate();
		String days = CommonUtils.getDaysInYear(date)+"";
		String tmp1 = CurrencyUtils.mulDouble(amt, rate)+"";
		String tmp2 = CurrencyUtils.divDouble(tmp1, days, 2)+"";//四舍五入保留两位小数
		String mdtFee =CurrencyUtils.divDouble(tmp2, 100+"", 2)+"" ;
		return mdtFee;
	}
	/**
	 * 更新资产净值
	 * @param date
	 * @param actInfo
	 * @param trdNo
	 * @return
	 */
	public List<Object> updateMdtAssetCal(String date,MdtActInfModel actInfo, String trdNo) {
		//资产净值更新
		String amt = this.calAsset ( date, actInfo.getActId());
		String preActDate = CommonUtils.addDays(actInfo.getActDate(), -1);
		String isFinished = "0";
		if (CommonConstant.COMPARE_EQUAL.equals(CommonUtils.compareDate(preActDate, date))){
			isFinished = "1";
		}
		//计算受托户资产净值，写入每日资产净值表
		mdtAcctdetInfDao.updOrInsertAmt(actInfo.getActId(), date, isFinished, trdNo, amt);
		return null;
	}
	/**
	 * 每日资产净值计算
	 * @param date
	 * @param actId
	 * @return
	 */
	public String calAsset (String date,String actId) {
		//获取前一日日期
		String preDate = CommonUtils.addDays(date, -1);
		//获取日终信息表前一日余额
		String preAmt = mdtDayendInfDao.getAmtByDate(actId, preDate);
		//获取受托户明细账表余额
		String amt = mdtDetDao.getAmtByDate(actId, date);
		//加总
		double amtSum = CurrencyUtils.sumDouble(StringUtils.isEmpty(preAmt) ?  0+"" : preAmt, amt);
		
		return amtSum+"";
	}
	
    /**
     * 日终月结计息
     * @param beginDate
     * @param endDate
     * @param actId
     * @param trdNo
     * @return
     */
	public List<Object> calMdtDailyIntDraw(String beginDate,String endDate,String actId, String trdNo) {
		int inerval = Integer.parseInt(CommonUtils.getDaysBetween2Date(beginDate, endDate));
		String amt = "";
		//逐日计息
		for (int i=0; i<=inerval; i++) {
			amt = mdtAcctdetInfDao.getActLastAmtBefDate(actId, beginDate);
			mdtDetTrdDao.insertDrawForDayend(trdNo, CommonUtils.addDays(beginDate, i), actId, amt);
		}
		return null;
	}
	
	/**
	 * 日结申请校验
	 * @param actInfo
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public String checkMdtDayend (MdtActInfModel actInfo, String beginDate, String endDate) {
		String actId = actInfo.getActId();
		if (StringUtils.isEmpty(beginDate)) {
			return "未维护受托户起始核算日期，无法日结";
		}
		if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(endDate, CommonUtils.getCurrentDate()))) {
			return "只能对当前日期（不含）之前的日期进行日结";
		}
		if (StringUtils.isEmpty(actInfo.getMdtfeeRate()) || StringUtils.isEmpty(actInfo.getTrustfeeRate())) {
			return "未维护受托户受托费或托管费费率，无法日结";
		}
		String lastDate = CommonUtils.getLastDateOfMonth(beginDate);
		if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(endDate, lastDate))) {
			return "不允许跨月日结";
		}
		if (mdtDayendInfDao.isFinishDayendInterval(actId, beginDate, endDate)) {
			return "该日期已完成日结，无法提交申请";
		}
		//如果结束日期为当月最后一天
		if (CommonUtils.isLastDayOfMon(endDate)) {
			//校验本期利润科目余额是否为0 本期利润科目有余额，请调账后再进行月末日结
			String amt = mdtActMoRptDao.getAmtByDate(actInfo,  "4103", endDate);
			if (0 != new BigDecimal(0).compareTo(new BigDecimal(amt))) {
				return "本期利润科目有余额，请调账后再进行月末日结";
			}
			//银行存款科目月末余额不等于受托户明细月末余额，不允许提交月末最后一日日结申请
			String bankDeposit = mdtActMoRptDao.getAmtByDate(actInfo, "1002", endDate);
			String acctdetAmt = mdtAcctdetInfDao.getActLastAmtBefDate(actId, endDate);
			if (0 != new BigDecimal(bankDeposit).compareTo(new BigDecimal(acctdetAmt))) {
				return "银行存款科目月末余额不等于受托户明细月末余额";
			}
		} else {
			//判断是否计息
			if (!mdtAcctdetInfDao.isCalDrawBtwDateAndLastest(actId, endDate)) {
				//获取未计息起始日期
				String beginDateUnCalDraw = mdtAcctdetInfDao.getBeginDateCalDraw(actId, endDate, actInfo.getActDate());
				return  beginDateUnCalDraw + "日期开始未计提利息，无法日结";
				
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
			return  new RetResult(false,"无此计划受托户信息",rr.getReturnObj());
		}
		boolean isFinish = mdtDayendInfDao.isFinishMonend(relModel.getActId(), date);
		
		contextObj.put("isFinish", isFinish ? "true" : "false" );
		rr.setContextObj(contextObj);
		rr.setReturnObj(contextObj);
		return new RetResult(true,"判断是否月结完成",rr.getReturnObj());
	}
}
