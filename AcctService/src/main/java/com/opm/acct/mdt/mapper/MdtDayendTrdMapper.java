package com.opm.acct.mdt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtDayendTrdModel;


@Mapper
public interface MdtDayendTrdMapper {
	/**
	 * 插入交易信息
	 * @param mdtDayendTrdModel
	 * @return
	 */
	public int insertMdtDayendTrd (@Param("mdtDayendTrdModel") MdtDayendTrdModel mdtDayendTrdModel);
	
	/**
	 * 获取日结(取消日结)交易信息
	 * @param trdNo
	 * @return
	 */
	public MdtDayendTrdModel getMdtDayendTrd (@Param("trdNo") String trdNo);
	
}
