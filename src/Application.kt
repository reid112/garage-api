package ca.rjreid

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*

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
            call.respond(Response(ResponseStatus.SUCCESS))
        }

        get("/api/door/status") {

        }

        post("/api/door/open") {

        }

        post("/api/door/close") {

        }
    }
}

