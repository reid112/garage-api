package ca.rjreid

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {}
    }

    routing {
        //TODO: Do something with this route
        get("/") {
            call.respond("Welcome")
        }

        //TODO: Implement this correctly
        get("/api/door/status") {
            call.respond(DoorStatusResponse(ResponseStatus.SUCCESS, DoorStatus.CLOSED))
        }

        //TODO: Implement this correctly
        post("/api/door/open") {
            // time how long the door takes to open and use a coroutine to delay response until door is open
            call.respond(DoorStatusResponse(ResponseStatus.SUCCESS, DoorStatus.OPEN))
        }

        //TODO: Implement this correctly
        post("/api/door/close") {
            // time how long the door takes to close and use a coroutine to delay response until door is closed
            call.respond(DoorStatusResponse(ResponseStatus.SUCCESS, DoorStatus.CLOSED))
        }
    }
}

