package com.opm.acct.mdt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MdtAcctdetHisMapper {
	
	/**
	 * ���л��ʽ�������Ϣ��ʷ�����
	 * @param trdNo
	 * @return
	 */
	public String insertMdtAcctDetHis (@Param("trdNo") String trdNo);
	
}
