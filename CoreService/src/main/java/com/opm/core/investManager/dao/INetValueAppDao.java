package com.opm.core.investManager.dao;

import java.util.List;

import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueInfEntity;
import com.opm.core.investManager.entity.NetValueMAppEntity;
import com.opm.core.investManager.entity.NetValueTrdEntity;

/**
 * ��ֵ����
 * @author kfzx-chenym
 *
 */
public interface INetValueAppDao {
	/**
	 * ɾ��������ϸ
	 * @param netValueAppModel
	 */
	public void delApp(NetValueAppEntity netValueAppModel);

	/**
	 * ����������ϸ
	 * @param netValueAppModel
	 */
	public void addApp(NetValueAppEntity netValueAppModel);

	/**
	 * ��ѯ������ϸ
	 * @param qryNetValueAppModel
	 * @return
	 */
	public NetValueAppEntity qryAppByUniq(NetValueAppEntity qryNetValueAppModel);

	/**
	 * ��ѯ������ϸ
	 * @param qryNetValueAppEntity
	 * @return
	 */
	public List<NetValueAppEntity> qryApp(NetValueAppEntity qryNetValueAppEntity);

	/**
	 * ͨ��Ψһ����ѯ��������
	 * @param netValueMAppEntity
	 * @return
	 */
	public NetValueMAppEntity qryMAppByPK(NetValueMAppEntity netValueMAppEntity);

	/**
	 * ������������
	 * @param netValueMAppEntity
	 */
	public void addMApp(NetValueMAppEntity netValueMAppEntity);

	/**
	 * ɾ����������
	 * @param netValueMAppEntity
	 */
	public void delMApp(NetValueMAppEntity netValueMAppEntity);

}
