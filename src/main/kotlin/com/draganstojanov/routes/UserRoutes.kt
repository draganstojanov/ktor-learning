package com.draganstojanov.routes

import com.draganstojanov.models.userStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting() {
    route(UserRoute.USER.value) {
        get {
            if (userStorage.isNotEmpty()) {
                call.respond(userStorage)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.OK)
            }
        }
    }
    get(UserRoute.QUERY_ID.value) {
        val id = call.parameters[UserRoute.PARAM_ID.value] ?: return@get call.respondText(
            "Missing id",
            status = HttpStatusCode.BadRequest
        )
        val user = userStorage.find { it.id == id } ?: return@get call.respondText(
                "No customer with id $id",
                status = HttpStatusCode.NotFound
            )
        call.respond(user)
    }
    post {

    }
    delete(UserRoute.QUERY_ID.value) {

    }
}

https://ktor.io/docs/creating-http-apis.html#customer_routing

private enum class UserRoute(val value: String) {
    USER("/user"),
    QUERY_ID("{id?}"),
    PARAM_ID("id")
}