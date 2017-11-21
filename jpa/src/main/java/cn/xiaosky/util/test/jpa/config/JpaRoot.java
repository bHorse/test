package cn.xiaosky.util.test.jpa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(name = "db",value = "classpath:db.properties")
@EnableTransactionManagement(proxyTargetClass = true)
public class JpaRoot {

    private Logger logger= LoggerFactory.getLogger(JpaRoot.class);
    @Bean
    public DataSource dataSource(Environment environment) throws PropertyVetoException {
        org.apache.tomcat.jdbc.pool.DataSource dataSource=new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUsername(environment.getProperty("db.user"));
        dataSource.setPassword(environment.getProperty("db.password"));
        dataSource.setInitialSize(Integer.parseInt(environment.getProperty("db.initialSize")));
        dataSource.setMaxActive(Integer.parseInt(environment.getProperty("db.maxActive")));
        dataSource.setMaxIdle(Integer.parseInt(environment.getProperty("db.maxIdle")));
        dataSource.setMinIdle(Integer.parseInt(environment.getProperty("db.minIdle")));
        dataSource.setMaxWait(Integer.parseInt(environment.getProperty("db.maxWait")));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb=new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("cn.xiaosky.util.test.jpa.entity");

        emfb.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE); //// TODO: 2017/11/21 如果不开启这个可以缓存吗?
        Map<String,Object> jpaProperties=new HashMap<>();
        jpaProperties.put("hibernate.format_sql",false);
        //jpaProperties.put("hibernate.cache.use_query_cache",true); //查询缓存
        //jpaProperties.put("hibernate.cache.user_second_level_cache",true);
        //jpaProperties.put("hibernate.cache.region.factory_class","org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
        //jpaProperties.put("net.sf.ehcache.configurationResourceName","ehcache.xml");
        //jpaProperties.put("hibernate.generate_statistics",true); 统计缓存状态,测试的时候使用
        emfb.setJpaPropertyMap(jpaProperties);

        return emfb;
    }
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        return adapter;
    }
}
