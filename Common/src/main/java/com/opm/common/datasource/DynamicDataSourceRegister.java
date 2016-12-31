package com.opm.common.datasource;

import ch.qos.logback.classic.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/11/29.
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    private ConversionService conversionService = new DefaultConversionService();

    private PropertyValues dataSourcePropertyValues;

    private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";

    private DataSource defaultDataSource;

    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();

    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            Object type = dsMap.get("type");
            if (type == null)
                type = DATASOURCE_TYPE_DEFAULT;// Ĭ��DataSource

            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);

            String driverClassName = dsMap.get("driverClassName").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();
            String maxActive = dsMap.get("maxActive").toString();
            String initialSize = dsMap.get("initialSize").toString();
            String maxWait = dsMap.get("maxWait").toString();
            String minIdle = dsMap.get("minIdle").toString();

            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username).password(password).type(dataSourceType);
            DataSource dataSource = (DataSource) factory.build();

            dataSource.setMaxActive(Integer.parseInt(maxActive));
            dataSource.setInitialSize(Integer.parseInt(initialSize));
            dataSource.setMaxWait(Integer.parseInt(maxWait));
            dataSource.setMinIdle(Integer.parseInt(minIdle));

            return dataSource;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void setEnvironment(Environment env) {
        this.initDefaultDataSource(env);
        this.initCustomDataSources(env);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);

        LOGGER.info("Dynamic DataSource Registry");
    }

    private void initCustomDataSources(Environment env) {
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.jdbc.");
        String dsPrefixs = propertyResolver.getProperty("names");
        if(StringUtils.isNotEmpty(dsPrefixs)){

            for (String dsPrefix : dsPrefixs.split(",")) {
                Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
                DataSource ds = buildDataSource(dsMap);
                customDataSources.put(dsPrefix, ds);
                this.dataBinder(ds, env);
            }
        }

    }

    private void initDefaultDataSource(Environment env) {
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "default.jdbc.");
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("type", propertyResolver.getProperty("type"));
        dsMap.put("driverClassName", propertyResolver.getProperty("driverClassName"));
        dsMap.put("url", propertyResolver.getProperty("url"));
        dsMap.put("username", propertyResolver.getProperty("username"));
        dsMap.put("password", propertyResolver.getProperty("password"));
        dsMap.put("maxActive", propertyResolver.getProperty("maxActive"));
        dsMap.put("initialSize", propertyResolver.getProperty("initialSize"));
        dsMap.put("maxWait", propertyResolver.getProperty("maxWait"));
        dsMap.put("minIdle", propertyResolver.getProperty("minIdle"));

        defaultDataSource = buildDataSource(dsMap);

        this.dataBinder(defaultDataSource, env);
    }

    private void dataBinder(DataSource dataSource, Environment env){
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        //dataBinder.setValidator(new LocalValidatorFactory().run(this.applicationContext));
        dataBinder.setConversionService(conversionService);
        dataBinder.setIgnoreNestedProperties(false);//false
        dataBinder.setIgnoreInvalidFields(false);//false
        dataBinder.setIgnoreUnknownFields(true);//true
        if(dataSourcePropertyValues == null){
            Map<String, Object> rpr = new RelaxedPropertyResolver(env, "default.jdbc").getSubProperties(".");
            Map<String, Object> values = new HashMap<>(rpr);
            values.remove("type");
            values.remove("driverClassName");
            values.remove("url");
            values.remove("username");
            values.remove("password");
            values.remove("maxActive");
            values.remove("initialSize");
            values.remove("maxWait");
            values.remove("minIdle");

            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
    }

}
