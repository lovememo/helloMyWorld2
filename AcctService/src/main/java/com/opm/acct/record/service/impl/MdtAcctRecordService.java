package com.opm.acct.record.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opm.acct.common.CommonConstant;
import com.opm.acct.common.remote.IPlanClient;
import com.opm.acct.mdt.dao.IMdtActDao;
import com.opm.acct.mdt.dao.IMdtActRelDao;
import com.opm.acct.mdt.dao.IMdtDetDao;
import com.opm.acct.mdt.dao.IMdtDetTrdDao;
import com.opm.acct.mdt.model.MdtActRelModel;
import com.opm.acct.mdt.model.MdtDetTrdModel;
import com.opm.acct.mdt.model.PlanBasicInfoModel;
import com.opm.acct.record.service.IMdtAcctRecordService;
import com.opm.common.business.param.RetResult;

@Repository("MdtAcctRecordService")
public class MdtAcctRecordService implements IMdtAcctRecordService {
	
    @Autowired
   	private IMdtActRelDao mdtActRelDao;
	    
	@Autowired
	private IMdtDetTrdDao mdtDetTrdDao;
	
	@Autowired
	private IMdtDetDao mdtDetDao;
	
	@Autowired
	private IMdtActDao mdtActDao;
	
	 @Autowired
	 private IPlanClient iPlanClient;
	    
	public RetResult doPrepare(List<MdtDetTrdModel> list, String trdNo, String planId) {
		RetResult rr = new RetResult();
		Map<String, Object> contextObj = new HashMap<>();
		contextObj.put("trdNo", trdNo);
		rr.setContextObj(contextObj);
		rr.setReturnObj(contextObj);
		
		//入受托户会计记账交易表
    	String retStr = mdtDetTrdDao.insertMdtDetTrdForAcctRecord(list, trdNo);
    	if (CommonConstant.RETMSG_ERROR.equals(retStr)) {
    		return new RetResult(false,"受托户记账数据入库失败",null);
    	}
    	
    	//校验
		RetResult checkResult =  checkBeforeMdtAcctRecord (trdNo, planId);
		rr.setResult(checkResult.getResult());
		rr.setReason(checkResult.getReason());
		return rr;
	}

	/**
	 * 受托户记账前校验
	 * @return
	 */
	public RetResult checkBeforeMdtAcctRecord (String trdNo, String planId) {
    	//获取受托户核算标志 actFlag;
//		PlanBasicInfoModel planModel = iPlanClient.getPlanInfByPlanId("planId");
//		if (null == planModel) {
//			return new RetResult(false, "无计划信息",null);
//		}
//    	String actFlag = planModel.getAccountingFlag();
//		//受托户核算标志校验
//		if (CommonConstant.FLAG_N.equals(actFlag)) {
//			return new RetResult(false, "计划编码:planId="+planId+",计划规则上不允许受托户核算",null);
//		}
		//3.借贷校验 (1) 借贷明细总额校验
		String checkResult ;//= mdtDetTrdDao.debitCreditAmtCheck(trdNo);
//		if (CommonConstant.RETMSG_ERROR.equals(checkResult)) {
//			return new RetResult(false, "计划编码:planId="+planId+",借贷明细总额不相等",null);
//		}
		//3.借贷校验 (2)是否满足记账会计恒等式
		checkResult = mdtDetTrdDao.debitTradeAmtCheck(trdNo);
		if (CommonConstant.RETMSG_ERROR.equals(checkResult)) {
			return new RetResult(false, "计划编码:planId="+planId+",不满足记账会计恒等式",null);
		}
		return new RetResult(true, "受托户记账校验通过",null);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public RetResult doTrade(String trdNo, String planId) {
		//记账前再次校验 判断返回????????????
//		RetResult checkResult =   checkBeforeMdtAcctRecord (trdNo, planId);
//		if (!checkResult.getResult()) {
//			return new RetResult(checkResult.getResult(), checkResult.getReason(),null);
//		}
		//获取受托户关系信息
    	MdtActRelModel mdtActRelModel =  (MdtActRelModel) mdtActRelDao.getMdtActRel(planId);
    	if (null == mdtActRelModel) {
    		return new RetResult(false, "计划编码:planId="+planId+",无受托户信息",null);
    	}
		//入受托户会计明细账表???????回滚
		String retStr = mdtDetDao.insertMdtDet(trdNo);
		if (CommonConstant.RETMSG_ERROR.equals(retStr)) {
			return new RetResult(false, "受托户会计明细账记账失败",null);
		}
		//入受托户会计余额账表
		retStr = mdtActDao.insertMdtAct(mdtActRelModel.getActId());
		if (CommonConstant.RETMSG_ERROR.equals(retStr)) {
			return new RetResult(false, "受托户会计余额账记账失败",null);
		}
		return new RetResult(true, "受托户记账成功",null);
	}
}
