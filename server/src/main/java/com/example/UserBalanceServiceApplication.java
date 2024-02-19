package com.example;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.example.repository")
@ComponentScan(basePackages = "com.example")
@PropertySource("classpath:application.properties")
@SpringBootApplication
public class UserBalanceServiceApplication {

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("db.driver.name")));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.user"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Установка свойства для автоматического создания и удаления схемы базы данных
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "drop");
        properties.setProperty("hibernate.hbm2ddl.auto", "create"); // Используем create-drop
        em.setJpaProperties(properties);

        return em;
    }



}
