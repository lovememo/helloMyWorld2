package com.opm.core.workflow.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.common.core.AppBasicInfo;

/**
 * Created by kfzx-liuyz1 on 2016/10/26.
 */
@Mapper
public interface AppMapper {
	public AppBasicInfo getPlanAppInfo(@Param("appNo") long appId);

	public void updateAppState(@Param("appNo") long appId, @Param("appState") long applyStatus);
}
