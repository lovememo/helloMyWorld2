package com.opm.core.investManager.dao;

import java.util.List;

import com.opm.core.investManager.entity.NetValueTrdEntity;

public interface INetValueTrdDao {

	/**
	 * ͨ�����׺Ų�ѯ������ϸ
	 * @param trdNo
	 * @return
	 */
	public List<NetValueTrdEntity> qryTrdByTrdNo(String trdNo);

	/**
	 * ɾ��������ϸ
	 * @param netValueTrdModel
	 */
	public void delTrd(NetValueTrdEntity netValueTrdModel);

	/**
	 * ����������ϸ
	 * @param netValueTrdModel
	 */
	public void addTrd(NetValueTrdEntity netValueTrdModel);
}
