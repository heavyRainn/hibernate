package com.epam.hiber.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam.hiber")
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
public class SpringConfig {

    private static final String HBM2DLL_PROPERTY = "hibernate.hbm2ddl.auto";
    private static final String DIALECT_PROPERTY = "hibernate.dialect";
    private static final String SHOW_SQL_PROPERTY = "hibernate.show_sql";
    private static final String LAZY_LOAD_PROPERTY = "hibernate.enable_lazy_load_no_trans";
    private static final String PACKAGES_TO_SCAN = "com.epam.hiber.entity";

    @Value("${db.driver.name}")
    private String driver;

    @Value("${db.username}")
    private String userName;

    @Value("${db.password}")
    private String password;

    @Value("${db.url}")
    private String url;

    @Value("${db.pool.initial}")
    private int initialSize;

    @Value("${db.pool.total}")
    private int maxActive;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2dll;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show.sql}")
    private String showSql;

    @Value("${hibernate.enable_lazy_load}")
    private String lazyLoad;

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);

        return dataSource;
    }

    @Bean
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

        sessionBuilder
                .scanPackages(PACKAGES_TO_SCAN)
                .setProperty(HBM2DLL_PROPERTY, hbm2dll)
                .setProperty(DIALECT_PROPERTY, dialect)
                .setProperty(SHOW_SQL_PROPERTY, showSql)
                .setProperty(LAZY_LOAD_PROPERTY, lazyLoad);

        return sessionBuilder.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

        return transactionManager;
    }

}
