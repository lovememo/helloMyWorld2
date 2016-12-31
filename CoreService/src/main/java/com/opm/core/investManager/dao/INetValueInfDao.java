package com.opm.core.investManager.dao;

import java.util.List;

import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueInfEntity;
import com.opm.core.investManager.model.NetValueAppFormModel2;

public interface INetValueInfDao {

	/**
	 * ͨ����Ϣ������ѯ������ϸ
	 * @param qryNetValueInfModel
	 * @return
	 */
	public NetValueInfEntity qryInfByPK(NetValueInfEntity qryNetValueInfModel);

	/**
	 * ������Ϣ��ϸ
	 * @param netValueInfMode
	 */
	public void addInf(NetValueInfEntity netValueInfMode);

	/**
	 * ������Ϣ��ϸ
	 * @param updNetValueInfModel
	 */
	public void updInf(NetValueInfEntity updNetValueInfModel);

	/**
	 * �������б�
	 * @param planId
	 * @return
	 */
	public List<NetValueAppEntity> qryEvaluateDateList(String planId);

	/**
	 * ��ֵ��ѯ
	 * @param netValueInfEntity
	 * @return
	 */
	public NetValueAppFormModel2 qryInfDesc(NetValueInfEntity netValueInfEntity);

	/**
	 * ��ѯ��ֵ�б�
	 * @param qryNetValueInfEntity
	 * @return
	 */
	public List<NetValueInfEntity> qryInf(NetValueInfEntity qryNetValueInfEntity);
}
