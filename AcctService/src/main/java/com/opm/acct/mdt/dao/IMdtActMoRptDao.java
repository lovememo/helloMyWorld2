package com.opm.acct.mdt.dao;

import java.util.List;

import com.opm.acct.mdt.model.MdtActInfModel;
import com.opm.acct.mdt.model.MdtActMoRptModel;

public interface IMdtActMoRptDao {
	/**
	 * 根据日期获取当前余额
	 * @param actInfo
	 * @param mdtSubjectType
	 * @param date
	 * @return
	 */
	public String getAmtByDate(MdtActInfModel actInfo, String mdtSubjectType ,String date);
	
	/**
	 * 插入受托户会计余额月转存表
	 * @param list
	 * @return
	 */
	public int insertMdtActMoRpt (List<MdtActMoRptModel> list);
	
	/**
	 * 月末 取消月末报表
	 * @param actId
	 * @param date
	 * @return
	 */
	public int deleteMdtActMoRpt (String actId,String date);
}
