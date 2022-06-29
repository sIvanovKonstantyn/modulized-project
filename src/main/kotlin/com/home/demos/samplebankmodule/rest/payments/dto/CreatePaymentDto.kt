package com.home.demos.samplebankmodule.rest.payments.dto

data class CreatePaymentDto(
        var title: String,
        var clientId: Long,
        var target: String,
        var sum: Long
)
