package com.opm.acct.mdt.dayend.cancel.service;

import java.util.List;

import com.opm.acct.mdt.model.MdtActInfModel;

public interface IMdtDayendCancelService {
	
	/**
	 * 受托户取消日结数据准备
	 * @param planId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object> doPrepare (String planId, String beginDate, String endDate);
	/**
	 * 受托户取消日结生效
	 * @param trdNo
	 * @param actInfo
	 * @return
	 */
	public List<Object> doTrade (String trdNo, MdtActInfModel actInfo);
	

	/**
	 * 获取最后一个已结账日期
	 * @param planId
	 * @return
	 */
	public String getLastDayendDate (String planId);
	
}
