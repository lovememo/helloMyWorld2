package com.opm.common.business.api;

import com.opm.common.support.SpringContextHolder;

/**
 * Created by kfzx-liuyz1 on 2016/12/14.
 */
public class VoucherBusinessApiFactory implements IBusinessApiFactory {
    @Override
    public IBusinessApi createBusinessApi() {
        return SpringContextHolder.getBean(VoucherBusinessApi.class);
    }
}
