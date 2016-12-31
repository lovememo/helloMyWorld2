package com.opm.voucher.query.dao.impl;

import com.opm.common.aspect.anno.TargetDataSource;
import com.opm.common.core.AppBasicInfo;
import com.opm.voucher.query.dao.IAppInfoQryDao;
import com.opm.voucher.query.mapper.AppInfoQryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kfzx-liuyz1 on 2016/11/29.
 */
@Repository
public class AppInfoQryDao implements IAppInfoQryDao {

    @Autowired
    private AppInfoQryMapper appInfoQryMapper;

    @TargetDataSource(name="qry")
    @Override
    public AppBasicInfo getPlanAppInfo(long appId) {
        return this.appInfoQryMapper.getPlanAppInfo(appId);
    }
}
