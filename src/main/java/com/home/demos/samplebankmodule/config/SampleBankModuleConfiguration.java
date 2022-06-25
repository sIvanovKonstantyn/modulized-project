package com.home.demos.samplebankmodule.config;

import org.apache.commons.dbutils.QueryRunner;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.DispatcherServlet;

import javax.sql.DataSource;

@Configuration
public class SampleBankModuleConfiguration {

    @Autowired
    private Environment environment;

    @Autowired
    private ApplicationContext context;

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DataSource dataSource() {

        try {
            Class.forName(environment.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        JdbcDataSource jdbcDataSource = new JdbcDataSource();

        jdbcDataSource.setUrl(environment.getProperty("db.url"));
        jdbcDataSource.setUser(environment.getProperty("db.user"));
        jdbcDataSource.setUser(environment.getProperty("db.password"));

        return jdbcDataSource;
    }

    @Bean
    public QueryRunner queryRunner() {
        System.out.println("BEANS:\n"
                + String.join("\n", context.getBeanDefinitionNames())
                + "...BEANS\n"
        );

        return new QueryRunner(dataSource());
    }
}
