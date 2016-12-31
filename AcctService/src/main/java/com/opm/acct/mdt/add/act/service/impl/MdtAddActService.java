package com.opm.acct.mdt.add.act.service.impl;

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
import com.opm.acct.mdt.act.inf.service.IMdtAcctMdtActInfService;
import com.opm.acct.mdt.add.act.service.IMdtAddActService;
import com.opm.acct.mdt.dao.IMdtActInfDao;
import com.opm.acct.mdt.dao.IMdtActMoRptDao;
import com.opm.acct.mdt.dao.IMdtActRelDao;
import com.opm.acct.mdt.dao.IMdtAdjActTrdDao;
import com.opm.acct.mdt.dao.IMdtDayendInfDao;
import com.opm.acct.mdt.dao.impl.MdtAcctdetInfDao;
import com.opm.acct.mdt.dao.impl.MdtDetTrdDao;
import com.opm.acct.mdt.model.MdtAcctdetInfModel;
import com.opm.acct.mdt.model.MdtActInfModel;
import com.opm.acct.mdt.model.MdtAdjActTrdModel;
import com.opm.acct.mdt.model.MdtDetTrdModel;
import com.opm.acct.record.service.IMdtAcctRecordService;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-wanghong01 on 2016/12/8.
 */

@Service("MdtAddActService")
public class MdtAddActService implements IMdtAddActService {

    @SuppressWarnings("unused")
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtAddActService.class);

    @Autowired
    private MdtAcctdetInfDao mdtAcctdetInfDao;
    
    @Autowired
	private IMdtAcctMdtActInfService mdtAcctMdtActInfService;
    
    @Autowired
    private MdtDetTrdDao mdtDetTrdDao;
    
    @Autowired
	private IMdtAcctRecordService mdtAcctRecordService;
    
    @Autowired
    private IMdtDayendInfDao mdtDayendInfDao;
    
    @Autowired
    private IMdtActMoRptDao mdtActMoRptDao;
    
    @Autowired
	private IMdtAdjActTrdDao mdtAdjActTrdDao;
    
    @Autowired
   	private IMdtActRelDao mdtActRelDao;
    
    @Autowired
   	private IMdtActInfDao mdtActInfDao;

	@Override
	public List<Object> doPrepare(String planId, String mdtAddActType, String opDate, String mdtAddOpType,
			String reason, String mdtSubjectTypeStr, String debitCreditFlagStr,
			String tradeAmtStr,String memo) {

		List<String> mdtSubjectTypeList = CommonUtils.getArrayFromStr(mdtSubjectTypeStr, CommonConstant.SEPARATOR);
		if (null == mdtSubjectTypeList || 0 == mdtSubjectTypeList.size()) {
			return null;
		}
		List<String> debitCreditFlagList = CommonUtils.getArrayFromStr(debitCreditFlagStr, CommonConstant.SEPARATOR);
		List<String> tradeAmtList = CommonUtils.getArrayFromStr(tradeAmtStr, CommonConstant.SEPARATOR);
		//插入交易表???????? 插入成功或不成功
		String trdNo = "";
		List<MdtAdjActTrdModel> list = new ArrayList<MdtAdjActTrdModel>();
		for (int i=0; i<mdtSubjectTypeList.size(); i++) {
			list.add(new MdtAdjActTrdModel (trdNo, mdtAddActType, opDate, mdtAddOpType,
					reason, mdtSubjectTypeList.get(i), debitCreditFlagList.get(i), tradeAmtList.get(i)));
		}
		//???????????获取对象为空时，注意空指针
		MdtActInfModel actInfo = mdtAcctMdtActInfService.getActInf(planId);
		//补帐申请校验
		String retMsg = checkMdtAddAct (actInfo, opDate, mdtAddOpType,mdtAddActType,list);
		if (!CommonConstant.RETMSG_SUCCESS.equals(retMsg)) {
			return null;//retMsg;
		}
		mdtAdjActTrdDao.insertMdtAdjActTrd(list);
//		//校验
//		if (StringUtils.isEmpty(mdtAddOpType) || mdtAddOpType.indexOf("1|2|3|4|5|6|7") < 0) {
//			String debitAmt = mdtAdjActTrdDao.getDebitOrCreditAmt(trdNo, "1");
//			String creditAmt = mdtAdjActTrdDao.getDebitOrCreditAmt(trdNo, "2");
//			double subAmt = CurrencyUtils.subDouble(debitAmt, creditAmt);
//			if (0 != new BigDecimal(0).compareTo(new BigDecimal(subAmt))) {
//				return null; //"借贷不等";
//			}
//			//各科目净增加额不满足资产-负债-收入+费用=所有者权益
//			String creditAmtSum = mdtAdjActTrdDao.getCreditAmt(trdNo);
//			if (0 != new BigDecimal(0).compareTo(new BigDecimal(creditAmtSum))) {
//				return null; //"各科目净增加额不满足资产-负债=所有者权益+收入-费用";
//			}
//			//当补帐双方的科目都是资产和负债时，所有者权益是不变的，只要任何一方有一个不是资产或负债，都认为所有者权益是变了的（实际也可能不变），都要取消日结。
//			List<MdtAdjActTrdModel> listTmp = mdtAdjActTrdDao.getMdtAdjActTrdForCheck(trdNo);
//			if (null == listTmp || 0 == listTmp.size()) {
//				isDayendFlag = "0";
//			}
//		}
//		if ("2".equals(mdtAddActType) && 
//				mdtAddOpType.indexOf("1|2|3|4|5|6|7") >= 0) {
//			isDayendFlag = "0";
//		}
//		if ("0".equals(isDayendFlag)) {
//			String opDateMax = mdtAdjActTrdDao.getMaxOpDate(trdNo);
//			if (mdtDayendInfDao.isFinishDayend(actInfo.getActId(), opDateMax))  {
//				return null;//opDateMax+"已日结，请取消该日日结后再处理申请";
//			}
//		}
		return null;
	}

	@Override
	public List<Object> doTrade(String trdNo,String planId) {
		String actId = mdtActRelDao.getMdtActRel(planId).getActId();
		MdtActInfModel actInfo = mdtActInfDao.getMdtActInf(actId);
		String mdtAddActTypeMax =  mdtAdjActTrdDao.getMaxValueByFieldName(trdNo, "mdt_add_act_type");
		String mdtAddOpTypeMax =  mdtAdjActTrdDao.getMaxValueByFieldName(trdNo, "mdt_add_op_type");
		String opDateMax = "";
		String tradeAmtMax = "";
		if (mdtAddOpTypeMax.indexOf("1|2|3|4|5|6|7") >= 0) {
			opDateMax = mdtAdjActTrdDao.getMaxValueByFieldName(trdNo, "op_date"); 
			tradeAmtMax = mdtAdjActTrdDao.getMaxValueByFieldName(trdNo, "trade_amt");
		}
		List<MdtDetTrdModel> list = new ArrayList<MdtDetTrdModel>();
		if ("2".equals(mdtAddActTypeMax) && "1".equals(mdtAddOpTypeMax)) {
			//借记“其他应付款-待投资未确认”、贷记“其他应付款-待投资已确认”
			list.add(new MdtDetTrdModel(opDateMax, "224101", "1", tradeAmtMax, trdNo,"转入收款登记"));
			list.add(new MdtDetTrdModel(opDateMax, "224102", "2", tradeAmtMax, trdNo,"转入收款登记"));
			//借记“其他应付款-待投资已确认”、贷记“实收基金”
			list.add(new MdtDetTrdModel(opDateMax, "224102", "1", tradeAmtMax, trdNo,"转入到账匹配"));
			list.add(new MdtDetTrdModel(opDateMax, "4001", "2", tradeAmtMax, trdNo,"转入到账匹配"));
		} else
			//结息
		if ("2".equals(mdtAddActTypeMax) && "2".equals(mdtAddOpTypeMax)) {
			//借记“银行存款”（金额为录入的金额），贷记“应收利息”（金额为录入日期前一日应收利息的余额），贷记“存款利息收入”（金额为银行存款-应收利息）
			list.add(new MdtDetTrdModel(opDateMax, "1002", "1", tradeAmtMax, trdNo,"结息"));
			String amt = mdtActMoRptDao.getAmtByDate(actInfo, "1204", CommonUtils.addDays(opDateMax, -1));
			list.add(new MdtDetTrdModel(opDateMax, "1204", "2", amt, trdNo,"结息"));
			amt = mdtActMoRptDao.getAmtByDate(actInfo, "1204", CommonUtils.addDays(opDateMax, -1));
			amt = CurrencyUtils.subDouble(tradeAmtMax, amt)+"";
			list.add(new MdtDetTrdModel(opDateMax, "6011", "2", amt, trdNo,"结息"));
		} else 
		if ("2".equals(mdtAddActTypeMax) && "3".equals(mdtAddOpTypeMax)) {
			//借记“其他费用”、贷记“银行存款”
			list.add(new MdtDetTrdModel(opDateMax, "6605", "1", tradeAmtMax, trdNo,"其他费用"));
			list.add(new MdtDetTrdModel(opDateMax, "1002", "2", tradeAmtMax, trdNo,"其他费用"));
		} else 
		if ("2".equals(mdtAddActTypeMax) && "4".equals(mdtAddOpTypeMax)) {
			//借记“应交税金”、贷记“银行存款”
			list.add(new MdtDetTrdModel(opDateMax, "2221", "1", tradeAmtMax, trdNo,"个税支付"));
			list.add(new MdtDetTrdModel(opDateMax, "1002", "2", tradeAmtMax, trdNo,"个税支付"));
		} else 
			//记账前退款
		if ("2".equals(mdtAddActTypeMax) && "5".equals(mdtAddOpTypeMax)) {
			//借记“其他应付款-待投资未确认”、贷记“银行存款”
			list.add(new MdtDetTrdModel(opDateMax, "224101", "1", tradeAmtMax, trdNo,"退款"));
			list.add(new MdtDetTrdModel(opDateMax, "1002", "2", tradeAmtMax, trdNo,"退款"));
		} else 
			//记账后退款
		if ("2".equals(mdtAddActTypeMax) && "6".equals(mdtAddOpTypeMax)) {
			//借记“实收基金”、贷记“银行存款”
			list.add(new MdtDetTrdModel(opDateMax, "4001", "1", tradeAmtMax, trdNo,"退款"));
			list.add(new MdtDetTrdModel(opDateMax, "1002", "2", tradeAmtMax, trdNo,"退款"));
		} else 
		if ("2".equals(mdtAddActTypeMax) && "7".equals(mdtAddOpTypeMax)) {
			//借记“未分配利润”，贷记“银行存款”
			list.add(new MdtDetTrdModel(opDateMax, "4104", "1", tradeAmtMax, trdNo,"利润划拨"));
			list.add(new MdtDetTrdModel(opDateMax, "1002", "2", tradeAmtMax, trdNo,"利润划拨"));
		} else 
		if ("1".equals(mdtAddActTypeMax) || "99".equals(mdtAddOpTypeMax)) {
			List<MdtAdjActTrdModel> listTmp = mdtAdjActTrdDao.getMdtAdjActTrd(trdNo);
			if (null != listTmp && 0 != listTmp.size()) {
				String memo = "补帐";
				if ("2".equals(mdtAddActTypeMax)) {
					memo = "其他业务";
				}
				for (MdtAdjActTrdModel tmp : listTmp) {
					list.add(new MdtDetTrdModel(tmp.getOpDate(), tmp.getMdtSubjectType(), 
							tmp.getDebitCreditFlag(), tmp.getTradeAmt(), trdNo,memo));
				}
			}
		}
		mdtDetTrdDao.insertMdtDetTrd(list);
		mdtAcctRecordService.doTrade(trdNo, planId);
		return null;
	}
	
	/**
	 * 日结申请校验
	 * @param actInfo
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public String checkMdtAddAct (MdtActInfModel actInfo,String opDate, String mdtAddOpType, String mdtAddActType,List<MdtAdjActTrdModel> list) {
		if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(actInfo.getActDate(), opDate))) {
			return "受托户补帐的日期不能早于受托户核算起始日期";
		}
		if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(opDate, CommonUtils.getCurrentDate()))) {
			return "受托户补帐的日期不能超过系统时间";
		}
		if (mdtDayendInfDao.isFinishMonend(actInfo.getActId(), opDate)) {
			return "补账当月最后一日已日结，请取消该日日结后再提交申请";
		}
		if (mdtAddOpType.equals("2")) {
			List<MdtAcctdetInfModel> listTmp = mdtAcctdetInfDao.getInfBetween2Date(actInfo.getActId(), 
					opDate, CommonUtils.getLastDateOfMonth(opDate));
			if (null == listTmp || 0 == listTmp.size()) {
				return "所录日期当日受托户明细未导入，无法取得前一日应收利息余额";
			}
		}
		String isDayendFlag = "1"; //是否需要判断日结 0 是 1 否
		int exsitsCount = 0;;//根据此字段赋值isDayendFlag
		String trdNo = "";
		//借贷是否相等
		if (StringUtils.isEmpty(mdtAddOpType) || mdtAddOpType.indexOf("1|2|3|4|5|6|7") < 0) {
			//校验借贷是否相等
			double debitAmt = 0;
			double creditAmt = 0;
			for (MdtAdjActTrdModel tmp : list) {
				if ("1".equals(tmp.getDebitCreditFlag())) {
					debitAmt = CurrencyUtils.sumDouble(debitAmt+"", tmp.getTradeAmt());
				} else if ("2".equals(tmp.getDebitCreditFlag())){
					creditAmt = CurrencyUtils.sumDouble(creditAmt+"", tmp.getTradeAmt());
				}
				trdNo = tmp.getTrdNo();
			}
			double subAmt = CurrencyUtils.subDouble(debitAmt+"", creditAmt+"");
			if (0 != new BigDecimal(0).compareTo(new BigDecimal(subAmt))) {
				return "借贷不等";
			}
			
			//各科目净增加额不满足资产-负债-收入+费用=所有者权益
			double creditAmtSum = 0;
			for (MdtAdjActTrdModel tmp : list) {
				if (tmp.getMdtSubjectType().indexOf("1002|1204|6404|6405|6605") >= 0) {
					if ("1".equals(tmp.getDebitCreditFlag())) {
						creditAmtSum = CurrencyUtils.sumDouble(creditAmtSum+"", CurrencyUtils.mulDouble(1+"", tmp.getTradeAmt())+"");
					} else if ("2".equals(tmp.getDebitCreditFlag())) {
						creditAmtSum = CurrencyUtils.sumDouble(creditAmtSum+"", CurrencyUtils.mulDouble(-1+"", tmp.getTradeAmt())+"");
					}
				} else if (tmp.getMdtSubjectType().indexOf("2207|2210|2211|2221|6011|4001|4103|4104") >= 0 || 
						"2241".equals(tmp.getMdtSubjectType().substring(0, 4))) {
					if ("1".equals(tmp.getDebitCreditFlag())) {
						creditAmtSum = CurrencyUtils.sumDouble(creditAmtSum+"", CurrencyUtils.mulDouble(1+"", tmp.getTradeAmt())+"");
					} else if ("2".equals(tmp.getDebitCreditFlag())) {
						creditAmtSum = CurrencyUtils.sumDouble(creditAmtSum+"", CurrencyUtils.mulDouble(-1+"", tmp.getTradeAmt())+"");
					}
				}
				if (tmp.getMdtSubjectType().indexOf("1002|1204|2207|2210|2211|2221") < 0 &&
						!"2241".equals(tmp.getMdtSubjectType().substring(0, 4))) {
					exsitsCount = exsitsCount+1;
				}
			}
			if (0 != new BigDecimal(0).compareTo(new BigDecimal(creditAmtSum))) {
				return "各科目净增加额不满足资产-负债=所有者权益+收入-费用";
			}
			//当补帐双方的科目都是资产和负债时，所有者权益是不变的，只要任何一方有一个不是资产或负债，都认为所有者权益是变了的（实际也可能不变），都要取消日结。
			if (0 != exsitsCount) {
				isDayendFlag = "0";
			}
			
		}
		if ("2".equals(mdtAddActType) && 
				mdtAddOpType.indexOf("1|2|3|4|5|6|7") >= 0) {
			isDayendFlag = "0";
		}
		if ("0".equals(isDayendFlag)) {
			String opDateMax = mdtAdjActTrdDao.getMaxOpDate(trdNo);
			if (mdtDayendInfDao.isFinishDayend(actInfo.getActId(), opDateMax))  {
				return opDateMax+"已日结，请取消该日日结后再处理申请";
			}
		}
		return CommonConstant.RETMSG_SUCCESS;
	} 
}
