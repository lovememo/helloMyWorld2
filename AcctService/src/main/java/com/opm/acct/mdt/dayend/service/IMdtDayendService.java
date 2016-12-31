package com.opm.acct.mdt.dayend.service;

import java.util.List;

import com.opm.acct.mdt.model.MdtActInfModel;
import com.opm.acct.mdt.model.MdtDayendTrdModel;
import com.opm.common.business.param.RetResult;

public interface IMdtDayendService {
	
	/**
	 * 受托户日结数据准备
	 * @param planId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object> doPrepare (String planId, String beginDate, String endDate);
	/**
	 * 受托户日结生效
	 * @param trdNo
	 * @param actInfo
	 * @return
	 */
	public List<Object> doTrade (String trdNo, MdtActInfModel actInfo);
	
	/**
	 * 获取第一个未日结日期
	 * @param planId
	 * @return
	 */
	public String getFirstDayNoEnd (String planId);
	/**
	 * 受托户日结（取消日结）申请查询
	 * @param trdNo
	 * @return
	 */
	public MdtDayendTrdModel qryMdtDayendApp (String trdNo);
	
	/**
	 * 判断是否完成月结
	 * @param actId
	 * @param date
	 * @return
	 */
	public RetResult isFinishMonend (String planId, String date);
	
}
