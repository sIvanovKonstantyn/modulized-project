package com.home.demos.samplebankmodule.model

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.asyncRepository
import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.cardRepository

class Card(
        val id: Long,
        val title: String,
        var clientId: Long,
        val sum: Long
) {

    fun create(): TransactionId {
        return asyncRepository().process(this) { cardRepository().save(this) }
    }

    fun findAllLikeThis(): TransactionId {
        return asyncRepository().process(this) { cardRepository().findAllByClientId(clientId) }
    }
}
