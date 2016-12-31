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
			LOGGER.error("受托户导入数据准备阶段查询受托户关系 无数据");
			rr.setResult(false);
			rr.setReason("受托户导入数据准备失败");
			return rr;
		}
		//查询dataservice中的fileNo数据 入opm_mdt_acctdet_trd
		int count = 0;
		try {
			count = mdtAcctdetTrdDao.insertMdtAcctdetTrdList(serialNo, mdtActRel.getActId(), trdNo);		
			//受托户核算标志交易?????????
	        //String iPlanClient.getPlanBasicInfo(planId);
	        if (0 == count) {
	        	rr.setResult(true);
				rr.setReason("受托户导入文件无数据");
				return rr;
	        }
			//受托户明细导入校验
			String ret = mdtAcctdetTrdDao.checkMdtDetImpTrade(trdNo, mdtActRel.getActId());
			if (CommonConstant.RETMSG_ERROR.equals(ret)) {
				rr.setResult(false);
				rr.setReason("受托户导入文件数据校验异常");
				return rr;
			}
			
			count = mdtAcctdetTrdDao.getCountsByCheckFlag(trdNo, "N");
			if (0 != count) {
				rr.setResult(false);
				rr.setReason("受托户文件导入数据校验不通过");
				return rr;
			}
		} catch (Exception e) {
			LOGGER.error("受托户导入数据准备处理异常", e);
			rr.setResult(false);
			rr.setReason("受托户导入数据准备处理异常");
			return rr;
		}
	
		rr.setResult(true);
		rr.setReason("受托户文件导入成功");
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
		//3.根据trdNo查询受托户明细申请表opm_mdt_acctdet_app插入受托户会计记账交易表,mdtSubjectType, debitCreditFlag 不同，插入两次
		//----???????????????----
		List<MdtDetTrdModel> list = new ArrayList<MdtDetTrdModel>();
		list.add(new MdtDetTrdModel(trdNo, "1002", "1"));
		list.add(new MdtDetTrdModel(trdNo, "224101", "2"));
		mdtDetTrdDao.insertMdtDetTrdForImp(list,trdNo);
		//4.获取受托户计息区间中的结束日期,查询受托户明细申请表opm_mdt_acctdet_app
		//----????????????----
		String endDate = mdtAcctdetTrdDao.getMdtDailyIntDrawEndDate(trdNo);
		/**
		 * 5.获取获取受托户计息起始日期,必须大于等于受托户起始核算日期,若不是第一次导入受托户非历史明细，
		 * 为导入前最后一条明细的交易日期,若从未导入过受托户历史明细，第一次导入受托户明细时，opm_mdt_acctdet_inf中无数据，
		 * 起始时间设定为本次导入时间最小值
		 */
		//获取有效的受托户账务信息数
		int count = mdtAcctdetInfDao.getMdtAcctdetInfCount(mdtActInfModel.getActId(), "Y");
		//受托户计息起始日期
		String startDate = "";
		if (0 > count) {
			startDate =mdtAcctdetInfDao.getBeginDateForImpDraw(actId);
		} else {
			//查询受托户明细申请表opm_mdt_acctdet_app 本次导入最小操作日期
			//---???????????????---
			startDate = mdtAcctdetTrdDao.getMinOpDate(trdNo);
		}
		//若计息结束日期大于计息起始日期所在月份的最后一天，则开始日期调整为结束日期所在月份第一天，注意startDate 与endDate为空是的情况处理?????
		String lastestDay = CommonUtils.getLastDateOfMonth(startDate);
		String compareResult = CommonUtils.compareDate(endDate, lastestDay);
		if (CommonConstant.RETMSG_ERROR.equals(compareResult)) {
			return CommonUtils.getRespList(CommonConstant.RETMSG_ERROR, 
					"获取受托户计息区间的开始日期-->计息结束日期与计息开始日期所在月份最后一天的日期比较 出错");
			 
		} 
		if (CommonConstant.COMPARE_GREAT.equals(compareResult)) {
			startDate = endDate.substring(0, 8) + "01";
		}
		//6.插入受托户资金账务信息表，全部明细确认状态标记为"待确认",插入出错是否要回退?????????
		//根据trdNo查询受托户明细申请表opm_mdt_acctdet_app---???????????----
		mdtAcctdetInfDao.insertMdtAcctdetInf(trdNo);
		//7。计息
		List<Object> respTmp =  this.mdtDailyIntDraw(startDate, endDate, actId, trdNo);
		if (CommonConstant.RETMSG_ERROR.equals(respTmp.get(0))) {
			return respTmp;
		}
		//8.受托户核算会计记账
		//mdtAcctRecordService.doPrepare(list);
		mdtAcctRecordService.doTrade(trdNo,planId);
		
//		respTmp = this.mdtAcctRecord(planId);
//		if (CommonConstant.RETCODE_ERROR.equals(respTmp.get(0))) {
//			return respTmp;
//		}
		//9.受托户资金账务信息历史表更新
		mdtAcctdetHisDao.insertMdtAcctDetHis(trdNo);
		
		return CommonUtils.getRespList(CommonConstant.RETMSG_SUCCESS, CommonConstant.RETMSG_SUCCESS);
	}
	
	/**
	 * 计息
	 * @param startDate
	 * @param endDate
	 * @param planId
	 * @param appNo
	 * @return
	 */
	public List<Object> mdtDailyIntDraw(String startDate, String endDate, String actId, String trdNo) {
		LOGGER.info("MdtAcctDetInfDao-->mdtDailyIntDraw方法入参:startDate="+startDate+", endDate="+endDate+", actId="+actId+", trdNo="+trdNo);
		//计算开始日期与结束日期相差天数
		String interval = CommonUtils.getDaysBetween2Date(startDate, endDate);
		if (CommonConstant.RETMSG_ERROR.equals(interval)) {
			return CommonUtils.getRespList(CommonConstant.RETMSG_ERROR, 
					"MdtAcctDetInfDao-->mdtDailyIntDraw中 计算开始日期与结束日期相差天数出错");
		}
		//回滚机制??????????????/
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
