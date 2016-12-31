package com.opm.acct.mdt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.common.CommonConstant;
import com.opm.acct.common.CommonUtils;
import com.opm.acct.mdt.dao.IMdtAcctdetTrdDao;
import com.opm.acct.mdt.mapper.MdtAcctdetInfMapper;
import com.opm.acct.mdt.mapper.MdtAcctdetTrdMapper;
import com.opm.acct.mdt.mapper.MdtDayendInfMapper;
import com.opm.acct.mdt.model.MdtAcctdetTrdModel;

import ch.qos.logback.classic.Logger;

@Repository("MdtAcctdetTrdDao")
public class MdtAcctdetTrdDao implements IMdtAcctdetTrdDao {
	
	 private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtAcctdetTrdDao.class);
	
	@Autowired
	private MdtAcctdetTrdMapper mdtAcctdetTrdMapper;
	
	@Autowired
	private MdtAcctdetInfMapper mdtAcctdetInfMapper;
	
	@Autowired
	private MdtDayendInfMapper mdtDayendInfMapper;
	
	

	@Override
	public String checkMdtDetImpTrade(String trdNo,String actId) {
		try {
			
			//����ֶ�У��,�Ƿ�Ϊ�յ�У��
			mdtAcctdetTrdMapper.mdtDetImpCheck1(trdNo);
			mdtAcctdetTrdMapper.mdtDetImpCheck2(trdNo);
			mdtAcctdetTrdMapper.mdtDetImpCheck3(trdNo);
			mdtAcctdetTrdMapper.mdtDetImpCheck4(trdNo);
			mdtAcctdetTrdMapper.mdtDetImpCheck5(trdNo);
			mdtAcctdetTrdMapper.mdtDetImpCheck6(trdNo);
			mdtAcctdetTrdMapper.mdtDetImpCheck7(trdNo);
			mdtAcctdetTrdMapper.mdtDetImpCheck8(trdNo);
			mdtAcctdetTrdMapper.mdtDetImpCheck9(trdNo);
			mdtAcctdetTrdMapper.mdtDetImpCheck10(trdNo);
			mdtAcctdetTrdMapper.mdtDetImpCheck11(trdNo,actId);
			mdtAcctdetTrdMapper.mdtDetImpCheck12(trdNo,actId);
			
			//������ϸ�ڲ��谴���ں�ʱ������ У��
			String retMsg = this.checkMdtAcctdetTrdSortByDateTime(trdNo);
			if (CommonConstant.RETMSG_ERROR.equals(retMsg)) {
				return retMsg;
			}
			/*
			 * ����ʷ��ϸ�ĵ�����Ҫ����У�飬����ǰ���һ����ϸ�Ľ���������������ϸ�����һ����ϸ�������ڣ������Ѿ��������ս�
	         *������ǰ���һ����ϸ�뵼����ϸ����ͬһ�·ݣ���ӵ�����ϸ�����³���1�գ���������ϸ�����һ����ϸ�������ڲ����Ѿ������ս�
			 */
			retMsg = this.checkDayendBefImp(trdNo, actId);
			if (CommonConstant.RETMSG_ERROR.equals(retMsg)) {
				return retMsg;
			}
			
			/*
			 * У������Ƿ���ȷ��
             * (1)�����ÿ����������ϸ����������������ϸ���⣩=�����������������ϸ�����+������������ϸ����������������������ϸ������֮��ĸ���������ϸ���˽��-���˽��
             * (2)�ϴε�������״ε���ʱΪ�ƻ���Ϣ�е����л���ʼ��+���ε���������������ϸ������֮ǰ��ϸ�����˽��-���˽��=���ε���������������ϸ����
      
			 */
			retMsg = this.checkAmtBefImp(trdNo, actId);
			if (CommonConstant.RETMSG_ERROR.equals(retMsg)) {
				return retMsg;
			}
			
			return CommonConstant.RETMSG_SUCCESS;
		}  catch (Exception e) {
			LOGGER.error("���л���������׼���׶������ֶ��Ƿ�Ϊ��У�鴦���쳣", e);
			return CommonConstant.RETMSG_ERROR;
		}
	}
	/**
	 * У�����л�������ϸ�ڲ��Ƿ�������ʱ������
	 * @param trdNo
	 * @return
	 */
	public String checkMdtAcctdetTrdSortByDateTime(String trdNo) {
		//LOGGER.info("MdtAcctdetTmpDao-->checkMdtAcctdetTmpSortByDateTime���л���ϸ�����ڲ��Ƿ�������ʱ�����򷽷����:appNo="+appNo);
		List<MdtAcctdetTrdModel> list = mdtAcctdetTrdMapper.qryMdtAcctdetTrdByTrdNoAndCheckFlag(trdNo, "0");
		if (null == list || 0 == list.size()) {
			return CommonConstant.RETMSG_SUCCESS;//CommonUtils.getRespList(CommonConstant.RETCODE_ERROR, "���л���ϸ��ʱ���������л� ������ϸ��¼appNo="+appNo+" ,checkFlag=0");
		}
		String opDate = "";
		String opTime = "";
		MdtAcctdetTrdModel tmp = null;
		List<MdtAcctdetTrdModel> updList = new ArrayList<MdtAcctdetTrdModel>();
		MdtAcctdetTrdModel updModel = null;
		String retMsg = CommonConstant.RETMSG_SUCCESS;//��ʶ�˴�У���Ƿ�ͨ��,�м����ֻҪ��һ���쳣 ��ΪУ�鲻ͨ���������쳣�����У��?
		for (int i=0; i<list.size(); i++) {
			try {
				tmp = list.get(i);
				if (0 == i) {
					//��һ����¼��������ֵ?????????���ݿ�����opDate opTime����Ϊ����?������Ϊ�գ���Ҫ��int i ��ѭ��
					opDate = tmp.getOpDate();
					opTime = tmp.getOpTime();
					continue;
				} 
				
				if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(opDate, tmp.getOpDate())) || 
						(CommonConstant.COMPARE_EQUAL.equals(CommonUtils.compareDate(opDate, tmp.getOpDate())) 
								&& StringUtils.isNotBlank(opTime) && StringUtils.isNotBlank(tmp.getOpTime()) && 
								CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareTime(opTime, tmp.getOpTime())))) {
					updModel = new MdtAcctdetTrdModel(tmp.getTrdNo(), tmp.getSeqId(), "������ϸ�ڲ��谴���ں�ʱ������");
					updList.add(updModel);
					//500����¼����һ�����ݿ�
					if (CommonConstant.BATCH_SIZE == updList.size()) {
						//????????������ô����
						mdtAcctdetTrdMapper.updCheckResultPerRecord(updList);
						updList.clear();
					}  
					
				}
				
				opDate = tmp.getOpDate();
				opTime = tmp.getOpTime();
				if (CommonConstant.COMPARE_EQUAL.equals(CommonUtils.compareDate(opDate, tmp.getOpDate()))) {
					opTime = StringUtils.isEmpty(opTime) ? tmp.getOpTime() : opTime;
				}
			} catch (Exception e) {
				//�����쳣����ִ��
				updList.clear();
				opDate = tmp.getOpDate();
				opTime = tmp.getOpTime();
				if (CommonConstant.COMPARE_EQUAL.equals(CommonUtils.compareDate(opDate, tmp.getOpDate()))) {
					opTime = StringUtils.isEmpty(opTime) ? tmp.getOpTime() : opTime;
				}
				LOGGER.error("���л���������׼���׶�����У���ڲ��Ƿ�������ʱ���������쳣��trdNo="+trdNo, e);
				retMsg = CommonConstant.RETMSG_ERROR;
			}
			
		}
		
		try {
			//????????������ô����
			if (null != updList && 0 != updList.size()) {
				mdtAcctdetTrdMapper.updCheckResultPerRecord(updList);
				updList.clear();
			}
			
		} catch (Exception e) {
			LOGGER.error("���л���������׼���׶�����У���ڲ��Ƿ�������ʱ���������쳣��trdNo="+trdNo, e);
			retMsg = CommonConstant.RETMSG_ERROR;
		}
		
		return retMsg;
	}
	/**
	 * ���л���ϸ�����ս����У��
	 * ����ʷ��ϸ�ĵ�����Ҫ����У�飬����ǰ���һ����ϸ�Ľ���������������ϸ�����һ����ϸ�������ڣ������Ѿ��������ս�
     * ������ǰ���һ����ϸ�뵼����ϸ����ͬһ�·ݣ���ӵ�����ϸ�����³���1�գ���������ϸ�����һ����ϸ�������ڲ����Ѿ������ս�
     * 
	 * @param trdNo
	 * @param actId
	 * @return
	 */
	public String checkDayendBefImp(String trdNo, String actId) {
		//LOGGER.info("MdtAcctdetTmpDao-->checkDayendBefImp���л���ϸ����ǰ�ս����У�鷽�����:appNo="+appNo+" ,planId="+planId);
		//��ȡ�ж��ս��������ʼ����
		String startDateBefImp = mdtAcctdetInfMapper.getOpDateBefImpLastDet(actId);
		String endDateBefImp = mdtAcctdetTrdMapper.getMaxOpDateByTrdNo(trdNo);
		String compareResult = CommonUtils.compareDate(startDateBefImp, endDateBefImp.substring(0, 8) + "01");
		String retMsg = CommonConstant.RETMSG_SUCCESS;
		if (CommonConstant.RETMSG_ERROR.equals(compareResult)) {
			LOGGER.error("MdtAcctdetTmpDao-->checkDayendBefImp�ж��ս����ʱ,��ʼ����="+startDateBefImp+" ,���������="+endDateBefImp+"�Ƚϳ���");
			return CommonConstant.RETMSG_ERROR;
		} else if (CommonConstant.COMPARE_LESS.equals(compareResult)) {
			startDateBefImp = endDateBefImp.substring(0, 8) + "01";
		} 
		
		String intervalDays = CommonUtils.getDaysBetween2Date(startDateBefImp, endDateBefImp);
		if (CommonConstant.RETMSG_ERROR.equals(intervalDays)) {
			LOGGER.error("MdtAcctdetTmpDao-->checkDayendBefImp��ȡ���ڼ����������,��ʼ����="+startDateBefImp+",��������="+endDateBefImp);
			return CommonConstant.RETMSG_ERROR;
		}
		int count = 0;
		//?????????�Ƿ��ж���������ֻҪ��һ���������ս�ģ��Ϳ����ˣ���Ϊ�洢���̸��µ�������appNo,�����е����appNo��checkFlag=0�Ķ�������
		for (int i=0; i<=Integer.parseInt(intervalDays); i++) {
			try {
				count = mdtDayendInfMapper.isFinishDayend(actId, CommonUtils.addDays(startDateBefImp, i));
				if (0 != count) {
					mdtAcctdetTrdMapper.updCheckResultByTrdNo(trdNo, "����ǰ���һ����ϸ�Ľ�������(������ǰ���һ����ϸ�뵼����ϸ����ͬһ�·ݣ���ӵ�����ϸ����1��) "
	                           +startDateBefImp + " ��������ϸ�����һ����ϸ��������  "+
	                           endDateBefImp + " ���սᣬ��ȡ������ս���������ύ����");
				}
			} catch (Exception e) {
				LOGGER.error("����ǰ���һ����ϸ�Ľ���������������ϸ�����һ����ϸ�������ڣ������Ѿ��������ս�  У���쳣",e);
				retMsg = CommonConstant.RETMSG_ERROR;
			}
			
		}
		return retMsg;
	}
	/**
	 * У������Ƿ���ȷ��
     *(1)�����ÿ����������ϸ����������������ϸ���⣩=�����������������ϸ�����+������������ϸ����������������������ϸ������֮��ĸ���������ϸ���˽��-���˽��
     *(2)�ϴε�������״ε���ʱΪ�ƻ���Ϣ�е����л���ʼ��+���ε���������������ϸ������֮ǰ��ϸ�����˽��-���˽��=���ε���������������ϸ���� 
	 * @param trdNo
	 * @param actId
	 * @return
	 */
	public String checkAmtBefImp(String trdNo, String actId) {
		try {
			mdtAcctdetTrdMapper.updateAmtImpCheck(trdNo, actId);
			mdtAcctdetTrdMapper.checkAmtBefImp1(trdNo);
			mdtAcctdetTrdMapper.checkAmtBefImp2(trdNo);
		} catch (Exception e) {
			LOGGER.info("���л���ϸ����ǰ���У���쳣",  e);
			return CommonConstant.RETMSG_ERROR;
		}
		
		return CommonConstant.RETMSG_SUCCESS;
	}
	@Override
	public List<MdtAcctdetTrdModel> qryMdtAcctdetTrdListByTrdNo(String trdNo) {
		return mdtAcctdetTrdMapper.qryMdtAcctdetTrdListByTrdNo(trdNo);
	}
	@Override
	public String getMdtDailyIntDrawEndDate(String trdNo) {
		return mdtAcctdetTrdMapper.getMdtDailyIntDrawEndDate(trdNo);
	}

	@Override
	public String getMinOpDate(String trdNo) {
		return mdtAcctdetTrdMapper.getMinOpDate(trdNo);
	}
	@Override
	public int insertMdtAcctdetTrd(MdtAcctdetTrdModel mdtAcctdetTrdModel) {
		return mdtAcctdetTrdMapper.insertMdtAcctdetTrd(mdtAcctdetTrdModel);
	}
	@Override
	public int updMdtAcctdetTrdForMod(String trdNo, String seqId) {
		return mdtAcctdetTrdMapper.updMdtAcctdetTrdForMod(trdNo, seqId);
	}
	@Override
	public MdtAcctdetTrdModel getMdtAcctdetTrdBySeqId(String seqId) {
		return mdtAcctdetTrdMapper.getMdtAcctdetTrdBySeqId(seqId);
	}
	@Override
	public int insertMdtAcctdetTrdList(String serialNo, String actId, String trdNo) {
		return mdtAcctdetTrdMapper.insertMdtAcctdetTrdList(serialNo, actId, trdNo);
	}
	@Override
	public int getCountsByCheckFlag(String trdNo, String checkFlag) {
		return mdtAcctdetTrdMapper.getCountsByCheckFlag(trdNo, checkFlag);
	}
}
