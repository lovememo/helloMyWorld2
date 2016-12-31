package com.opm.core.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.common.mapper.FileMapper;
import com.opm.core.common.model.FileRelApp;

@Repository
public class FileDao {

	@Autowired
	FileMapper fileMapper;
	
	public FileRelApp qryFileRel(FileRelApp fileRelApp) {
		return fileMapper.qryFileRel(fileRelApp);
	}

	public int insertFileRelApp(FileRelApp fileRelApp) {
		return fileMapper.insertFileRelApp(fileRelApp);
	}
}
