package com.home.demos.samplebankmodule.repositories.payments.impl;

import com.home.demos.samplebankmodule.model.Payment;
import com.home.demos.samplebankmodule.repositories.payments.PaymentRepository;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository {

    private final QueryRunner queryRunner;

    public PaymentRepositoryImpl(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    @Override
    public List<Payment> findAllByClientId(Long clientId) {
        try (Connection connection = queryRunner.getDataSource().getConnection()) {
            return queryRunner.query(
                    connection,
                    "SELECT * FROM payments WHERE client_id=?",
                    new BeanListHandler<>(Payment.class),
                    clientId
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment save(Payment payment) {
        try (Connection connection = queryRunner.getDataSource().getConnection()) {
            Long id = queryRunner.insert(
                    connection,
                    "INSERT INTO payments (title, clientId, target, sum) VALUES(?,?,?,?)",
                    new ScalarHandler<>(),
                    payment.getTitle(), payment.getClientId(), payment.getTarget(), payment.getSum()
            );

            payment.setId(id);

            return payment;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
