package com.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by xiehui1956(@)gmail.com on 17-4-11.
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.mysql.cms")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcOperations")
    public JdbcTemplate getIgCmsDS(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource,true);
    }



}
