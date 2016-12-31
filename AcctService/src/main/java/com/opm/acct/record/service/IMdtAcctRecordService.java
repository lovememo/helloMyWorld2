package com.opm.acct.record.service;

import java.util.List;

import com.opm.acct.mdt.model.MdtDetTrdModel;
import com.opm.common.business.param.RetResult;

public interface IMdtAcctRecordService {
	
	/**
	 * ���л���Ƽ�������׼��
	 * @param mdtDetTrd
	 * @return
	 */
	public RetResult doPrepare (List<MdtDetTrdModel> mdtDetTrd, String trdNo, String planId);
	/**
	 * ���л���Ƽ�����Ч
	 * @param planId
	 * @return
	 */
	public RetResult doTrade (String trdNo, String planId);
	
}
