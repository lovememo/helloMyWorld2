package com.opm.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.opm.common.aspect.anno.TargetDataSource;
import com.opm.common.datasource.DynamicDataSourceContextHolder;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-liuyz1 on 2016/11/29.
 */

@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DynamicDataSourceAspect.class);


    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
        String dsId = ds.name();
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            LOGGER.error("数据源[{}]不存在，使用默认数据源 > {}", ds.name(), point.getSignature());
        } else {
            LOGGER.debug("Use DataSource : {} > {}", ds.name(), point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(ds.name());
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
        LOGGER.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}
