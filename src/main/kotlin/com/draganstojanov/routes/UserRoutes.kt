package com.draganstojanov.routes

import com.draganstojanov.models.User
import com.draganstojanov.models.userStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

https://ktor.io/docs/getting-started-ktor-client.html#new-project

fun Route.userRouting() {

    route(UserRoute.USER.value) {

        get {
            if (userStorage.isNotEmpty()) {
                call.respond(userStorage)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.OK)
            }
        }

        get(UserRoute.QUERY_ID.value) {
            val id = call.parameters[UserRoute.PARAM_ID.value] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )

            val user = userStorage.find { it.id == id.toLongOrNull() } ?: return@get call.respondText(
                "No customer with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(user)
        }

        post {
            val user = call.receive<User>()
            userStorage.add(user)
            call.respondText("User stored correctly", status = HttpStatusCode.Created)
        }

        delete(UserRoute.QUERY_ID.value) {
            val id = call.parameters[UserRoute.PARAM_ID.value] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (userStorage.removeIf { it.id == id.toLongOrNull() }) {
                call.respondText("User removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }

    }

}


private enum class UserRoute(val value: String) {
    USER("/user"),
    QUERY_ID("{id?}"),
    PARAM_ID("id")
}