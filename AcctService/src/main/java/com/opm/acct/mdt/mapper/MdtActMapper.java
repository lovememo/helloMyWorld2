package com.opm.acct.mdt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MdtActMapper {
	/**
	 * �������л��������˱�
	 * @return
	 */
	public int insertMdtAct (@Param("actId") String actId);
	
}
