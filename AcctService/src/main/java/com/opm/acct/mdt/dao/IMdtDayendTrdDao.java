package com.opm.acct.mdt.dao;

import com.opm.acct.mdt.model.MdtDayendTrdModel;

public interface IMdtDayendTrdDao {
	/**
	 * ���뽻����Ϣ
	 * @param mdtDayendTrdModel
	 * @return
	 */
	public int insertMdtDayendTrd (MdtDayendTrdModel mdtDayendTrdModel);
	
	/**
	 * ��ȡ�ս�(ȡ���ս�)������Ϣ
	 * @param trdNo
	 * @return
	 */
	public MdtDayendTrdModel getMdtDayendTrd (String trdNo);
	
}
