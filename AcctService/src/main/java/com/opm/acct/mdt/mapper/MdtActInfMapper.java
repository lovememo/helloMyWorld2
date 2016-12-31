package com.opm.acct.mdt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtActInfModel;


@Mapper
public interface MdtActInfMapper {
	/**
	 * 获取受托户信息
	 * @return
	 */
	public MdtActInfModel getMdtActInf (@Param("actId") String actId);
	
}
