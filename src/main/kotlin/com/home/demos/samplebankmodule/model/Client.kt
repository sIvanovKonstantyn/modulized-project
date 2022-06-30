package com.home.demos.samplebankmodule.model

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.asyncRepository
import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.clientRepository

class Client(
        val id: Long,
        val name: String
) {

    fun create(): TransactionId {
        return asyncRepository().process(this) { clientRepository().save(this) }
    }

    fun findAll(): TransactionId {
        return asyncRepository().process(this) { clientRepository().findAll() }
    }
}
