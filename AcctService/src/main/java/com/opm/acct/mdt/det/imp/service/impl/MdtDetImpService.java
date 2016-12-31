package com.opm.acct.mdt.det.imp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.acct.common.CommonConstant;
import com.opm.acct.common.CommonUtils;
import com.opm.acct.mdt.act.inf.service.IMdtAcctMdtActInfService;
import com.opm.acct.mdt.dao.IMdtAcctdetTrdDao;
import com.opm.acct.mdt.dao.IMdtActRelDao;
import com.opm.acct.mdt.dao.impl.MdtAcctdetHisDao;
import com.opm.acct.mdt.dao.impl.MdtAcctdetInfDao;
import com.opm.acct.mdt.dao.impl.MdtDetTrdDao;
import com.opm.acct.mdt.det.imp.service.IMdtDetImpService;
import com.opm.acct.mdt.model.MdtAcctdetInfModel;
import com.opm.acct.mdt.model.MdtAcctdetTrdModel;
import com.opm.acct.mdt.model.MdtActInfModel;
import com.opm.acct.mdt.model.MdtActRelModel;
import com.opm.acct.mdt.model.MdtDetTrdModel;
import com.opm.acct.record.service.IMdtAcctRecordService;
import com.opm.common.business.param.RetResult;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-wanghong01 on 2016/12/8.
 */

@Service("MdtDetImpService")
public class MdtDetImpService implements IMdtDetImpService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtDetImpService.class);

    @Autowired
    private IMdtAcctdetTrdDao mdtAcctdetTrdDao;
    
    @Autowired
    private MdtDetTrdDao mdtDetTrdDao;
    
    @Autowired
    private MdtAcctdetInfDao mdtAcctdetInfDao;
    
    @Autowired
    private MdtAcctdetHisDao mdtAcctdetHisDao;
    
    @Autowired
 	private IMdtAcctMdtActInfService mdtAcctMdtActInfService;
    
    @Autowired
 	private IMdtActRelDao mdtActRelDao;
    
    @Autowired
   	private IMdtAcctRecordService mdtAcctRecordService;
    
	@Override
	public RetResult doPrepare(String trdNo, String planId, String serialNo) {
		RetResult rr = new RetResult();
		Map<String, Object> contextObj = new HashMap<>();
		contextObj.put("trdNo", trdNo);
		contextObj.put("serialNo", serialNo);
		contextObj.put("planId", planId);
		rr.setContextObj(contextObj);
		rr.setReturnObj(contextObj);
		MdtActRelModel mdtActRel = (MdtActRelModel)mdtActRelDao.getMdtActRel(planId);
		if (null == mdtActRel) {
			LOGGER.error("���л���������׼���׶β�ѯ���л���ϵ ������");
			rr.setResult(false);
			rr.setReason("���л���������׼��ʧ��");
			return rr;
		}
		//��ѯdataservice�е�fileNo���� ��opm_mdt_acctdet_trd
		int count = 0;
		try {
			count = mdtAcctdetTrdDao.insertMdtAcctdetTrdList(serialNo, mdtActRel.getActId(), trdNo);		
			//���л������־����?????????
	        //String iPlanClient.getPlanBasicInfo(planId);
	        if (0 == count) {
	        	rr.setResult(true);
				rr.setReason("���л������ļ�������");
				return rr;
	        }
			//���л���ϸ����У��
			String ret = mdtAcctdetTrdDao.checkMdtDetImpTrade(trdNo, mdtActRel.getActId());
			if (CommonConstant.RETMSG_ERROR.equals(ret)) {
				rr.setResult(false);
				rr.setReason("���л������ļ�����У���쳣");
				return rr;
			}
			
			count = mdtAcctdetTrdDao.getCountsByCheckFlag(trdNo, "N");
			if (0 != count) {
				rr.setResult(false);
				rr.setReason("���л��ļ���������У�鲻ͨ��");
				return rr;
			}
		} catch (Exception e) {
			LOGGER.error("���л���������׼�������쳣", e);
			rr.setResult(false);
			rr.setReason("���л���������׼�������쳣");
			return rr;
		}
	
		rr.setResult(true);
		rr.setReason("���л��ļ�����ɹ�");
		return rr;
	}

	@Override
	public List<Object> doTrade(String trdNo, String planId) {
		mdtDetImpEffect(planId,trdNo);
		return null;
	}

	public List<Object> mdtDetImpEffect(String planId,String trdNo) {
		MdtActInfModel mdtActInfModel = (MdtActInfModel) mdtAcctMdtActInfService.getActInf(planId);
		String actId = mdtActInfModel.getActId();
		//3.����trdNo��ѯ���л���ϸ�����opm_mdt_acctdet_app�������л���Ƽ��˽��ױ�,mdtSubjectType, debitCreditFlag ��ͬ����������
		//----???????????????----
		List<MdtDetTrdModel> list = new ArrayList<MdtDetTrdModel>();
		list.add(new MdtDetTrdModel(trdNo, "1002", "1"));
		list.add(new MdtDetTrdModel(trdNo, "224101", "2"));
		mdtDetTrdDao.insertMdtDetTrdForImp(list,trdNo);
		//4.��ȡ���л���Ϣ�����еĽ�������,��ѯ���л���ϸ�����opm_mdt_acctdet_app
		//----????????????----
		String endDate = mdtAcctdetTrdDao.getMdtDailyIntDrawEndDate(trdNo);
		/**
		 * 5.��ȡ��ȡ���л���Ϣ��ʼ����,������ڵ������л���ʼ��������,�����ǵ�һ�ε������л�����ʷ��ϸ��
		 * Ϊ����ǰ���һ����ϸ�Ľ�������,����δ��������л���ʷ��ϸ����һ�ε������л���ϸʱ��opm_mdt_acctdet_inf�������ݣ�
		 * ��ʼʱ���趨Ϊ���ε���ʱ����Сֵ
		 */
		//��ȡ��Ч�����л�������Ϣ��
		int count = mdtAcctdetInfDao.getMdtAcctdetInfCount(mdtActInfModel.getActId(), "Y");
		//���л���Ϣ��ʼ����
		String startDate = "";
		if (0 > count) {
			startDate =mdtAcctdetInfDao.getBeginDateForImpDraw(actId);
		} else {
			//��ѯ���л���ϸ�����opm_mdt_acctdet_app ���ε�����С��������
			//---???????????????---
			startDate = mdtAcctdetTrdDao.getMinOpDate(trdNo);
		}
		//����Ϣ�������ڴ��ڼ�Ϣ��ʼ���������·ݵ����һ�죬��ʼ���ڵ���Ϊ�������������·ݵ�һ�죬ע��startDate ��endDateΪ���ǵ��������?????
		String lastestDay = CommonUtils.getLastDateOfMonth(startDate);
		String compareResult = CommonUtils.compareDate(endDate, lastestDay);
		if (CommonConstant.RETMSG_ERROR.equals(compareResult)) {
			return CommonUtils.getRespList(CommonConstant.RETMSG_ERROR, 
					"��ȡ���л���Ϣ����Ŀ�ʼ����-->��Ϣ�����������Ϣ��ʼ���������·����һ������ڱȽ� ����");
			 
		} 
		if (CommonConstant.COMPARE_GREAT.equals(compareResult)) {
			startDate = endDate.substring(0, 8) + "01";
		}
		//6.�������л��ʽ�������Ϣ��ȫ����ϸȷ��״̬���Ϊ"��ȷ��",��������Ƿ�Ҫ����?????????
		//����trdNo��ѯ���л���ϸ�����opm_mdt_acctdet_app---???????????----
		mdtAcctdetInfDao.insertMdtAcctdetInf(trdNo);
		//7����Ϣ
		List<Object> respTmp =  this.mdtDailyIntDraw(startDate, endDate, actId, trdNo);
		if (CommonConstant.RETMSG_ERROR.equals(respTmp.get(0))) {
			return respTmp;
		}
		//8.���л������Ƽ���
		//mdtAcctRecordService.doPrepare(list);
		mdtAcctRecordService.doTrade(trdNo,planId);
		
//		respTmp = this.mdtAcctRecord(planId);
//		if (CommonConstant.RETCODE_ERROR.equals(respTmp.get(0))) {
//			return respTmp;
//		}
		//9.���л��ʽ�������Ϣ��ʷ�����
		mdtAcctdetHisDao.insertMdtAcctDetHis(trdNo);
		
		return CommonUtils.getRespList(CommonConstant.RETMSG_SUCCESS, CommonConstant.RETMSG_SUCCESS);
	}
	
	/**
	 * ��Ϣ
	 * @param startDate
	 * @param endDate
	 * @param planId
	 * @param appNo
	 * @return
	 */
	public List<Object> mdtDailyIntDraw(String startDate, String endDate, String actId, String trdNo) {
		LOGGER.info("MdtAcctDetInfDao-->mdtDailyIntDraw�������:startDate="+startDate+", endDate="+endDate+", actId="+actId+", trdNo="+trdNo);
		//���㿪ʼ��������������������
		String interval = CommonUtils.getDaysBetween2Date(startDate, endDate);
		if (CommonConstant.RETMSG_ERROR.equals(interval)) {
			return CommonUtils.getRespList(CommonConstant.RETMSG_ERROR, 
					"MdtAcctDetInfDao-->mdtDailyIntDraw�� ���㿪ʼ������������������������");
		}
		//�ع�����??????????????/
		for (int i=0; i<=Integer.valueOf(interval); i++) {
			
			mdtDetTrdDao.insertDrawForImp(trdNo, CommonUtils.addDays(startDate, i), actId);
		}
		return CommonUtils.getRespList(CommonConstant.RETMSG_SUCCESS, CommonConstant.RETMSG_SUCCESS);
	}

	@Override
	public List<MdtAcctdetTrdModel> qryMdtAcctdetTrdList(String trdNo) {
		
		return mdtAcctdetTrdDao.qryMdtAcctdetTrdListByTrdNo(trdNo);
	}

	@Override
	public List<Map<String,String>> getMdtAcctLastDet(String planId) {
		MdtActRelModel mdtActRel = mdtActRelDao.getMdtActRel(planId);
		List<Map<String,String>> actInf = mdtAcctdetInfDao.getMdtAcctLastDet(mdtActRel.getActId());
		return actInf;
	}
	
	@Override
	public List<MdtAcctdetInfModel> qryMdtAcctdetInfList(String planId, String opDate, String amt, String oppAcctName,
			String direct, int beginNum, int fetchNum) {
		MdtActRelModel mdtActRel = mdtActRelDao.getMdtActRel(planId);
		return mdtAcctdetInfDao.qryMdtAcctdetInfList(mdtActRel.getActId(), opDate, amt, oppAcctName, direct, beginNum, fetchNum);
	}

}
