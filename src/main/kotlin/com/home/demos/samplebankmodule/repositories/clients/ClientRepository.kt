package com.home.demos.samplebankmodule.repositories.clients

import com.home.demos.samplebankmodule.model.Client
import com.home.demos.samplebankmodule.repositories.Repository

sealed interface ClientRepository : Repository {
    fun save(client: Client): Client
    fun findAll(): List<Client>
}
