package com.opm.acct.mdt.dao;

import com.opm.acct.mdt.model.MdtDayendTrdModel;

public interface IMdtDayendTrdDao {
	/**
	 * 插入交易信息
	 * @param mdtDayendTrdModel
	 * @return
	 */
	public int insertMdtDayendTrd (MdtDayendTrdModel mdtDayendTrdModel);
	
	/**
	 * 获取日结(取消日结)交易信息
	 * @param trdNo
	 * @return
	 */
	public MdtDayendTrdModel getMdtDayendTrd (String trdNo);
	
}
