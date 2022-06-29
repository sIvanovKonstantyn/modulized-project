package com.home.demos.samplebankmodule.config

import com.google.gson.Gson
import com.home.demos.samplebankmodule.repositories.cards.CardRepository
import com.home.demos.samplebankmodule.repositories.cards.CardRepositoryImpl
import com.home.demos.samplebankmodule.repositories.clients.ClientRepository
import com.home.demos.samplebankmodule.repositories.clients.ClientRepositoryImpl
import com.home.demos.samplebankmodule.repositories.payments.PaymentRepository
import com.home.demos.samplebankmodule.repositories.payments.PaymentRepositoryImpl
import org.apache.commons.dbutils.QueryRunner
import org.h2.jdbcx.JdbcDataSource
import java.util.*
import javax.sql.DataSource

sealed class SampleBankModuleConfiguration {
    companion object {
        private val properties = Properties()
        private val gson = Gson()
        private val paymentRepository: PaymentRepository = PaymentRepositoryImpl(queryRunner())
        private val cardRepository: CardRepository = CardRepositoryImpl(queryRunner())
        private val clientRepository: ClientRepository = ClientRepositoryImpl(queryRunner())

        init {
            properties.load(
                    SampleBankModuleConfiguration::class.java.classLoader.getResourceAsStream("application.properties")
            )
        }

        fun gson(): Gson {
            return gson
        }

        fun paymentRepository(): PaymentRepository {
            return paymentRepository
        }

        fun cardRepository(): CardRepository {
            return cardRepository
        }

        fun clientRepository(): ClientRepository {
            return clientRepository
        }

        private fun dataSource(): DataSource {
            try {
                Class.forName(properties.getProperty("db.driver"))
            } catch (e: ClassNotFoundException) {
                throw RuntimeException(e)
            }
            val jdbcDataSource = JdbcDataSource()
            jdbcDataSource.setUrl(properties.getProperty("db.url"))
            jdbcDataSource.user = properties.getProperty("db.user")
            jdbcDataSource.user = properties.getProperty("db.password")
            return jdbcDataSource
        }

        private fun queryRunner(): QueryRunner {
            return QueryRunner(dataSource())
        }
    }
}
