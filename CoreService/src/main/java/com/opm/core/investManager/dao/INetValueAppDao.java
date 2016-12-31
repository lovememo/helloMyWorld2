package com.opm.core.investManager.dao;

import java.util.List;

import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueInfEntity;
import com.opm.core.investManager.entity.NetValueMAppEntity;
import com.opm.core.investManager.entity.NetValueTrdEntity;

/**
 * 净值管理
 * @author kfzx-chenym
 *
 */
public interface INetValueAppDao {
	/**
	 * 删除申请明细
	 * @param netValueAppModel
	 */
	public void delApp(NetValueAppEntity netValueAppModel);

	/**
	 * 新增申请明细
	 * @param netValueAppModel
	 */
	public void addApp(NetValueAppEntity netValueAppModel);

	/**
	 * 查询申请明细
	 * @param qryNetValueAppModel
	 * @return
	 */
	public NetValueAppEntity qryAppByUniq(NetValueAppEntity qryNetValueAppModel);

	/**
	 * 查询申请明细
	 * @param qryNetValueAppEntity
	 * @return
	 */
	public List<NetValueAppEntity> qryApp(NetValueAppEntity qryNetValueAppEntity);

	/**
	 * 通过唯一键查询申请主表
	 * @param netValueMAppEntity
	 * @return
	 */
	public NetValueMAppEntity qryMAppByPK(NetValueMAppEntity netValueMAppEntity);

	/**
	 * 新增申请主表
	 * @param netValueMAppEntity
	 */
	public void addMApp(NetValueMAppEntity netValueMAppEntity);

	/**
	 * 删除申请主表
	 * @param netValueMAppEntity
	 */
	public void delMApp(NetValueMAppEntity netValueMAppEntity);

}
