package com.home.demos.samplebankmodule.repositories.clients

import com.home.demos.samplebankmodule.model.Client
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.BeanListHandler
import org.apache.commons.dbutils.handlers.ScalarHandler

class ClientRepositoryImpl(private val queryRunner: QueryRunner) : ClientRepository {

    override fun findAll(): List<Client> {
        return queryRunner.dataSource.connection.use { connection ->
            queryRunner.query(
                    connection,
                    "SELECT * FROM clients",
                    BeanListHandler(Client::class.java)
            )
        }
    }

    override fun save(client: Client): Client {
        return queryRunner.dataSource.connection.use { connection ->
            val id = queryRunner.insert(
                    connection,
                    "INSERT INTO clients (name) VALUES()",
                    ScalarHandler<Long>(),

                    )
            Client(id, client.name)
        }
    }
}
