package com.opm.acct.mdt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.opm.acct.mdt.model.TrdModel;

@Mapper
public interface TrdMapper {

	/**
	 * select
	 * table:opm_trd
	 * @param trdModel
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List qryTrd(TrdModel trdModel);
	
	/**
	 * insert
	 * table:opm_trd
	 * @param trdModel
	 */
	public void addTrd(TrdModel trdModel);
	
	/**
	 * update
	 * table:opm_trd
	 * @param trdModel
	 */
	public void updTrd(TrdModel trdModel);
	
}
