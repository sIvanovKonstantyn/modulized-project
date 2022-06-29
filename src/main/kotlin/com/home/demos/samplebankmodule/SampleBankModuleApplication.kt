package com.home.demos.samplebankmodule

import com.home.demos.samplebankmodule.rest.cards.CardResource
import com.home.demos.samplebankmodule.rest.cards.CardResourceImpl
import com.home.demos.samplebankmodule.rest.clients.ClientResource
import com.home.demos.samplebankmodule.rest.clients.ClientResourceImpl
import com.home.demos.samplebankmodule.rest.payments.PaymentResource
import com.home.demos.samplebankmodule.rest.payments.PaymentResourceImpl
import spark.Request
import spark.Response
import spark.Spark.*
import java.time.LocalDateTime

class SampleBankModuleApplication {
    companion object {
        var cardResource: CardResource = CardResourceImpl()
        val clientResource: ClientResource = ClientResourceImpl()
        val paymentResource: PaymentResource = PaymentResourceImpl()

        @JvmStatic
        fun main(args: Array<String>) {
            println(LocalDateTime.now().toString() + ": SPARK server starting")
            val start = System.currentTimeMillis()
            val port = 8080
            port(port)

            path("/") {
                path("/payments") {
                    post("", paymentResource.create())
                    get("/:clientId", paymentResource.findAllForClient())
                }
                path("/cards") {
                    post("", cardResource.create())
                    get("/:clientId", cardResource.findAllForClient())
                }
                path("/clients") {
                    post("", clientResource.create())
                    get("/:clientId", clientResource.findAll())
                }
            }

            exception(Exception::class.java) { exception: Exception, _: Request?, response: Response ->
                response.status(500)
                response.body(exception.message)
            }

            awaitInitialization()
            println(
                    LocalDateTime.now()
                            .toString() + ": SPARK server started in " + (System.currentTimeMillis().toDouble() - start.toDouble()) / 1000 + "s. "
                            + "on port: " + port
            )
        }
    }
}
