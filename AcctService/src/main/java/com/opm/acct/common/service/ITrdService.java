package com.opm.acct.common.service;

import com.opm.acct.mdt.model.TrdModel;

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
