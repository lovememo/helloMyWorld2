package com.opm.acct.mdt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtActRelModel;


@Mapper
public interface MdtActRelMapper {
	/**
	 * ��ȡ���л���ϵ��Ϣ
	 * @param relId
	 * @return
	 */
	public MdtActRelModel getMdtActRel (@Param("relId") String relId);
	
}
