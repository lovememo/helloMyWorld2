package com.opm.voucher.query.service.impl;

import com.opm.common.core.AppBasicInfo;
import com.opm.voucher.query.dao.IAppInfoQryDao;
import com.opm.voucher.query.service.IAppInfoQryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kfzx-liuyz1 on 2016/11/29.
 */
@Service("AppInfoQryService")
public class AppInfoQryService implements IAppInfoQryService {

    @Autowired
    private IAppInfoQryDao appInfoQryDao;


    @Override
    public AppBasicInfo getPlanAppInfo(long appId) {
        return this.appInfoQryDao.getPlanAppInfo(appId);
    }
}
