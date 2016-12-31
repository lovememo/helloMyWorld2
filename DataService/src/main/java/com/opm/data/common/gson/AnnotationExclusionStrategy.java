package com.opm.data.common.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.opm.data.common.annotation.GsonExclude;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * Created by kfzx-jinjf on 2016/12/15.
 */
public class AnnotationExclusionStrategy implements ExclusionStrategy {
    public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.getAnnotation(GsonExclude.class) != null;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(GsonExclude.class) != null;
    }
}