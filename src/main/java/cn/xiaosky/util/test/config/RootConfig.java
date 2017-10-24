/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package cn.xiaosky.util.test.config;

import cn.xiaosky.util.test.main.AppMainRun;
import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author wb-tby290972
 * @version $Id: RootConfig.java, v 0.1 2017年10月24日 10:28 wb-tby290972 Exp $
 */
@Configuration
@PropertySource({"classpath:app.properties","classpath:db.properties"})
@MapperScan("cn.xiaosky.util.test.dao")
@ComponentScan(basePackages = "cn.xiaosky.util.test",excludeFilters =@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Configuration.class))
@EnableTransactionManagement(proxyTargetClass = true)
public class RootConfig {
    @Autowired private Environment evn;

    @Bean
    public AppProperties appProperties(){
        AppProperties appProperties = new AppProperties();
        appProperties.setName(evn.getProperty("app.name"));
        appProperties.setVersion(evn.getProperty("app.version"));
        return appProperties;
    }
    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setUrl(evn.getProperty("db.url"));
        dataSource.setDriverClassName(evn.getProperty("db.driver"));
        dataSource.setUsername(evn.getProperty("db.user"));
        dataSource.setPassword(evn.getProperty("db.password"));
        dataSource.setInitialSize(Integer.parseInt(evn.getProperty("db.initialSize")));
        dataSource.setMaxActive(Integer.parseInt(evn.getProperty("db.maxActive")));
        dataSource.setMaxIdle(Integer.parseInt(evn.getProperty("db.maxIdle")));
        dataSource.setMinIdle(Integer.parseInt(evn.getProperty("db.minIdle")));
        dataSource.setMaxWait(Integer.parseInt(evn.getProperty("db.maxWait")));
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader()).getResources("classpath:mapper/*.xml"));
        return  sqlSessionFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}