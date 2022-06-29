package com.home.demos.samplebankmodule.rest.clients

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.gson
import com.home.demos.samplebankmodule.model.Client
import com.home.demos.samplebankmodule.rest.clients.dto.CreateClientDto
import com.home.demos.samplebankmodule.rest.clients.dto.CreatedClientDto
import spark.Request
import spark.Response
import spark.Route
import java.util.*
import java.util.stream.Collectors

class ClientResourceImpl : ClientResource {
    override fun create(): Route {
        return Route { request: Request, _: Response ->
            Optional.of(gson().fromJson(request.body(), CreateClientDto::class.java))
                    .map(CreateClientDto::toModel)
                    .map(Client::create)
                    .map(Client::toCreatedClientDto)
                    .map<Any>(gson()::toJson)
                    .orElse("TODO: ERROR")
        }
    }

    override fun findAll(): Route {
        return Route { _: Request, _: Response ->
            gson().toJson(
                    Optional.of(Client(0, ""))
                            .map(Client::findAll)
                            .orElse(emptyList())
                            .stream()
                            .map(Client::toCreatedClientDto)
                            .collect(Collectors.toList())
            )
        }
    }
}

private fun Client.toCreatedClientDto(): CreatedClientDto {
    return CreatedClientDto(this.id, this.name)
}

private fun CreateClientDto.toModel(): Client {
    return Client(0, this.name)
}
