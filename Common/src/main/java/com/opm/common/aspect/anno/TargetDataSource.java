package com.opm.common.aspect.anno;

import java.lang.annotation.*;

/**
 * Created by kfzx-liuyz1 on 2016/11/29.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
