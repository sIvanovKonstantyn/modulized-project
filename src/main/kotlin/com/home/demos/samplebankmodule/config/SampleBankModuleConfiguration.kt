package com.home.demos.samplebankmodule.config

import com.google.gson.Gson
import com.home.demos.samplebankmodule.repositories.cards.CardRepository
import com.home.demos.samplebankmodule.repositories.cards.CardRepositoryImpl
import com.home.demos.samplebankmodule.repositories.clients.ClientRepository
import com.home.demos.samplebankmodule.repositories.clients.ClientRepositoryImpl
import com.home.demos.samplebankmodule.repositories.payments.AsyncRepository
import com.home.demos.samplebankmodule.repositories.payments.PaymentRepository
import com.home.demos.samplebankmodule.repositories.payments.PaymentRepositoryImpl
import org.apache.commons.dbutils.QueryRunner
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.h2.jdbcx.JdbcDataSource
import java.util.*
import javax.sql.DataSource


sealed class SampleBankModuleConfiguration {
    companion object {
        private val properties = kotlin.run {
            val properties = Properties()
            properties.load(
                    SampleBankModuleConfiguration::class.java.classLoader.getResourceAsStream("application.properties")
            )
            properties
        }
        private val producer = kotlin.run {
            val producerProperties = Properties()
            producerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getProperty("kafka.bootstrap-servers"))
            producerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
            producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)

            KafkaProducer<String, String>(producerProperties)
        }

        private val gson = Gson()
        private val paymentRepository: PaymentRepository = PaymentRepositoryImpl(queryRunner())
        private val cardRepository: CardRepository = CardRepositoryImpl(queryRunner())
        private val clientRepository: ClientRepository = ClientRepositoryImpl(queryRunner())
        private val asyncRepository: AsyncRepository = AsyncRepository()
        fun gson(): Gson {
            return gson
        }

        fun eventProducer(): KafkaProducer<String, String> {
            return producer
        }

        fun asyncRepository(): AsyncRepository {
            return asyncRepository
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
