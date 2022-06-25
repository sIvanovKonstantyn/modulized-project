package com.home.demos.samplebankmodule.config;

import com.home.demos.samplebankmodule.repositories.cards.CardRepository;
import com.home.demos.samplebankmodule.repositories.cards.impl.CardRepositoryImpl;
import com.home.demos.samplebankmodule.repositories.clients.ClientRepository;
import com.home.demos.samplebankmodule.repositories.clients.impl.ClientRepositoryImpl;
import com.home.demos.samplebankmodule.repositories.payments.PaymentRepository;
import com.home.demos.samplebankmodule.repositories.payments.impl.PaymentRepositoryImpl;
import org.apache.commons.dbutils.QueryRunner;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class SampleBankModuleConfiguration {

    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(
                    SampleBankModuleConfiguration.class.getClassLoader().getResourceAsStream("application.properties")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PaymentRepository paymentRepository() {
        return new PaymentRepositoryImpl(queryRunner());
    }

    public static CardRepository cardRepository() {
        return new CardRepositoryImpl(queryRunner());
    }

    public static ClientRepository clientRepository() {
        return new ClientRepositoryImpl(queryRunner());
    }

    public static DataSource dataSource() {

        try {
            Class.forName(PROPERTIES.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        JdbcDataSource jdbcDataSource = new JdbcDataSource();

        jdbcDataSource.setUrl(PROPERTIES.getProperty("db.url"));
        jdbcDataSource.setUser(PROPERTIES.getProperty("db.user"));
        jdbcDataSource.setUser(PROPERTIES.getProperty("db.password"));

        return jdbcDataSource;
    }

    public static QueryRunner queryRunner() {
        return new QueryRunner(dataSource());
    }
}
