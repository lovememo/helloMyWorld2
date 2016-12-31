package com.opm.core.investManager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueEntity;
import com.opm.core.investManager.entity.NetValueInfEntity;
import com.opm.core.investManager.model.NetValueAppFormModel2;

@Mapper
public interface NetValueInfMapper {

	/**
	 * select
	 * table:opm_net_value_inf
	 * @param netValueInfModel
	 * @return
	 */
	public List<NetValueInfEntity> qryInf(NetValueInfEntity netValueInfModel);

	/**
	 * insert
	 * table:opm_net_value_inf
	 * @param netValueInfMode
	 */
	public void addInf(NetValueInfEntity netValueInfMode);

	/**
	 * update
	 * table:opm_net_value_inf
	 * @param netValueInfModel
	 */
	public void updInf(NetValueInfEntity netValueInfModel);

	/**
	 * select
	 * table:opm_net_value_inf
	 * @param netValueInfEntity
	 * @return
	 */
	public List<NetValueAppEntity> qryEvaluateDate(NetValueInfEntity netValueInfEntity);

	/**
	 * select
	 * table:opm_net_value_inf,opm_net_value_app, opm_net_value_m_app
	 * @param netValueInfEntity
	 * @return
	 */
	public NetValueAppFormModel2 qryInfDesc(NetValueInfEntity netValueInfEntity);

}
