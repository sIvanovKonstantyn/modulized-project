package com.home.demos.samplebankmodule.rest.cards

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.gson
import com.home.demos.samplebankmodule.model.Card
import com.home.demos.samplebankmodule.rest.cards.dto.CreateCardDto
import spark.Request
import spark.Response
import spark.Route
import java.util.*

class CardResourceImpl : CardResource {
    override fun create(): Route {
        return Route { request: Request, _: Response ->
            Optional.of(gson().fromJson(request.body(), CreateCardDto::class.java))
                    .map(CreateCardDto::toModel)
                    .map(Card::create)
                    .map(gson()::toJson)
                    .orElse("TODO: ERROR")
        }
    }

    override fun findAllForClient(): Route {
        return Route { request: Request, _: Response ->
            Optional.of(request.params(":clientId"))
                    .map(String::toLong)
                    .map { id: Long -> Card(id, "", 0, 0) }
                    .map(Card::findAllLikeThis)
                    .map(gson()::toJson)
                    .orElse("TODO: ERROR")
        }
    }
}

private fun CreateCardDto.toModel(): Card {
    return Card(0, this.title, this.clientId, 0)
}
