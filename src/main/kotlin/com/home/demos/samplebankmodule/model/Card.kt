package com.home.demos.samplebankmodule.model

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.cardRepository

class Card(
        val id: Long,
        val title: String,
        var clientId: Long,
        val sum: Long
) {

    fun create(): Card {
        return cardRepository()
                .save(this)
    }

    fun findAllLikeThis(): List<Card> {
        return cardRepository()
                .findAllByClientId(clientId)
    }
}
