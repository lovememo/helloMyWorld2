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

    private String direction;//1-�� 2-��

    private String remark;//��ע
    private String medium;//����
    private String charset;//����
    private String partition;//0-���������ļ��� 1-�������п�����Ϣ���ļ���
    private List<DtlRule> parseRuleList;//��������
    private List<DtlRule> checkRuleList;//�������

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
