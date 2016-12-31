package com.opm.acct.mdt.det.mod.service;

import java.util.List;

public interface IMdtDetModService {
	
	/**
	 * ���л���ϸ�޸�����׼��
	 * @param planId
	 * @param seqId
	 * @param dealType
	 * @param memo
	 * @return
	 */
	public List<Object> doPrepare (String planId, String seqId, String dealType,String memo);
	/**
	 * ���л���ϸ������Ч
	 * @param trdNo
	 * @param planId
	 * @return
	 */
	public List<Object> doTrade (String trdNo, String planId);
	
}
