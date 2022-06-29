package com.home.demos.samplebankmodule.repositories.payments

import com.home.demos.samplebankmodule.model.Payment
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.BeanListHandler
import org.apache.commons.dbutils.handlers.ScalarHandler

class PaymentRepositoryImpl(private val queryRunner: QueryRunner) : PaymentRepository {

    override fun findAllByClientId(clientId: Long): List<Payment> {
        return queryRunner.dataSource.connection.use { connection ->
            queryRunner.query(
                    connection,
                    "SELECT * FROM payments WHERE client_id=",
                    BeanListHandler(Payment::class.java),
                    clientId
            )
        }
    }

    override fun save(payment: Payment): Payment {
        return queryRunner.dataSource.connection.use { connection ->
            val id = queryRunner.insert(
                    connection,
                    "INSERT INTO payments (title, clientId, target, sum) VALUES(,,,)",
                    ScalarHandler<Long>(),
                    payment.title, payment.clientId, payment.target, payment.sum
            )
            Payment(id, payment.title, payment.clientId, payment.target, payment.sum)
        }
    }
}
