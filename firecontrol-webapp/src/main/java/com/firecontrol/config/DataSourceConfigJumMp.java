package com.firecontrol.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
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
 * druid连接池配置
 * Created by mariry on 2019/6/21.
 */
@Configuration
public class DataSourceConfigJumMp {
    private static final Logger log = LoggerFactory.getLogger(ConfigProperties.class);

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private Environment env;



    @Bean(name = "dataSource")
    /*@ConfigurationProperties(prefix = "spring.datasource.jmu-mp")*/
    @ConfigurationProperties(prefix = "spring.datasource.druid")
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

        return ds;
    }

    @Bean(name = "sqlSessionFactory")
    //public SqlSessionFactory createSqlSessionFactory(@Qualifier("jmuMp") DataSource ds) throws Exception {
    public SqlSessionFactory createSqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);

        fb.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));

        return fb.getObject();
    }

    //@Bean(name = "transactionManagerJmuMp")
    @Primary
    //public DataSourceTransactionManager transactionManager(@Qualifier("jmuMp") DataSource ds) throws Exception {
    public DataSourceTransactionManager transactionManager(DataSource ds) throws Exception {
        return new DataSourceTransactionManager(ds);
    }

}
