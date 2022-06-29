package com.home.demos.samplebankmodule.model

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration

class Client(
        val id: Long,
        val name: String
) {

    fun create(): Client {
        return SampleBankModuleConfiguration
                .clientRepository()
                .save(this)
    }

    fun findAll(): List<Client> {
        return SampleBankModuleConfiguration
                .clientRepository()
                .findAll()
    }
}
