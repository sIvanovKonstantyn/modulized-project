package com.home.demos.samplebankmodule.model

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.paymentRepository

class Payment(
        val id: Long,
        val title: String,
        var clientId: Long,
        val target: String,
        val sum: Long
) {

    fun create(): Payment {
        return paymentRepository()
                .save(this)
    }

    fun findAllLikeThis(): List<Payment> {
        return paymentRepository()
                .findAllByClientId(clientId)
    }
}
