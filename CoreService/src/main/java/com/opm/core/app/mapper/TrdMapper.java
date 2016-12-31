package com.opm.core.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.opm.core.app.model.TrdModel;

@Mapper
public interface TrdMapper {

	/**
	 * select
	 * table:opm_trd
	 * @param trdModel
	 * @return
	 */
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
