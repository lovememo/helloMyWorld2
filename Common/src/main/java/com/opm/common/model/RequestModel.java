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
     * 开始条数
     */
    private String begNum;

    /**
     * 获取条数
     */
    private String fetchNum;

    /**
     * 公共元素
     * 用户提交的共有部分
     * 格式：
     * { "callSrvId": "", "callInfId": "", "serialId": "" }
     */
    private String publicData;

    /**
     * 公共部分对应的Map
     */
    @JsonIgnore
    private Map<String, Object> publicDataCtx = null;

    /**
     * 私有元素
     * 用户提交的私有部分
     * 格式：
     * {key : value}
     */
    private String privateData;

    /**
     * 私有部分对应的Map
     */
    @JsonIgnore
    private Map<String, Object> privateDataCtx = null;

    /**
     * 默认构造函数
     */
    public RequestModel() {
        this("", "");
    }

    /**
     * 构造函数
     *
     * @param publicData  公共元素
     * @param privateData 私有元素
     */
    public RequestModel(String publicData, String privateData) {
        this.publicData = publicData;
        this.privateData = privateData;
    }

    /**
     * 获取共有数据部分，原始的字符串
     *
     * @return 原始共有的内容
     */
    public String getPublicData() {
        return publicData;
    }

    /**
     * 设置共有数据部分[符合json格式]
     *
     * @param publicData 公共数据段
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
     * 获取私有数据部分，原始的字符串
     *
     * @return 原始私有的内容
     */
    public String getPrivateData() {
        return privateData;
    }

    /**
     * 设置私有数据部分[符合json格式]
     *
     * @param privateData 私有数据段
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
     * 获取私有的json对象
     *
     * @return key:val格式的Map
     */
    @JsonIgnore
    public synchronized Map<String, Object> getPrivateCtx() {
        if (null == this.privateDataCtx) {
            this.privateDataCtx = UtilTools.parseToMap(this.getPrivateData());
        }

        return this.privateDataCtx;
    }

    /**
     * 获取共有的json对象
     *
     * @return key:val格式的Map
     */
    @JsonIgnore
    public synchronized Map<String, Object> getPublicCtx() {
        if (null == this.publicDataCtx) {
            this.publicDataCtx = UtilTools.parseToMap(this.getPublicData());
        }

        return this.publicDataCtx;
    }

    /**
     * 根据给定的key来获取对应的值[私有部分的内容]
     * <p>
     * 如果给定的key值对应的内容不存在，则返回空字符串("")
     *
     * @param key key值
     * @return 对应的值
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
     * 根据给定的key来获取对应的值[私有部分的内容]
     * <p>
     * 如果给定的key值对应的内容不存在，则返回空列表
     *
     * @param key key值
     * @return 对应的List
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
     * 根据给定的key来获取对应的值[私有部分的内容]
     * <p>
     * 如果给定的key值对应的内容不存在，则返回空列表
     *
     * @param key key值
     * @return 对应的List
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
