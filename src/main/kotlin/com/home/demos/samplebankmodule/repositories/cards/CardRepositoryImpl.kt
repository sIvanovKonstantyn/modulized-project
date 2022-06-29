package com.home.demos.samplebankmodule.repositories.cards

import com.home.demos.samplebankmodule.model.Card
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.BeanListHandler
import org.apache.commons.dbutils.handlers.ScalarHandler

class CardRepositoryImpl(private val queryRunner: QueryRunner) : CardRepository {

    override fun findAllByClientId(clientId: Long): List<Card> {
        return queryRunner.dataSource.connection.use { connection ->
            queryRunner.query(
                    connection,
                    "SELECT * FROM cards WHERE client_id=",
                    BeanListHandler(Card::class.java),
                    clientId
            )
        }
    }

    override fun save(card: Card): Card {
        return queryRunner.dataSource.connection.use { connection ->
            val id = queryRunner.insert(
                    connection,
                    "INSERT INTO cards (title, clientId, sum) VALUES(,,)",
                    ScalarHandler<Long>(),
                    card.title, card.clientId, card.sum
            )
            Card(id, card.title, card.clientId, card.sum);
        }
    }
}


