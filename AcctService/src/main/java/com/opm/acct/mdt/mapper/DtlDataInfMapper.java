package com.opm.acct.mdt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.DtlDataInfModel;


@Mapper
public interface DtlDataInfMapper {
	/**
	 * 查询受托户导入数据
	 * @param serialNo
	 * @param checkFlag
	 * @param status
	 * @return
	 */
	public List<DtlDataInfModel> getFileData (@Param("serialNo") String serialNo,@Param("checkFlag") String checkFlag,
			@Param("status") String status,
			@Param("beginNum") int beginNum, @Param("fetchNum") int fetchNum);
	
}
