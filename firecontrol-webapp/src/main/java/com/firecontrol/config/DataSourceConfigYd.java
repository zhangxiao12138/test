package com.firecontrol.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 *  * druid连接池配置-- 云大的库
 * Created by mariry on 2019/11/7.
 */
@Configuration
@MapperScan(basePackages = "com.firecontrol.mapper.ydmapper", sqlSessionFactoryRef = "sqlSessionFactoryYd", sqlSessionTemplateRef = "sqlSessionTemplateYd")
public class DataSourceConfigYd {
    private static final Logger log = LoggerFactory.getLogger(DataSourceConfigYd.class);
    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private Environment env;


    @Bean(name = "dataSourceYd")
    @ConfigurationProperties(prefix = "spring.datasource.smartcity")
    public DataSource dataSourceJmuMp() {
        log.info("here in DataSourceConfigYd dataSourceYd: /n" +
                "url:"+configProperties.getYdUrl() +
                ",/nuserName:" + configProperties.getYdUsername() +
                ",/n ");
        log.info("###################### create yd datasource entity #############################");
        DataSource ds = DataSourceBuilder.create().type(DruidDataSource.class).build();
        return ds;

    }

    @Bean(name = "sqlSessionFactoryYd")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("dataSourceYd") DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);

        //fb.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap/ydmapper/*Mapper.xml"));
        log.info("############################  created accessiot dd SessionFactory! #########################");
        return fb.getObject();
    }

    @Bean(name = "transactionManagerDataSourceYd")
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSourceYd") DataSource ds) throws Exception {
        return new DataSourceTransactionManager(ds);
    }


    @Bean("sqlSessionTemplateYd")
    @Primary
    public SqlSessionTemplate createMidWareSqlSessionTemplate(@Qualifier("sqlSessionFactoryYd") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }





}
