package com.home.demos.samplebankmodule.repositories.payments

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration
import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.eventProducer
import com.home.demos.samplebankmodule.model.TransactionId
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.*

class AsyncRepository {

    private val topicName = "sample_bank_module_answers"

    fun <V, R> process(input: V, call: (input: V) -> R): TransactionId {
        val id = UUID.randomUUID().toString()
        GlobalScope.launch {
            try {
                val result = call.invoke(input)
                val jsonObject = SampleBankModuleConfiguration.gson().toJson(result)
                eventProducer().sendEvent(id, jsonObject)
            } catch (e: Exception) {
                println(e.message)
                eventProducer().sendEvent(id, "{\"errorMessage\": $e.message}")
            }
        }
        return TransactionId(id)
    }

    private fun KafkaProducer<String, String>.sendEvent(ticketId: String, jsonBody: String) {
        try {
            val producerRecord = ProducerRecord<String, String>(topicName, jsonBody)
            producerRecord.headers().add("ticketId", ticketId.toByteArray())

            this.send(producerRecord)
            this.flush()
        } catch (e: java.lang.Exception) {
            println(e.message)
        }
    }
}
