package com.opm.voucher.order.entity;

import com.opm.common.enumdict.OrderType;

import java.util.Date;

/**
 * Created by kfzx-liuyz1 on 2016/10/30.
 */
public class OrderEntity {

    private long orderId;
    private String planId;
    private OrderType orderType;
    private long appId;
    private String coPlanFlag;
    private Date updateTime;
    private boolean validFlag;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public boolean isValidFlag() {
        return validFlag;
    }

    public void setValidFlag(boolean validFlag) {
        this.validFlag = validFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

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

    public String getCoPlanFlag() {
        return coPlanFlag;
    }

    public void setCoPlanFlag(String coPlanFlag) {
        this.coPlanFlag = coPlanFlag;
    }

}
