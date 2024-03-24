package io.minibuilds.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.minibuilds.core.PlaceService

fun Application.configureRouting(placeService: PlaceService) {
    routing {
        get("/places") {
            val name = call.request.queryParameters["name"]
            call.respond(placeService.getPlaces(name))
        }
    }
}
