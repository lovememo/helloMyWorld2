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
		//���뽻�ױ�???????? ����ɹ��򲻳ɹ�
		String trdNo = "";
		List<MdtAdjActTrdModel> list = new ArrayList<MdtAdjActTrdModel>();
		for (int i=0; i<mdtSubjectTypeList.size(); i++) {
			list.add(new MdtAdjActTrdModel (trdNo, mdtAddActType, opDate, mdtAddOpType,
					reason, mdtSubjectTypeList.get(i), debitCreditFlagList.get(i), tradeAmtList.get(i)));
		}
		//???????????��ȡ����Ϊ��ʱ��ע���ָ��
		MdtActInfModel actInfo = mdtAcctMdtActInfService.getActInf(planId);
		//��������У��
		String retMsg = checkMdtAddAct (actInfo, opDate, mdtAddOpType,mdtAddActType,list);
		if (!CommonConstant.RETMSG_SUCCESS.equals(retMsg)) {
			return null;//retMsg;
		}
		mdtAdjActTrdDao.insertMdtAdjActTrd(list);
//		//У��
//		if (StringUtils.isEmpty(mdtAddOpType) || mdtAddOpType.indexOf("1|2|3|4|5|6|7") < 0) {
//			String debitAmt = mdtAdjActTrdDao.getDebitOrCreditAmt(trdNo, "1");
//			String creditAmt = mdtAdjActTrdDao.getDebitOrCreditAmt(trdNo, "2");
//			double subAmt = CurrencyUtils.subDouble(debitAmt, creditAmt);
//			if (0 != new BigDecimal(0).compareTo(new BigDecimal(subAmt))) {
//				return null; //"�������";
//			}
//			//����Ŀ�����Ӷ�����ʲ�-��ծ-����+����=������Ȩ��
//			String creditAmtSum = mdtAdjActTrdDao.getCreditAmt(trdNo);
//			if (0 != new BigDecimal(0).compareTo(new BigDecimal(creditAmtSum))) {
//				return null; //"����Ŀ�����Ӷ�����ʲ�-��ծ=������Ȩ��+����-����";
//			}
//			//������˫���Ŀ�Ŀ�����ʲ��͸�ծʱ��������Ȩ���ǲ���ģ�ֻҪ�κ�һ����һ�������ʲ���ծ������Ϊ������Ȩ���Ǳ��˵ģ�ʵ��Ҳ���ܲ��䣩����Ҫȡ���սᡣ
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
//				return null;//opDateMax+"���սᣬ��ȡ�������ս���ٴ�������";
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
			//��ǡ�����Ӧ����-��Ͷ��δȷ�ϡ������ǡ�����Ӧ����-��Ͷ����ȷ�ϡ�
			list.add(new MdtDetTrdModel(opDateMax, "224101", "1", tradeAmtMax, trdNo,"ת���տ�Ǽ�"));
			list.add(new MdtDetTrdModel(opDateMax, "224102", "2", tradeAmtMax, trdNo,"ת���տ�Ǽ�"));
			//��ǡ�����Ӧ����-��Ͷ����ȷ�ϡ������ǡ�ʵ�ջ���
			list.add(new MdtDetTrdModel(opDateMax, "224102", "1", tradeAmtMax, trdNo,"ת�뵽��ƥ��"));
			list.add(new MdtDetTrdModel(opDateMax, "4001", "2", tradeAmtMax, trdNo,"ת�뵽��ƥ��"));
		} else
			//��Ϣ
		if ("2".equals(mdtAddActTypeMax) && "2".equals(mdtAddOpTypeMax)) {
			//��ǡ����д������Ϊ¼��Ľ������ǡ�Ӧ����Ϣ�������Ϊ¼������ǰһ��Ӧ����Ϣ���������ǡ������Ϣ���롱�����Ϊ���д��-Ӧ����Ϣ��
			list.add(new MdtDetTrdModel(opDateMax, "1002", "1", tradeAmtMax, trdNo,"��Ϣ"));
			String amt = mdtActMoRptDao.getAmtByDate(actInfo, "1204", CommonUtils.addDays(opDateMax, -1));
			list.add(new MdtDetTrdModel(opDateMax, "1204", "2", amt, trdNo,"��Ϣ"));
			amt = mdtActMoRptDao.getAmtByDate(actInfo, "1204", CommonUtils.addDays(opDateMax, -1));
			amt = CurrencyUtils.subDouble(tradeAmtMax, amt)+"";
			list.add(new MdtDetTrdModel(opDateMax, "6011", "2", amt, trdNo,"��Ϣ"));
		} else 
		if ("2".equals(mdtAddActTypeMax) && "3".equals(mdtAddOpTypeMax)) {
			//��ǡ��������á������ǡ����д�
			list.add(new MdtDetTrdModel(opDateMax, "6605", "1", tradeAmtMax, trdNo,"��������"));
			list.add(new MdtDetTrdModel(opDateMax, "1002", "2", tradeAmtMax, trdNo,"��������"));
		} else 
		if ("2".equals(mdtAddActTypeMax) && "4".equals(mdtAddOpTypeMax)) {
			//��ǡ�Ӧ��˰�𡱡����ǡ����д�
			list.add(new MdtDetTrdModel(opDateMax, "2221", "1", tradeAmtMax, trdNo,"��˰֧��"));
			list.add(new MdtDetTrdModel(opDateMax, "1002", "2", tradeAmtMax, trdNo,"��˰֧��"));
		} else 
			//����ǰ�˿�
		if ("2".equals(mdtAddActTypeMax) && "5".equals(mdtAddOpTypeMax)) {
			//��ǡ�����Ӧ����-��Ͷ��δȷ�ϡ������ǡ����д�
			list.add(new MdtDetTrdModel(opDateMax, "224101", "1", tradeAmtMax, trdNo,"�˿�"));
			list.add(new MdtDetTrdModel(opDateMax, "1002", "2", tradeAmtMax, trdNo,"�˿�"));
		} else 
			//���˺��˿�
		if ("2".equals(mdtAddActTypeMax) && "6".equals(mdtAddOpTypeMax)) {
			//��ǡ�ʵ�ջ��𡱡����ǡ����д�
			list.add(new MdtDetTrdModel(opDateMax, "4001", "1", tradeAmtMax, trdNo,"�˿�"));
			list.add(new MdtDetTrdModel(opDateMax, "1002", "2", tradeAmtMax, trdNo,"�˿�"));
		} else 
		if ("2".equals(mdtAddActTypeMax) && "7".equals(mdtAddOpTypeMax)) {
			//��ǡ�δ�������󡱣����ǡ����д�
			list.add(new MdtDetTrdModel(opDateMax, "4104", "1", tradeAmtMax, trdNo,"���󻮲�"));
			list.add(new MdtDetTrdModel(opDateMax, "1002", "2", tradeAmtMax, trdNo,"���󻮲�"));
		} else 
		if ("1".equals(mdtAddActTypeMax) || "99".equals(mdtAddOpTypeMax)) {
			List<MdtAdjActTrdModel> listTmp = mdtAdjActTrdDao.getMdtAdjActTrd(trdNo);
			if (null != listTmp && 0 != listTmp.size()) {
				String memo = "����";
				if ("2".equals(mdtAddActTypeMax)) {
					memo = "����ҵ��";
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
	 * �ս�����У��
	 * @param actInfo
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public String checkMdtAddAct (MdtActInfModel actInfo,String opDate, String mdtAddOpType, String mdtAddActType,List<MdtAdjActTrdModel> list) {
		if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(actInfo.getActDate(), opDate))) {
			return "���л����ʵ����ڲ����������л�������ʼ����";
		}
		if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(opDate, CommonUtils.getCurrentDate()))) {
			return "���л����ʵ����ڲ��ܳ���ϵͳʱ��";
		}
		if (mdtDayendInfDao.isFinishMonend(actInfo.getActId(), opDate)) {
			return "���˵������һ�����սᣬ��ȡ�������ս�����ύ����";
		}
		if (mdtAddOpType.equals("2")) {
			List<MdtAcctdetInfModel> listTmp = mdtAcctdetInfDao.getInfBetween2Date(actInfo.getActId(), 
					opDate, CommonUtils.getLastDateOfMonth(opDate));
			if (null == listTmp || 0 == listTmp.size()) {
				return "��¼���ڵ������л���ϸδ���룬�޷�ȡ��ǰһ��Ӧ����Ϣ���";
			}
		}
		String isDayendFlag = "1"; //�Ƿ���Ҫ�ж��ս� 0 �� 1 ��
		int exsitsCount = 0;;//���ݴ��ֶθ�ֵisDayendFlag
		String trdNo = "";
		//����Ƿ����
		if (StringUtils.isEmpty(mdtAddOpType) || mdtAddOpType.indexOf("1|2|3|4|5|6|7") < 0) {
			//У�����Ƿ����
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
				return "�������";
			}
			
			//����Ŀ�����Ӷ�����ʲ�-��ծ-����+����=������Ȩ��
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
				return "����Ŀ�����Ӷ�����ʲ�-��ծ=������Ȩ��+����-����";
			}
			//������˫���Ŀ�Ŀ�����ʲ��͸�ծʱ��������Ȩ���ǲ���ģ�ֻҪ�κ�һ����һ�������ʲ���ծ������Ϊ������Ȩ���Ǳ��˵ģ�ʵ��Ҳ���ܲ��䣩����Ҫȡ���սᡣ
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
				return opDateMax+"���սᣬ��ȡ�������ս���ٴ�������";
			}
		}
		return CommonConstant.RETMSG_SUCCESS;
	} 
}
