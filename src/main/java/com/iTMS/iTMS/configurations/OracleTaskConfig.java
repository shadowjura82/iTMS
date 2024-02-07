package com.iTMS.iTMS.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = {"com.iTMS.iTMS.repositories"},
        entityManagerFactoryRef = "oracleTaskEntityManager",
        transactionManagerRef = "oracleTaskTransactionManager"
)
public class OracleTaskConfig {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean oracleTaskEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(oracleTaskDataSource());   // подключаем DataSource
        em.setPackagesToScan(new String[]{"com.iTMS.iTMS.models", "com.iTMS.iTMS.repositories"});  // указываем пакет где находится Entity
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); // подключаем hibernate
        HashMap<String, Object> properties = new HashMap<>();      //задаем свойства hibernate больше инфо тут https://javarush.com/quests/lectures/questhibernate.level09.lecture04
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.hibernate.show-sql"));
        properties.put("hibernate.format_sql", env.getProperty("spring.jpa.hibernate.format_sql"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    public DataSource oracleTaskDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager oracleTaskTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(oracleTaskEntityManager().getObject());
        return transactionManager;
    }
}