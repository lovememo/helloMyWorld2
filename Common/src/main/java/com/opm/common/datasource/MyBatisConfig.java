package com.opm.common.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Created by kfzx-liuyz1 on 2016/10/26.
 */

import ch.qos.logback.classic.Logger;

/**
 * springboot集成mybatis的基本入口
 * 1）创建数据源
 * 2）创建SqlSessionFactory
 */
@Configuration    //该注解类似于spring配置文件
@MapperScan("com.opm.common.transaction.mapper")
public class MyBatisConfig {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MyBatisConfig.class);

    @Autowired
    private Environment env;

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
        LOGGER.debug("init sqlSessionFactory...");
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);//指定数据源(这个必须有，否则报错)
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        //fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置
        fb.setTypeHandlersPackage("com.opm.common.typeHandler");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //避免空置的时候会报1111的错
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        fb.setConfiguration(configuration);
        return fb.getObject();
    }

}