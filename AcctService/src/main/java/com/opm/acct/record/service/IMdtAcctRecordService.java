package com.opm.acct.record.service;

import java.util.List;

import com.opm.acct.mdt.model.MdtDetTrdModel;
import com.opm.common.business.param.RetResult;

public interface IMdtAcctRecordService {
	
	/**
	 * 受托户会计记账数据准备
	 * @param mdtDetTrd
	 * @return
	 */
	public RetResult doPrepare (List<MdtDetTrdModel> mdtDetTrd, String trdNo, String planId);
	/**
	 * 受托户会计记账生效
	 * @param planId
	 * @return
	 */
	public RetResult doTrade (String trdNo, String planId);
	
}
