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
 * springboot����mybatis�Ļ������
 * 1����������Դ
 * 2������SqlSessionFactory
 */
@Configuration    //��ע��������spring�����ļ�
@MapperScan("com.opm.common.transaction.mapper")
public class MyBatisConfig {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MyBatisConfig.class);

    @Autowired
    private Environment env;

    /**
     * ��������Դ����SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
        LOGGER.debug("init sqlSessionFactory...");
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);//ָ������Դ(��������У����򱨴�)
        //�±������������*.xml�ļ�����������־ò��������Ҫʹ�õ�xml�ļ��Ļ���ֻ��ע��Ϳ��Ը㶨�����򲻼�
        //fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//ָ������
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//ָ��xml�ļ�λ��
        fb.setTypeHandlersPackage("com.opm.common.typeHandler");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //������õ�ʱ��ᱨ1111�Ĵ�
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        fb.setConfiguration(configuration);
        return fb.getObject();
    }

}