package com.opm.acct.mdt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtActInfModel;


@Mapper
public interface MdtActInfMapper {
	/**
	 * ��ȡ���л���Ϣ
	 * @return
	 */
	public MdtActInfModel getMdtActInf (@Param("actId") String actId);
	
}
