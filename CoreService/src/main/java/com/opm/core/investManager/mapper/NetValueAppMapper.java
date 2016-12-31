package com.opm.core.investManager.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueMAppEntity;


@Mapper
public interface NetValueAppMapper {

	/**
	 * delete
	 * table:opm_net_value_app
	 * @param netValueAppModel
	 */
	public void delApp(NetValueAppEntity netValueAppModel);

	/**
	 * insert
	 * table:opm_net_value_app
	 * @param netValueAppModel
	 */
	public void addApp(NetValueAppEntity netValueAppModel);

	/**
	 * select
	 * @param netValueAppModel
	 * @return
	 */
	public List<NetValueAppEntity> qryApp(NetValueAppEntity netValueAppModel);

	/**
	 * select
	 * table:opm_net_value_m_app
	 * @param netValueMAppEntity
	 * @return
	 */
	public List<NetValueMAppEntity> qryMApp(NetValueMAppEntity netValueMAppEntity);

	/**
	 * insert
	 * table:opm_net_value_m_app
	 * @param netValueMAppEntity
	 */
	public void addMApp(NetValueMAppEntity netValueMAppEntity);

	/**
	 * delete
	 * table:opm_net_value_m_app
	 * @param netValueMAppEntity
	 */
	public void delMApp(NetValueMAppEntity netValueMAppEntity);
	

}
