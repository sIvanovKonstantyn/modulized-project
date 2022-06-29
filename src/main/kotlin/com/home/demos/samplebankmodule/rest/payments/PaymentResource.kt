package com.home.demos.samplebankmodule.rest.payments

import spark.Route

interface PaymentResource {
    fun create(): Route
    fun findAllForClient(): Route
}
