package com.opm.acct.mdt.dao;

import java.util.List;

import com.opm.acct.mdt.model.TrdModel;


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
	@SuppressWarnings("rawtypes")
	public List qryTrd(TrdModel trdModel);

	/**
	 * ͨ�����׺Ų�ѯ����
	 * @param trdNo
	 * @return
	 */
	public TrdModel qryTrdByTrdNo(String trdNo);

}
