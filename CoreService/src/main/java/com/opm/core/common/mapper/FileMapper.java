package com.opm.core.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.opm.core.common.model.FileRelApp;

@Mapper
public interface FileMapper {

	FileRelApp qryFileRel(FileRelApp fileRelApp);

	int insertFileRelApp(FileRelApp fileRelApp);
}
