package com.opm.voucher.query.dao;

import com.opm.common.core.AppBasicInfo;

/**
 * Created by kfzx-liuyz1 on 2016/11/29.
 */
public interface IAppInfoQryDao {

    public AppBasicInfo getPlanAppInfo(long appId);

}
