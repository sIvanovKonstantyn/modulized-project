package com.home.demos.samplebankmodule.rest.payments.dto

data class CreatedPaymentDto(
        var id: Long,
        var title: String,
        var clientId: Long,
        var target: String,
        var sum: Long
)
