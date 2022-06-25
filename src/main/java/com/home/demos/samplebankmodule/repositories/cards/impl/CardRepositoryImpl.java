package com.home.demos.samplebankmodule.repositories.cards.impl;

import com.home.demos.samplebankmodule.repositories.cards.CardRepository;
import com.home.demos.samplebankmodule.repositories.cards.entities.Card;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

@Repository
public class CardRepositoryImpl implements CardRepository {

    private final QueryRunner queryRunner;

    @Autowired
    public CardRepositoryImpl(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    @Override
    public List<Card> findAllByClientId(Long clientId) {

        try (Connection connection = queryRunner.getDataSource().getConnection()) {
            return queryRunner.query(
                    connection,
                    "SELECT * FROM cards WHERE client_id=?",
                    new BeanListHandler<>(Card.class),
                    clientId
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Card save(Card card) {
        try (Connection connection = queryRunner.getDataSource().getConnection()) {
            Long id = queryRunner.insert(
                    connection,
                    "INSERT INTO cards (title, clientId, sum) VALUES(?,?,?)",
                    new ScalarHandler<>(),
                    card.getTitle(), card.getClientId(), card.getSum()
            );

            card.setId(id);

            return card;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
