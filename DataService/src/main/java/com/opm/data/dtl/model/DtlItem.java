package com.opm.data.dtl.model;

import com.opm.common.enumdict.DtlMedium;
import com.opm.common.enumdict.DtlMode;
import com.opm.common.enumdict.DtlType;

import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/29.
 */
public class DtlItem {
    private String dtlItemId;
    private String fileName;
    private String status;
    private String version;
    private String type;//1-�ӿ� 2-�ļ�����

    private String mode;//1-�ļ� 2-�ӿ�

    private String direction;//1-�� 2-��
    private String remark;//��ע

    private String medium;//����
    private String charset;//����
    private List<DtlRule> parseHeaderRuleList;//����ͷ����
    private List<DtlRule> parseBodyRuleList;//���������
    private List<DtlRule> checkRuleList;//�������
    private String headerRuleId;

    private String bodyRuleId;

    /**
     * �Ƿ���Ҫ�ļ�����
     * @return
     */
    public boolean needParseFixedFile() {
        //�ӿ������ļ�����
        boolean infFlag = DtlType.valueOfCode(this.type) == DtlType.INTERFACE && DtlMode.valueOfCode(this.mode) == DtlMode.FILE;
        //�ļ�����
        boolean fileFlag = DtlType.valueOfCode(this.type) == DtlType.FILE;
        //��Ҫ�ļ�����
        boolean isFile = fileFlag || infFlag;
        boolean isFixedSize = DtlMedium.valueOfCode(this.medium) == DtlMedium.TXT_FIXED_SIZE;
        return isFile && isFixedSize;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<DtlRule> getParseBodyRuleList() {
        return parseBodyRuleList;
    }

    public void setParseBodyRuleList(List<DtlRule> parseBodyRuleList) {
        this.parseBodyRuleList = parseBodyRuleList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public List<DtlRule> getParseHeaderRuleList() {
        return parseHeaderRuleList;
    }

    public void setParseHeaderRuleList(List<DtlRule> parseHeaderRuleList) {
        this.parseHeaderRuleList = parseHeaderRuleList;
        this.parseHeaderRuleList.sort(new DtlRuleComparator());//���ֶ�˳������
    }

    public List<DtlRule> getCheckRuleList() {
        return checkRuleList;
    }

    public void setCheckRuleList(List<DtlRule> checkRuleList) {
        this.checkRuleList = checkRuleList;
    }

    @Override
    public String toString() {
        return "DtlItem{" +
                "dtlItemId='" + dtlItemId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", status='" + status + '\'' +
                ", version='" + version + '\'' +
                ", type='" + type + '\'' +
                ", mode='" + mode + '\'' +
                ", direction='" + direction + '\'' +
                ", remark='" + remark + '\'' +
                ", medium='" + medium + '\'' +
                ", charset='" + charset + '\'' +
                ", parseHeaderRuleList=" + parseHeaderRuleList +
                ", parseBodyRuleList=" + parseBodyRuleList +
                ", checkRuleList=" + checkRuleList +
                ", headerRuleId='" + headerRuleId + '\'' +
                ", bodyRuleId='" + bodyRuleId + '\'' +
                '}';
    }
}
