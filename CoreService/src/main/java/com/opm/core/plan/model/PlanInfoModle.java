package com.opm.core.plan.model;

/**
 * Created by kfzx-liuyz1 on 2016/10/24.
 */
public class PlanInfoModle {


    private String productId;
    private String productName;
    private String planId;
    private String planName;
    private String planLocate;
    private String totalCount;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanLocate() {
        return planLocate;
    }

    public void setPlanLocate(String planLocate) {
        this.planLocate = planLocate;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }


}
