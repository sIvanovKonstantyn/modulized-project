package com.home.demos.samplebankmodule.repositories.clients.impl;

import com.home.demos.samplebankmodule.repositories.clients.ClientRepository;
import com.home.demos.samplebankmodule.repositories.clients.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Client> findAll() {
        return jdbcTemplate.queryForList(
                "SELECT * FROM clients",
                Client.class
        );
    }

    @Override
    @Transactional
    public Client save(Client client) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO clients (name) VALUES(?)",
                    new String[]{"id"});
            statement.setString(1, client.getName());
            return statement;
        }, keyHolder);
        client.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return client;
    }
}
