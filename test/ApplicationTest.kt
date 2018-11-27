package ca.rjreid

import com.google.gson.Gson
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    val gson = Gson()

    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Welcome", response.content)
            }
        }
    }

    @Test
    fun testGetStatus() {
        val doorStatusResponse = DoorStatusResponse(ResponseStatus.SUCCESS, DoorStatus.CLOSED)

        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/api/door/status").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(gson.toJson(doorStatusResponse), response.content)
            }
        }
    }

    @Test
    fun testOpenDoor() {
        val doorStatusResponse = DoorStatusResponse(ResponseStatus.SUCCESS, DoorStatus.OPEN)

        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/api/door/open").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(gson.toJson(doorStatusResponse), response.content)
            }
        }
    }

    @Test
    fun testCloseDoor() {
        val doorStatusResponse = DoorStatusResponse(ResponseStatus.SUCCESS, DoorStatus.CLOSED)

        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/api/door/close").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(gson.toJson(doorStatusResponse), response.content)
            }
        }
    }
}
