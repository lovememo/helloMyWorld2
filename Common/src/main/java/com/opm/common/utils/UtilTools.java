package com.opm.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import ch.qos.logback.classic.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

public class UtilTools {
    /**
     * 错误日志记录
     */
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UtilTools.class);

    /**
     * CTP的翻页
     * <br/>
     * <b>使用方法</b>
     * UtilTools.turnPage(list, totalNum);
     * <p>
     * 返回内容：
     * 1 成功
     * [{"totalNum":"29"}, {"appNo":"200960","empName":""}, ..]
     * 2 失败
     * [{"errorCode":"ctpUtil1000"}]
     *
     * @param ls 待返回的List
     * @return 包装成翻页后的CTP构造
     */
    public static List<Object> turnPage(List<? extends Object> ls) {
        return UtilTools.turnPage(ls, 0);
    }


    /**
     * CTP的翻页
     * <br/>
     * <b>使用方法</b>
     * UtilTools.turnPage(list, totalNum);
     * <p>
     * 返回内容：
     * 1 成功
     * [{"totalNum":"29"}, {"appNo":"200960","empName":""}, ..]
     * 2 失败
     * [{"errorCode":"ctpUtil1000"}]
     *
     * @param ls       待返回的List
     * @param totalNum 中的记录数量
     * @return 包装成翻页后的CTP构造
     */
    public static List<Object> turnPage(List<? extends Object> ls, int totalNum) {
        List<Object> retLs = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        try {
            if (null != ls) {
                int realTotalNum = ls.size() > totalNum ? ls.size() : totalNum;
                map.put("totalNum", String.format("%d", realTotalNum));
                retLs.add(map);

                ls.forEach(obj -> retLs.add(obj));
            } else {
                map.put("errorCode", "ctpUtil1000");
                retLs.add(map);
            }
        } catch (Exception e) {
            map.put("errorCode", "ctpUtil1000");
            retLs.add(map);
        }

        return retLs;
    }

    /**
     * json字符串转换JsonObjcet对象
     * <p>
     * 返回为Optional，需要判断是否为null
     *
     * @param json 待转换的json格式的字符串
     * @return Optional<JsonObject>
     */
    public static Optional<JsonObject> parseToJson(String json) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = null;

        try {
            if (StringUtils.isNotEmpty(json)) {
                jsonObj = parser.parse(json).getAsJsonObject();
            }
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }

        return Optional.ofNullable(jsonObj);
    }

    /**
     * json字符串转换为Map对象
     * <p>
     * 返回的Map的key为String类型
     *
     * @param json 待转换的json格式的字符串
     * @return key:val 转换后的Map
     */
    public static Map<String, Object> parseToMap(String json) {
        Map<String, Object> map = null;
        Optional<JsonObject> rets = UtilTools.parseToJson(json);

        if (rets.isPresent()) {
            map = UtilTools.parseToMap(rets.get());
        } else {
            map = new HashMap<>();
        }

        return map;
    }

    /**
     * JsonObject转换为Map
     * <p>
     * 返回的Map的key为String类型
     *
     * @param json 待转换的JsonObject
     * @return key:val 转换后的Map
     */
    public static Map<String, Object> parseToMap(JsonObject json) {
        Map<String, Object> map = new HashMap<>();
        Set<Map.Entry<String, JsonElement>> entrySet = json.entrySet();
        Iterator<Map.Entry<String, JsonElement>> iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, JsonElement> entry = iterator.next();
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            
            if (value instanceof JsonArray) {
                map.put(key, UtilTools.jsonToList((JsonArray) value));
            } else if (value instanceof JsonObject) {
                map.put(key, UtilTools.parseToMap((JsonObject) value));
            } else {
                map.put(key, value.isJsonNull() ? StringUtils.EMPTY : value.getAsString());
            }
        }

        return map;
    }

    /**
     * JsonArray转换为List
     * <p>
     * 其中List中的类型是Object类型
     *
     * @param jsonArray json数组
     * @return List
     */
    public static List<Object> jsonToList(JsonArray jsonArray) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Object val = jsonArray.get(i);
            if(val != null){
	            if (val instanceof JsonArray) {
	                list.add(UtilTools.jsonToList((JsonArray) val));
	            } else if (val instanceof JsonObject) {
	                list.add(UtilTools.parseToMap((JsonObject) val));
	            } else {
	                list.add(val.toString());
	            }
            }
        }

        return list;
    }
}
