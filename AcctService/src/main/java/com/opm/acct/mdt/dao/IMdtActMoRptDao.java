package com.opm.acct.mdt.dao;

import java.util.List;

import com.opm.acct.mdt.model.MdtActInfModel;
import com.opm.acct.mdt.model.MdtActMoRptModel;

public interface IMdtActMoRptDao {
	/**
	 * �������ڻ�ȡ��ǰ���
	 * @param actInfo
	 * @param mdtSubjectType
	 * @param date
	 * @return
	 */
	public String getAmtByDate(MdtActInfModel actInfo, String mdtSubjectType ,String date);
	
	/**
	 * �������л���������ת���
	 * @param list
	 * @return
	 */
	public int insertMdtActMoRpt (List<MdtActMoRptModel> list);
	
	/**
	 * ��ĩ ȡ����ĩ����
	 * @param actId
	 * @param date
	 * @return
	 */
	public int deleteMdtActMoRpt (String actId,String date);
}
