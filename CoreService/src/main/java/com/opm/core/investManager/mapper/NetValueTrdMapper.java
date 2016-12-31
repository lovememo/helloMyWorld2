package com.opm.core.investManager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.opm.core.investManager.entity.NetValueTrdEntity;

@Mapper
public interface NetValueTrdMapper {

	/**
	 * select
	 * table:opm_net_value_trd
	 * @param qryNetValueTreMdoel
	 * @return
	 */
	public List<NetValueTrdEntity> qryTrd(NetValueTrdEntity qryNetValueTreMdoel);

	/**
	 * delete
	 * table:opm_net_value_trd
	 * @param netValueTrdModel
	 */
	public void delTrd(NetValueTrdEntity netValueTrdModel);

	/**
	 * insert
	 * table:opm_net_value_trd
	 * @param netValueTrdModel
	 */
	public void addTrd(NetValueTrdEntity netValueTrdModel);

}
