package com.opm.acct.mdt.dao;

import java.util.List;

import com.opm.acct.mdt.model.DtlDataInfModel;

public interface IDtlDataInfDao {
	/**
	 * 查询受托户导入数据
	 * @param serialNo
	 * @param checkFlag
	 * @param status
	 * @return
	 */
	public List<DtlDataInfModel> getFileData (String serialNo,String checkFlag, String status,
			 int beginNum, int fetchNum);
}
