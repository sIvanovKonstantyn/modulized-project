package com.home.demos.samplebankmodule.rest.cards.dto

data class CreatedCardDto(
        var id: Long,
        var title: String,
        var clientId: Long,
        var sum: Long
)
