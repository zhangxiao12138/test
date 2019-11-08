package com.firecontrol.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * druid连接池配置-- 安全应急的库
 * Created by mariry on 2019/6/21.
 */
@Configuration
@MapperScan(basePackages = "com.firecontrol.mapper.iotmapper", sqlSessionFactoryRef = "baseSqlSessionFactory", sqlSessionTemplateRef = "baseSqlSessionTemplate")
public class DataSourceConfigJumMp {
    private static final Logger log = LoggerFactory.getLogger(DataSourceConfigJumMp.class);

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private Environment env;

    @Bean(name = "baseDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.iotaccess")
    public DataSource dataSourceJmuMp() {
        log.info("here in DataSourceConfigJumMp dataSourceJmuMp: /n" +
                "url:"+configProperties.getJmuMpUrl() +
        ",/nuserName:" + configProperties.getJmuMpUsername()+
        ",/n ");

        // 使用Druid连接池
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(configProperties.getJmuMpUrl());
        ds.setUsername(configProperties.getJmuMpUsername());
        ds.setPassword(configProperties.getJmuMpPassword());
        log.info("###################### create accessiot datasource entity #############################");
        return ds;
    }

    @Bean(name = "baseSqlSessionFactory")
    @Primary
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("baseDataSource")DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);

        fb.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
//        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:sqlmap/iotmapper/*Mapper.xml"));
        log.info("############################  created accessiot db SessionFactory! #########################");
        return fb.getObject();
    }

    @Bean(name = "baseTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("baseDataSource")DataSource ds) throws Exception {
        return new DataSourceTransactionManager(ds);
    }

    @Bean("baseSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate createMidWareSqlSessionTemplate(@Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
