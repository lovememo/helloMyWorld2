package com.opm.common.core;

import com.opm.common.enumdict.OrderType;

/**
 * Created by kfzx-liuyz1 on 2016/10/28.
 */
public class AppBasicInfo {


    private long appId;

    private String planId;

    private OrderType orderType;

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }


}
