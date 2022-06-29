package com.home.demos.samplebankmodule.rest.clients

import spark.Route

interface ClientResource {
    fun create(): Route
    fun findAll(): Route
}
