package com.opm.core.investManager.dao;

import java.util.List;

import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueInfEntity;
import com.opm.core.investManager.model.NetValueAppFormModel2;

public interface INetValueInfDao {

	/**
	 * 通过信息主键查询交易明细
	 * @param qryNetValueInfModel
	 * @return
	 */
	public NetValueInfEntity qryInfByPK(NetValueInfEntity qryNetValueInfModel);

	/**
	 * 新增信息明细
	 * @param netValueInfMode
	 */
	public void addInf(NetValueInfEntity netValueInfMode);

	/**
	 * 更新信息明细
	 * @param updNetValueInfModel
	 */
	public void updInf(NetValueInfEntity updNetValueInfModel);

	/**
	 * 定价日列表
	 * @param planId
	 * @return
	 */
	public List<NetValueAppEntity> qryEvaluateDateList(String planId);

	/**
	 * 净值查询
	 * @param netValueInfEntity
	 * @return
	 */
	public NetValueAppFormModel2 qryInfDesc(NetValueInfEntity netValueInfEntity);

	/**
	 * 查询净值列表
	 * @param qryNetValueInfEntity
	 * @return
	 */
	public List<NetValueInfEntity> qryInf(NetValueInfEntity qryNetValueInfEntity);
}
