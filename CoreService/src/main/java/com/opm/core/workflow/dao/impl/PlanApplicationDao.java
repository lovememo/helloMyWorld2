package com.opm.core.workflow.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.common.core.AppBasicInfo;
import com.opm.common.enumdict.ApplyStatus;
import com.opm.core.workflow.dao.IPlanApplicationDao;
import com.opm.core.workflow.mapper.AppMapper;

/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */

@Repository("PlanApplicationDao")
public class PlanApplicationDao implements IPlanApplicationDao {

    @Autowired
    private AppMapper appMapper;

    public void updateAppState(long appId,ApplyStatus applyStatus){
        this.appMapper.updateAppState(appId,applyStatus.getVal());
    }

    @Override
    public AppBasicInfo getPlanAppInfo(long appId) {
        return this.appMapper.getPlanAppInfo(appId);
    }


}
