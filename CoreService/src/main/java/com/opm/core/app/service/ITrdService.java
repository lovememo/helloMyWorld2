package com.opm.core.app.service;

import com.opm.core.app.model.TrdModel;

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
