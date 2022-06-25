package com.home.demos.samplebankmodule.repositories.payments.impl;

import com.home.demos.samplebankmodule.repositories.payments.PaymentRepository;
import com.home.demos.samplebankmodule.repositories.payments.entities.Payment;
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
public class PaymentRepositoryImpl implements PaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PaymentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Payment> findAllByClientId(Long clientId) {
        return jdbcTemplate.query(
                "SELECT * FROM payments WHERE client_id=?",
                new BeanPropertyRowMapper<>(Payment.class),
                clientId
        );
    }

    @Override
    @Transactional
    public Payment save(Payment payment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO payments (title, clientId, target, sum) VALUES(?,?,?,?)",
                    new String[]{"id"});
            statement.setString(1, payment.getTitle());
            statement.setLong(2, payment.getClientId());
            statement.setString(3, payment.getTarget());
            statement.setLong(4, payment.getSum());
            return statement;
        }, keyHolder);
        payment.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return payment;
    }
}
