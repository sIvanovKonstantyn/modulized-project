package com.home.demos.samplebankmodule.repositories.clients.impl;

import com.home.demos.samplebankmodule.repositories.clients.ClientRepository;
import com.home.demos.samplebankmodule.repositories.clients.entities.Client;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.util.List;

@ApplicationScoped
public class ClientRepositoryImpl implements ClientRepository {

    private final QueryRunner queryRunner;

    @Inject
    public ClientRepositoryImpl(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    @Override
    public List<Client> findAll() {
        try (Connection connection = queryRunner.getDataSource().getConnection()) {
            return queryRunner.query(
                    connection,
                    "SELECT * FROM clients",
                    new BeanListHandler<>(Client.class)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client save(Client client) {
        try (Connection connection = queryRunner.getDataSource().getConnection()) {
            Long id = queryRunner.insert(
                    connection,
                    "INSERT INTO clients (name) VALUES(?)",
                    new ScalarHandler<>(),
                    client.getName()
            );

            client.setId(id);

            return client;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
