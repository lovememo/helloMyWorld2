package com.opm.acct.mdt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtActMoRptModel;


@Mapper
public interface MdtActMoRptMapper {
	/**
	 * ��ȡ���л���������ת����Ϣ
	 * @param actId
	 * @param date
	 * @param mdtSubjectType
	 * @return
	 */
	public MdtActMoRptModel getMdtActMoRpt (@Param("actId") String actId,@Param("date") String date,
			@Param("mdtSubjectType") String mdtSubjectType);
	
	/**
	 * ��ȡ���
	 * @param actId
	 * @param date
	 * @param mdtSubjectType
	 * @return
	 */
	public String  getAmt (@Param("actId") String actId,@Param("date") String date,
			@Param("mdtSubjectType") String mdtSubjectType);
	
	/**
	 * �������л���������ת���
	 * @param list
	 * @return
	 */
	public int insertMdtActMoRpt (@Param("list") List<MdtActMoRptModel> list);
	
	/**
	 * ��ĩ ȡ����ĩ����
	 * @param actId
	 * @param date
	 * @return
	 */
	public int deleteMdtActMoRpt (@Param("actId") String actId,@Param("date") String date);
	
}
