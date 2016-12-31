package com.opm.acct.common.service;

import com.opm.acct.mdt.model.TrdModel;

public interface ITrdService {

	/**
	 * 保存申请主表
	 * @param trdModel
	 * @param appNo
	 * @return 
	 */
	public void saveTrd(TrdModel trdModel);
	
	/**
	 * 修改申请主表
	 * @param trdModel
	 */
	public void modTrd(TrdModel trdModel);

}
