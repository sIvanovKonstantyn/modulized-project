package com.home.demos.samplebankmodule.repositories.clients;

import com.home.demos.samplebankmodule.model.Client;

import java.util.List;

public interface ClientRepository {
    Client save(Client client);

    List<Client> findAll();
}
