package com.home.demos.samplebankmodule.rest.cards

import spark.Route

interface CardResource {
    fun create(): Route
    fun findAllForClient(): Route
}
