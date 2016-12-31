package com.opm.acct.mdt.det.imp.service;

import java.util.List;
import java.util.Map;

import com.opm.acct.mdt.model.MdtAcctdetInfModel;
import com.opm.acct.mdt.model.MdtAcctdetTrdModel;
import com.opm.common.business.param.RetResult;

public interface IMdtDetImpService {
	
	/**
	 * 受托户明细导入数据准备
	 * @param trdNo
	 * @param planId
	 * @param fileNo
	 * @return
	 */
	public RetResult doPrepare (String trdNo, String planId, String serialNo);
	/**
	 * 受托户明细导入生效
	 * @param trdNo
	 * @param planId
	 * @return
	 */
	public List<Object> doTrade (String trdNo, String planId);
	
	/**
	 * 获取受托户导入明细
	 * @param trdNo
	 * @return
	 */
	public List<MdtAcctdetTrdModel> qryMdtAcctdetTrdList (String trdNo);
	
	/**
	 * 查询 导入前最后一条明细信息
	 * @return
	 */
	public List<Map<String,String>> getMdtAcctLastDet (String planId);
	
	/**
	 * 受托户修改 受托户明细查询
	 * @param actId
	 * @param opDate
	 * @param amt
	 * @param oppAcctName
	 * @param direct
	 * @param beginNum
	 * @param fetchNum
	 * @return
	 */
	List<MdtAcctdetInfModel> qryMdtAcctdetInfList(String planId, String opDate, String amt, String oppAcctName, String direct,
			int beginNum,  int fetchNum);
	
}
