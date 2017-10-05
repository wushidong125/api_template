package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by shidong.wu on 2017/8/23.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryJpa",
                        transactionManagerRef = "transactionManagerJpa",
                        basePackages = {"com.spring.dao"})

public class JpaSourceManager {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    @Bean(name = "entityManagerJpa")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryBean(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryJpa")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .properties(getVendorProperties(dataSource))
                .packages("com.spring.entity")
                .persistenceUnit("jpaPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean("transactionManagerJpa")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
    }
}
