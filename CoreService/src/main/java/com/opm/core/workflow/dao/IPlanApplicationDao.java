package com.opm.core.workflow.dao;

import com.opm.common.core.AppBasicInfo;
import com.opm.common.enumdict.ApplyStatus;

/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */
public interface IPlanApplicationDao {
	public void updateAppState(long appId, ApplyStatus applyStatus);

	public AppBasicInfo getPlanAppInfo(long appId);
}