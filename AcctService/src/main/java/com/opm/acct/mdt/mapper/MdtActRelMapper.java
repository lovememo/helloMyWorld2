package com.opm.acct.mdt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtActRelModel;


@Mapper
public interface MdtActRelMapper {
	/**
	 * 获取受托户关系信息
	 * @param relId
	 * @return
	 */
	public MdtActRelModel getMdtActRel (@Param("relId") String relId);
	
}
