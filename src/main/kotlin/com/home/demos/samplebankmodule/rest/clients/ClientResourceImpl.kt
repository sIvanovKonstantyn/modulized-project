package com.home.demos.samplebankmodule.rest.clients

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.gson
import com.home.demos.samplebankmodule.model.Client
import com.home.demos.samplebankmodule.rest.clients.dto.CreateClientDto
import spark.Request
import spark.Response
import spark.Route
import java.util.*

class ClientResourceImpl : ClientResource {
    override fun create(): Route {
        return Route { request: Request, _: Response ->
            Optional.of(gson().fromJson(request.body(), CreateClientDto::class.java))
                    .map(CreateClientDto::toModel)
                    .map(Client::create)
                    .map<Any>(gson()::toJson)
                    .orElse("TODO: ERROR")
        }
    }

    override fun findAll(): Route {
        return Route { _: Request, _: Response ->
            Optional.of(Client(0, ""))
                    .map(Client::findAll)
                    .map<Any>(gson()::toJson)
                    .orElse("TODO: ERROR")
        }
    }
}

private fun CreateClientDto.toModel(): Client {
    return Client(0, this.name)
}
