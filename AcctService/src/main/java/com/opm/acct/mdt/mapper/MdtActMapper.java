package com.opm.acct.mdt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MdtActMapper {
	/**
	 * 插入受托户会计余额账表
	 * @return
	 */
	public int insertMdtAct (@Param("actId") String actId);
	
}
