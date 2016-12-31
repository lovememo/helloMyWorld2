package com.opm.core.app.dao;

import java.util.List;

import com.opm.core.app.model.TrdModel;

public interface ITrdDao {

	/**
	 * ���뽻������
	 * @param trdModel
	 * @return
	 */
	public void addTrd(TrdModel trdModel);

	/**
	 * ��ѯ��������
	 * @param trdNo
	 * @return
	 */
	public int qryTrdCount(TrdModel trdModel);
	
	/**
	 * ���½�������
	 * @param trdModel
	 */
	public void updTrd(TrdModel trdModel);

	/**
	 * ��ѯ��������
	 * @param trdModel
	 */
	public List qryTrd(TrdModel trdModel);

	/**
	 * ͨ�����׺Ų�ѯ����
	 * @param trdNo
	 * @return
	 */
	public TrdModel qryTrdByTrdNo(String trdNo);

}
