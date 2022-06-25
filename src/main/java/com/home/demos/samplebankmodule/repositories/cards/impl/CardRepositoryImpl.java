package com.home.demos.samplebankmodule.repositories.cards.impl;

import com.home.demos.samplebankmodule.repositories.cards.CardRepository;
import com.home.demos.samplebankmodule.repositories.cards.entities.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class CardRepositoryImpl implements CardRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CardRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Card> findAllByClientId(Long clientId) {
        return jdbcTemplate.query(
                "SELECT * FROM cards WHERE client_id=?",
                new BeanPropertyRowMapper<>(Card.class),
                clientId
        );
    }

    @Override
    @Transactional
    public Card save(Card card) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO cards (title, clientId, sum) VALUES(?,?,?)",
                    new String[]{"id"});
            statement.setString(1, card.getTitle());
            statement.setLong(2, card.getClientId());
            statement.setLong(3, card.getSum());
            return statement;
        }, keyHolder);
        card.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return card;
    }
}
