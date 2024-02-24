package com.example;

import java.util.Properties;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
@SpringBootApplication
public class UserBalanceServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserBalanceServiceApplication.class, args);
  }

  @Autowired
  private Environment environment;

  @Bean
  public DataSource dataSource() {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
    config.setUsername("rhogoron");
    config.setPassword("rhogoron");
    config.setDriverClassName("org.postgresql.Driver");
    return new HikariDataSource(config);
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource);
    em.setPackagesToScan("com.example");

    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", "create");
    properties.setProperty("hibernate.show_sql", "true");
    em.setJpaProperties(properties);

    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

    return em;
  }
}
