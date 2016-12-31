package com.opm.common.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opm.common.utils.UtilTools;

public final class RequestModel implements Serializable {
    /**
     * UID
     */
    private static final long serialVersionUID = -1941293842382361438L;

    /**
     * ��ʼ����
     */
    private String begNum;

    /**
     * ��ȡ����
     */
    private String fetchNum;

    /**
     * ����Ԫ��
     * �û��ύ�Ĺ��в���
     * ��ʽ��
     * { "callSrvId": "", "callInfId": "", "serialId": "" }
     */
    private String publicData;

    /**
     * �������ֶ�Ӧ��Map
     */
    @JsonIgnore
    private Map<String, Object> publicDataCtx = null;

    /**
     * ˽��Ԫ��
     * �û��ύ��˽�в���
     * ��ʽ��
     * {key : value}
     */
    private String privateData;

    /**
     * ˽�в��ֶ�Ӧ��Map
     */
    @JsonIgnore
    private Map<String, Object> privateDataCtx = null;

    /**
     * Ĭ�Ϲ��캯��
     */
    public RequestModel() {
        this("", "");
    }

    /**
     * ���캯��
     *
     * @param publicData  ����Ԫ��
     * @param privateData ˽��Ԫ��
     */
    public RequestModel(String publicData, String privateData) {
        this.publicData = publicData;
        this.privateData = privateData;
    }

    /**
     * ��ȡ�������ݲ��֣�ԭʼ���ַ���
     *
     * @return ԭʼ���е�����
     */
    public String getPublicData() {
        return publicData;
    }

    /**
     * ���ù������ݲ���[����json��ʽ]
     *
     * @param publicData �������ݶ�
     */
    public void setPublicData(String publicData) {
        //this.publicData = publicData;
        try {
            this.publicData = new String(publicData.getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            this.publicData = publicData;
        }
    }

    /**
     * ��ȡ˽�����ݲ��֣�ԭʼ���ַ���
     *
     * @return ԭʼ˽�е�����
     */
    public String getPrivateData() {
        return privateData;
    }

    /**
     * ����˽�����ݲ���[����json��ʽ]
     *
     * @param privateData ˽�����ݶ�
     */
    public void setPrivateData(String privateData) {
//        this.privateData = privateData;
        try {
            this.privateData = new String(privateData.getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            this.privateData = privateData;
        }
    }

    public String getBegNum() {
        return begNum;
    }

    public void setBegNum(String begNum) {
        this.begNum = begNum;
    }

    public String getFetchNum() {
        return fetchNum;
    }

    public void setFetchNum(String fetchNum) {
        this.fetchNum = fetchNum;
    }

    @Override
    public String toString() {
        return "RequestModel = {" +
                "privateData='" + privateData + '\'' +
                ", publicData='" + publicData + '\'' +
                '}';
    }

    /**
     * ��ȡ˽�е�json����
     *
     * @return key:val��ʽ��Map
     */
    @JsonIgnore
    public synchronized Map<String, Object> getPrivateCtx() {
        if (null == this.privateDataCtx) {
            this.privateDataCtx = UtilTools.parseToMap(this.getPrivateData());
        }

        return this.privateDataCtx;
    }

    /**
     * ��ȡ���е�json����
     *
     * @return key:val��ʽ��Map
     */
    @JsonIgnore
    public synchronized Map<String, Object> getPublicCtx() {
        if (null == this.publicDataCtx) {
            this.publicDataCtx = UtilTools.parseToMap(this.getPublicData());
        }

        return this.publicDataCtx;
    }

    /**
     * ���ݸ�����key����ȡ��Ӧ��ֵ[˽�в��ֵ�����]
     * <p>
     * ���������keyֵ��Ӧ�����ݲ����ڣ��򷵻ؿ��ַ���("")
     *
     * @param key keyֵ
     * @return ��Ӧ��ֵ
     */
    @JsonIgnore
    public String getStringValue(String key) {
        Optional<String> retOp = Optional.empty();
        String qryKey = key.trim();

        if (this.getPrivateCtx().containsKey(qryKey)) {
            Object obj = this.getPrivateCtx().get(qryKey);
            if (obj instanceof String) {
                retOp = Optional.of((String) obj);
            }
        }

        return retOp.isPresent() ? retOp.get() : StringUtils.EMPTY;
    }

    /**
     * ���ݸ�����key����ȡ��Ӧ��ֵ[˽�в��ֵ�����]
     * <p>
     * ���������keyֵ��Ӧ�����ݲ����ڣ��򷵻ؿ��б�
     *
     * @param key keyֵ
     * @return ��Ӧ��List
     */
    @SuppressWarnings("unchecked")
    @JsonIgnore
    public List<String> getListValue(String key) {
        Optional<List<String>> retOp = Optional.empty();
        List<String> ls = new ArrayList<String>();
        String qryKey = key.trim();
        if (this.getPrivateCtx().containsKey(qryKey)) {
            Object obj = this.getPrivateCtx().get(qryKey);
            if (obj instanceof List) {
                ((List<String>) obj).forEach(item -> ls.add(item));
                retOp = Optional.of(ls);
            }
        }

        return retOp.isPresent() ? retOp.get() : Collections.emptyList();
    }

    /**
     * ���ݸ�����key����ȡ��Ӧ��ֵ[˽�в��ֵ�����]
     * <p>
     * ���������keyֵ��Ӧ�����ݲ����ڣ��򷵻ؿ��б�
     *
     * @param key keyֵ
     * @return ��Ӧ��List
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @JsonIgnore
    public List<Map> getMapListValue(String key) {
        Optional<List<Map>> retOp = Optional.empty();
        List<Map> ls = new ArrayList<Map>();
        String qryKey = key.trim();
        if (this.getPrivateCtx().containsKey(qryKey)) {
            Object obj = this.getPrivateCtx().get(qryKey);
            if (obj instanceof List) {
                ((List<Map>) obj).forEach(item -> ls.add(item));
                retOp = Optional.of(ls);
            }
        }

        return retOp.isPresent() ? retOp.get() : Collections.emptyList();
    }

    @JsonIgnore
    public Map getMapValue(String key) {
        Optional<Map> retOp = Optional.empty();
        String qryKey = key.trim();

        if (this.getPrivateCtx().containsKey(qryKey)) {
            Object obj = this.getPrivateCtx().get(qryKey);
            if (obj instanceof Map) {
                retOp = Optional.of((Map) obj);
            }
        }

        return retOp.isPresent() ? retOp.get() : null;
    }


}
