package io.minibuilds.ui

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.minibuilds.core.PlaceService
import io.minibuilds.core.VisitService
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

fun Application.configureRouting(placeService: PlaceService, visitService: VisitService) {
    routing {
        get("/places") {
            val name = call.request.queryParameters["name"]
            call.respond(placeService.getPlaces(name))
        }

        get("/places/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val place = placeService.getPlace(id)
            if (place == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(place)
            }
        }

        get("/visits") {
            call.respond(visitService.getVisits())
        }

        post("/visits") {
            val visit = call.receive<VisitRequest>()
            call.respond(visitService.addVisit(visit.placeId, visit.visitDateTime))
        }
    }
}

@Serializable
data class VisitRequest(
    val placeId: Int,
    @Contextual
    val visitDateTime: LocalDateTime
)
