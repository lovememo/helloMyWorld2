package com.opm.discover.support;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/12/23.
 */
public class MapUtils {
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null){
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }
}
