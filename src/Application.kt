package ca.rjreid

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.gson.gson
import io.ktor.html.respondHtml
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import kotlinx.html.*
import java.text.DateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders)
    install(CORS) {
        maxAge = Duration.ofDays(1)
    }
    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(
            this@module.javaClass.classLoader,
            "templates"
        )
    }

    routing {
        get("/") {
            val actions = GarageRepo.getAll()
            call.respond(
                FreeMarkerContent(
                    "actions.ftl",
                    mapOf("actions" to actions))
            )
        }

        //TODO: Implement this correctly
        get("/api/door/status") {
            call.respond(DoorStatusResponse(ResponseStatus.SUCCESS, DoorStatus.CLOSED))
        }

        //TODO: Implement this correctly
        post("/api/door/open") {
            // time how long the door takes to open and use a coroutine to delay response until door is open
            GarageRepo.add(DoorAction(-1, 12, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)))
            call.respond(DoorStatusResponse(ResponseStatus.SUCCESS, DoorStatus.OPEN))
        }

        //TODO: Implement this correctly
        post("/api/door/close") {
            // time how long the door takes to close and use a coroutine to delay response until door is closed
            GarageRepo.add(DoorAction(-1, 12, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)))
            call.respond(DoorStatusResponse(ResponseStatus.SUCCESS, DoorStatus.CLOSED))
        }
    }
}

