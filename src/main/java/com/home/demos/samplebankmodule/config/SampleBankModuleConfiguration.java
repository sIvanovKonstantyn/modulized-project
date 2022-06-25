package com.home.demos.samplebankmodule.config;

import org.apache.commons.dbutils.QueryRunner;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.h2.jdbcx.JdbcDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import javax.ws.rs.Produces;

@ApplicationScoped
public class SampleBankModuleConfiguration {

    @ConfigProperty(name = "db.driver")
    private String driver;

    @ConfigProperty(name = "db.url")
    private String url;

    @ConfigProperty(name = "db.user")
    private String user;

    @ConfigProperty(name = "db.password")
    private String password;

    @Produces
    @ApplicationScoped
    public DataSource dataSource() {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        JdbcDataSource jdbcDataSource = new JdbcDataSource();

        jdbcDataSource.setUrl(url);
        jdbcDataSource.setUser(user);
        jdbcDataSource.setUser(password);

        return jdbcDataSource;
    }

    @Produces
    @ApplicationScoped
    public QueryRunner queryRunner() {
        return new QueryRunner(dataSource());
    }
}
