package com.sdui.dashboard

import com.sdui.dashboard.model.DatabaseRepository
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.routing
import jsonFormatter
import models.ServerDrivenScreenResponse
import org.jetbrains.exposed.sql.Database

fun main() {
    embeddedServer(Netty, port = BuildKonfig.SERVER_PORT, host = BuildKonfig.SERVER_HOST, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureDatabases()
    val repository = DatabaseRepository()
    routing {
        get("/ping") {
            call.respondText("pong")
        }
        get("/screens/{id}") {
            val id = call.parameters["id"]?.uppercase().orEmpty()
            call.respond(repository.getScreen(id))
        }
        put("/screens/{id}") {
            val id = call.parameters["id"]?.uppercase().orEmpty()
            call.respond(repository.updateScreen(id, call.receive<ServerDrivenScreenResponse>()))
        }
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(jsonFormatter)
    }
}

fun Application.configureDatabases() {
    Database.connect(BuildKonfig.DB_URL, "org.sqlite.JDBC")
}