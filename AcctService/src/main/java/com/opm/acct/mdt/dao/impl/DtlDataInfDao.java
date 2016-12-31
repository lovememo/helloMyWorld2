package com.opm.acct.mdt.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.mdt.dao.IDtlDataInfDao;
import com.opm.acct.mdt.mapper.DtlDataInfMapper;
import com.opm.acct.mdt.model.DtlDataInfModel;
import com.opm.common.aspect.anno.TargetDataSource;

@Repository("DtlDataInfDao")
public class DtlDataInfDao implements IDtlDataInfDao {
	@Autowired
	private DtlDataInfMapper dtlDataInfMapper;

	 @TargetDataSource(name="qry")
	@Override
	public List<DtlDataInfModel> getFileData(String serialNo, String checkFlag, String status,int beginNum, int fetchNum) {
		return dtlDataInfMapper.getFileData(serialNo, checkFlag, status,beginNum,fetchNum);
	}



}
