package com.opm.core.app.dao;

import java.util.List;

import com.opm.core.app.model.TrdModel;

public interface ITrdDao {

	/**
	 * 插入交易主表
	 * @param trdModel
	 * @return
	 */
	public void addTrd(TrdModel trdModel);

	/**
	 * 查询交易数量
	 * @param trdNo
	 * @return
	 */
	public int qryTrdCount(TrdModel trdModel);
	
	/**
	 * 更新交易主表
	 * @param trdModel
	 */
	public void updTrd(TrdModel trdModel);

	/**
	 * 查询交易主表
	 * @param trdModel
	 */
	public List qryTrd(TrdModel trdModel);

	/**
	 * 通过交易号查询交易
	 * @param trdNo
	 * @return
	 */
	public TrdModel qryTrdByTrdNo(String trdNo);

}
