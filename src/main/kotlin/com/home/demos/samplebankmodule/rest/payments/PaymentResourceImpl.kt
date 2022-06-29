package com.home.demos.samplebankmodule.rest.payments

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration.Companion.gson
import com.home.demos.samplebankmodule.model.Payment
import com.home.demos.samplebankmodule.rest.payments.dto.CreatePaymentDto
import com.home.demos.samplebankmodule.rest.payments.dto.CreatedPaymentDto
import spark.Request
import spark.Response
import spark.Route
import java.util.*
import java.util.stream.Collectors

class PaymentResourceImpl : PaymentResource {
    override fun create(): Route {
        return Route { request: Request, _: Response ->
            Optional.of(gson().fromJson(request.body(), CreatePaymentDto::class.java))
                    .map(CreatePaymentDto::toModel)
                    .map(Payment::create)
                    .map(Payment::toCreatedPaymentDto)
                    .map(gson()::toJson)
                    .orElse("TODO: ERROR")
        }
    }

    override fun findAllForClient(): Route {
        return Route { request: Request, _: Response ->
            gson().toJson(
                    Optional.of(request.params(":clientId"))
                            .map(String::toLong)
                            .map { id: Long -> Payment(id, "", 0, "", 0) }
                            .map(Payment::findAllLikeThis)
                            .orElse(emptyList())
                            .stream()
                            .map(Payment::toCreatedPaymentDto)
                            .collect(Collectors.toList())
            )
        }
    }
}

private fun Payment.toCreatedPaymentDto(): CreatedPaymentDto {
    return CreatedPaymentDto(this.id, this.title, this.clientId, this.target, this.sum)
}

private fun CreatePaymentDto.toModel(): Payment {
    return Payment(0, this.title, this.clientId, this.target, this.sum)
}
