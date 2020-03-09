package io.github.arkanjoms.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "io.github.arkanjoms.store.repository",
        entityManagerFactoryRef = "storeEntityManager",
        transactionManagerRef = "storeTransactionManager"
)
public class StoreDatasourceConfig {

    @Bean(name = "storeDatasourceProperties")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.store")
    public DataSourceProperties storeDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource storeDatasource() {
        DataSourceProperties properties = storeDatasourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }

    @Bean
    @Autowired
    @Primary
    public JdbcTemplate storeJdbcTemplate(@Qualifier("storeDatasource") DataSource datasource) {
        return new JdbcTemplate(datasource);
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean storeEntityManager(EntityManagerFactoryBuilder builder, @Qualifier("storeDatasource") DataSource datasource, Environment env) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.default_schema", "store");

        return builder
                .dataSource(datasource)
                .properties(properties)
                .packages("io.github.arkanjoms.store.model")
                .persistenceUnit("storePU")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager storeTransactionManager(@Qualifier("storeEntityManager") EntityManagerFactory storeEntityManager) {
        return new JpaTransactionManager(storeEntityManager);
    }
}
