package com.opm.data.dtl.model;

/**
 * Created by kfzx-jinjf on 2016/12/29.
 */
public class DtlRule {
    private String ruleId;
    private int fieldSeq;
    private String ruleType;
    private String ruleExpression;
    private String status;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public int getFieldSeq() {
        return fieldSeq;
    }

    public void setFieldSeq(int fieldSeq) {
        this.fieldSeq = fieldSeq;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleExpression() {
        return ruleExpression;
    }

    public void setRuleExpression(String ruleExpression) {
        this.ruleExpression = ruleExpression;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
