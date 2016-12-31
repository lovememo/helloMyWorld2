package com.opm.acct.mdt.det.mod.service;

import java.util.List;

public interface IMdtDetModService {
	
	/**
	 * 受托户明细修改数据准备
	 * @param planId
	 * @param seqId
	 * @param dealType
	 * @param memo
	 * @return
	 */
	public List<Object> doPrepare (String planId, String seqId, String dealType,String memo);
	/**
	 * 受托户明细导入生效
	 * @param trdNo
	 * @param planId
	 * @return
	 */
	public List<Object> doTrade (String trdNo, String planId);
	
}
