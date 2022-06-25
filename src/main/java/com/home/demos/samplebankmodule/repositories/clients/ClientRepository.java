package com.home.demos.samplebankmodule.repositories.clients;

import com.home.demos.samplebankmodule.repositories.clients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
