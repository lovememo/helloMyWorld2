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
			
			//相关字段校验,是否为空等校验
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
			
			//导入明细内部需按日期和时间排序 校验
			String retMsg = this.checkMdtAcctdetTrdSortByDateTime(trdNo);
			if (CommonConstant.RETMSG_ERROR.equals(retMsg)) {
				return retMsg;
			}
			/*
			 * 非历史明细的导入需要增加校验，导入前最后一条明细的交易日期至导入明细的最后一条明细交易日期，不能已经进行了日结
	         *若导入前最后一条明细与导入明细不在同一月份，则从导入明细当月月初（1日）至导入明细的最后一条明细交易日期不能已经进行日结
			 */
			retMsg = this.checkDayendBefImp(trdNo, actId);
			if (CommonConstant.RETMSG_ERROR.equals(retMsg)) {
				return retMsg;
			}
			
			/*
			 * 校验余额是否正确。
             * (1)导入的每条有余额的明细的余额（首条有余额的明细除外）=导入的上条有余额的明细的余额+上条有余额的明细（不含）至本条有余额的明细（含）之间的各条导入明细入账金额-出账金额
             * (2)上次导入后余额（首次导入时为计划信息中的受托户初始余额）+本次导入首条有余额的明细（含）之前明细的入账金额-出账金额=本次导入首条有余额的明细的余额；
      
			 */
			retMsg = this.checkAmtBefImp(trdNo, actId);
			if (CommonConstant.RETMSG_ERROR.equals(retMsg)) {
				return retMsg;
			}
			
			return CommonConstant.RETMSG_SUCCESS;
		}  catch (Exception e) {
			LOGGER.error("受托户导入数据准备阶段数据字段是否为空校验处理异常", e);
			return CommonConstant.RETMSG_ERROR;
		}
	}
	/**
	 * 校验受托户导入明细内部是否按照日期时间排序
	 * @param trdNo
	 * @return
	 */
	public String checkMdtAcctdetTrdSortByDateTime(String trdNo) {
		//LOGGER.info("MdtAcctdetTmpDao-->checkMdtAcctdetTmpSortByDateTime受托户明细导入内部是否按照日期时间排序方法入参:appNo="+appNo);
		List<MdtAcctdetTrdModel> list = mdtAcctdetTrdMapper.qryMdtAcctdetTrdByTrdNoAndCheckFlag(trdNo, "0");
		if (null == list || 0 == list.size()) {
			return CommonConstant.RETMSG_SUCCESS;//CommonUtils.getRespList(CommonConstant.RETCODE_ERROR, "受托户明细临时表中无受托户 导入明细记录appNo="+appNo+" ,checkFlag=0");
		}
		String opDate = "";
		String opTime = "";
		MdtAcctdetTrdModel tmp = null;
		List<MdtAcctdetTrdModel> updList = new ArrayList<MdtAcctdetTrdModel>();
		MdtAcctdetTrdModel updModel = null;
		String retMsg = CommonConstant.RETMSG_SUCCESS;//标识此次校验是否通过,中间过程只要有一次异常 认为校验不通过，出现异常会继续校验?
		for (int i=0; i<list.size(); i++) {
			try {
				tmp = list.get(i);
				if (0 == i) {
					//第一条记录给变量赋值?????????数据库表里的opDate opTime可能为空吗?若可以为空，则要用int i 来循环
					opDate = tmp.getOpDate();
					opTime = tmp.getOpTime();
					continue;
				} 
				
				if (CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareDate(opDate, tmp.getOpDate())) || 
						(CommonConstant.COMPARE_EQUAL.equals(CommonUtils.compareDate(opDate, tmp.getOpDate())) 
								&& StringUtils.isNotBlank(opTime) && StringUtils.isNotBlank(tmp.getOpTime()) && 
								CommonConstant.COMPARE_GREAT.equals(CommonUtils.compareTime(opTime, tmp.getOpTime())))) {
					updModel = new MdtAcctdetTrdModel(tmp.getTrdNo(), tmp.getSeqId(), "导入明细内部需按日期和时间排序");
					updList.add(updModel);
					//500条记录更新一次数据库
					if (CommonConstant.BATCH_SIZE == updList.size()) {
						//????????出错怎么处理
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
				//出现异常继续执行
				updList.clear();
				opDate = tmp.getOpDate();
				opTime = tmp.getOpTime();
				if (CommonConstant.COMPARE_EQUAL.equals(CommonUtils.compareDate(opDate, tmp.getOpDate()))) {
					opTime = StringUtils.isEmpty(opTime) ? tmp.getOpTime() : opTime;
				}
				LOGGER.error("受托户导入数据准备阶段数据校验内部是否按照日期时间排序处理异常，trdNo="+trdNo, e);
				retMsg = CommonConstant.RETMSG_ERROR;
			}
			
		}
		
		try {
			//????????出错怎么处理
			if (null != updList && 0 != updList.size()) {
				mdtAcctdetTrdMapper.updCheckResultPerRecord(updList);
				updList.clear();
			}
			
		} catch (Exception e) {
			LOGGER.error("受托户导入数据准备阶段数据校验内部是否按照日期时间排序处理异常，trdNo="+trdNo, e);
			retMsg = CommonConstant.RETMSG_ERROR;
		}
		
		return retMsg;
	}
	/**
	 * 受托户明细导入日结情况校验
	 * 非历史明细的导入需要增加校验，导入前最后一条明细的交易日期至导入明细的最后一条明细交易日期，不能已经进行了日结
     * 若导入前最后一条明细与导入明细不在同一月份，则从导入明细当月月初（1日）至导入明细的最后一条明细交易日期不能已经进行日结
     * 
	 * @param trdNo
	 * @param actId
	 * @return
	 */
	public String checkDayendBefImp(String trdNo, String actId) {
		//LOGGER.info("MdtAcctdetTmpDao-->checkDayendBefImp受托户明细导入前日结情况校验方法入参:appNo="+appNo+" ,planId="+planId);
		//获取判断日结情况的起始日期
		String startDateBefImp = mdtAcctdetInfMapper.getOpDateBefImpLastDet(actId);
		String endDateBefImp = mdtAcctdetTrdMapper.getMaxOpDateByTrdNo(trdNo);
		String compareResult = CommonUtils.compareDate(startDateBefImp, endDateBefImp.substring(0, 8) + "01");
		String retMsg = CommonConstant.RETMSG_SUCCESS;
		if (CommonConstant.RETMSG_ERROR.equals(compareResult)) {
			LOGGER.error("MdtAcctdetTmpDao-->checkDayendBefImp判断日结情况时,开始日期="+startDateBefImp+" ,与结束日期="+endDateBefImp+"比较出错");
			return CommonConstant.RETMSG_ERROR;
		} else if (CommonConstant.COMPARE_LESS.equals(compareResult)) {
			startDateBefImp = endDateBefImp.substring(0, 8) + "01";
		} 
		
		String intervalDays = CommonUtils.getDaysBetween2Date(startDateBefImp, endDateBefImp);
		if (CommonConstant.RETMSG_ERROR.equals(intervalDays)) {
			LOGGER.error("MdtAcctdetTmpDao-->checkDayendBefImp获取日期间隔天数出错,开始日期="+startDateBefImp+",结束日期="+endDateBefImp);
			return CommonConstant.RETMSG_ERROR;
		}
		int count = 0;
		//?????????是否判断日期区间只要有一个日期是日结的，就可以了，因为存储过程更新的条件是appNo,将所有的这个appNo，checkFlag=0的都更新了
		for (int i=0; i<=Integer.parseInt(intervalDays); i++) {
			try {
				count = mdtDayendInfMapper.isFinishDayend(actId, CommonUtils.addDays(startDateBefImp, i));
				if (0 != count) {
					mdtAcctdetTrdMapper.updCheckResultByTrdNo(trdNo, "导入前最后一条明细的交易日期(若导入前最后一条明细与导入明细不在同一月份，则从导入明细当月1日) "
	                           +startDateBefImp + " 至导入明细的最后一条明细交易日期  "+
	                           endDateBefImp + " 已日结，请取消相关日结后再重新提交申请");
				}
			} catch (Exception e) {
				LOGGER.error("导入前最后一条明细的交易日期至导入明细的最后一条明细交易日期，不能已经进行了日结  校验异常",e);
				retMsg = CommonConstant.RETMSG_ERROR;
			}
			
		}
		return retMsg;
	}
	/**
	 * 校验余额是否正确。
     *(1)导入的每条有余额的明细的余额（首条有余额的明细除外）=导入的上条有余额的明细的余额+上条有余额的明细（不含）至本条有余额的明细（含）之间的各条导入明细入账金额-出账金额
     *(2)上次导入后余额（首次导入时为计划信息中的受托户初始余额）+本次导入首条有余额的明细（含）之前明细的入账金额-出账金额=本次导入首条有余额的明细的余额； 
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
			LOGGER.info("受托户明细导入前金额校验异常",  e);
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
