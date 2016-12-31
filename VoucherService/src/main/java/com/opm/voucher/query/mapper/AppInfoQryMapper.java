package com.opm.voucher.query.mapper;

import com.opm.common.core.AppBasicInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by kfzx-liuyz1 on 2016/11/29.
 */
public interface AppInfoQryMapper {
    public AppBasicInfo getPlanAppInfo(@Param("appNo") long appId);
}
