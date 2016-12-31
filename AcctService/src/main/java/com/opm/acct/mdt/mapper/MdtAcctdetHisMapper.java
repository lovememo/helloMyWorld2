package com.opm.acct.mdt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MdtAcctdetHisMapper {
	
	/**
	 * 受托户资金账务信息历史表更新
	 * @param trdNo
	 * @return
	 */
	public String insertMdtAcctDetHis (@Param("trdNo") String trdNo);
	
}
