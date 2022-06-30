package com.home.demos.samplebankmodule.model

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.asyncRepository
import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.paymentRepository

class Payment(
        val id: Long,
        val title: String,
        var clientId: Long,
        val target: String,
        val sum: Long
) {

    fun create(): TransactionId {
        return asyncRepository().process(this) { paymentRepository().save(this) }
    }

    fun findAllLikeThis(): TransactionId {
        return asyncRepository().process(this) { paymentRepository().findAllByClientId(clientId) }
    }
}
