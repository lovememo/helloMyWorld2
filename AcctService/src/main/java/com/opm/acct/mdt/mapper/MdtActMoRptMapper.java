package com.opm.acct.mdt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtActMoRptModel;


@Mapper
public interface MdtActMoRptMapper {
	/**
	 * 获取受托户会计余额月转存信息
	 * @param actId
	 * @param date
	 * @param mdtSubjectType
	 * @return
	 */
	public MdtActMoRptModel getMdtActMoRpt (@Param("actId") String actId,@Param("date") String date,
			@Param("mdtSubjectType") String mdtSubjectType);
	
	/**
	 * 获取余额
	 * @param actId
	 * @param date
	 * @param mdtSubjectType
	 * @return
	 */
	public String  getAmt (@Param("actId") String actId,@Param("date") String date,
			@Param("mdtSubjectType") String mdtSubjectType);
	
	/**
	 * 插入受托户会计余额月转存表
	 * @param list
	 * @return
	 */
	public int insertMdtActMoRpt (@Param("list") List<MdtActMoRptModel> list);
	
	/**
	 * 月末 取消月末报表
	 * @param actId
	 * @param date
	 * @return
	 */
	public int deleteMdtActMoRpt (@Param("actId") String actId,@Param("date") String date);
	
}
