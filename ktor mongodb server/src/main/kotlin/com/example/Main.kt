package com.example

import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080 ) {
        install(ContentNegotiation) {
            jackson()
        }

        routing {
            requestRoutes()
        }
    }.start(wait = true)
}