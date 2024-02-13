package com.iTMS.iTMS.myDB.configurations;

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
        basePackages = "com.iTMS.iTMS.myDB.repositories",
        entityManagerFactoryRef = "postgreSQLEntityManager",
        transactionManagerRef = "postgreSQLTaskTransactionManager"
)
public class PostgreSQLConfig {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean postgreSQLEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgreSQLTaskDataSource());   // подключаем DataSource
        em.setPackagesToScan(new String[]{"com.iTMS.iTMS.myDB.models"});  // указываем пакет где находится Entity
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); // подключаем hibernate
        HashMap<String, Object> properties = new HashMap<>();      //задаем свойства hibernate больше инфо тут https://javarush.com/quests/lectures/questhibernate.level09.lecture04
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.hibernate.show-sql"));
        properties.put("hibernate.format_sql", env.getProperty("spring.jpa.hibernate.format_sql"));
        properties.put("hibernate.dialect", env.getProperty("secondary.spring.jpa.hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource postgreSQLTaskDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("secondary.spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("secondary.spring.datasource.url"));
        dataSource.setUsername(env.getProperty("secondary.spring.datasource.username"));
        dataSource.setPassword(env.getProperty("secondary.spring.datasource.password"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager postgreSQLTaskTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgreSQLEntityManager().getObject());
        return transactionManager;
    }
}
