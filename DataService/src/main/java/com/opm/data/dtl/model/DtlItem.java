package com.opm.data.dtl.model;

import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/29.
 */
public class DtlItem {
    private String dtlItemId;
    private String fileName;
    private String status;
    private String version;

    private String direction;//1-内 2-外

    private String remark;//备注
    private String medium;//介质
    private String charset;//编码
    private String partition;//0-仅仅包含文件体 1-包含首行控制信息及文件体
    private List<DtlRule> parseRuleList;//解析规则
    private List<DtlRule> checkRuleList;//清理规则

    private String headerRuleId;
    private String bodyRuleId;

    public String getHeaderRuleId() {
        return headerRuleId;
    }

    public void setHeaderRuleId(String headerRuleId) {
        this.headerRuleId = headerRuleId;
    }

    public String getBodyRuleId() {
        return bodyRuleId;
    }

    public void setBodyRuleId(String bodyRuleId) {
        this.bodyRuleId = bodyRuleId;
    }

    public String getDtlItemId() {
        return dtlItemId;
    }

    public void setDtlItemId(String dtlItemId) {
        this.dtlItemId = dtlItemId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getPartition() {
        return partition;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }

    public List<DtlRule> getParseRuleList() {
        return parseRuleList;
    }

    public void setParseRuleList(List<DtlRule> parseRuleList) {
        this.parseRuleList = parseRuleList;
    }

    public List<DtlRule> getCheckRuleList() {
        return checkRuleList;
    }

    public void setCheckRuleList(List<DtlRule> checkRuleList) {
        this.checkRuleList = checkRuleList;
    }
}
