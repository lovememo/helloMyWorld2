package com.opm.core.app.service;

import com.opm.core.app.model.TrdModel;

public interface ITrdService {

	/**
	 * ������������
	 * @param trdModel
	 * @param appNo
	 * @return 
	 */
	public void saveTrd(TrdModel trdModel);
	
	/**
	 * �޸���������
	 * @param trdModel
	 */
	public void modTrd(TrdModel trdModel);

}
