package me.jaden.config.autoconfig;

import com.zaxxer.hikari.HikariDataSource;
import me.jaden.config.EnableMyConfigurationProperties;
import me.jaden.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDatasourceProperties.class)
public class DataSourceConfig {
    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    @ConditionalOnMissingBean
    DataSource hikariDataSource(MyDatasourceProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName((properties.getDriverClassName()));
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUserName());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    @Bean
    @ConditionalOnMissingBean
    DataSource dataSource(MyDatasourceProperties properties) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUserName());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }
}
