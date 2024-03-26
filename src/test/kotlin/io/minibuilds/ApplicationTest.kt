package io.minibuilds

import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import io.minibuilds.core.Location
import io.minibuilds.core.Place
import io.minibuilds.core.PlaceType
import io.minibuilds.core.Visit
import io.minibuilds.ui.VisitRequest
import io.minibuilds.ui.config.LocalDateTimeSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `get places returns places from the places service`() = testApplication {
        client.get("/places").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(DEFAULT_PLACES, Json.decodeFromString(bodyAsText()))
        }
    }

    @Test
    fun `get places passes query params to services`() = testApplication {
        client.get("/places?name=tin").apply {
            val places: List<Place> = Json.decodeFromString(bodyAsText())
            assertEquals(1, places.size)
            assertEquals("Tintagel Castle", places[0].name)
        }
    }

    @Test
    fun `post request to visits creates a visit`() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json(Json {
                    serializersModule = SerializersModule {
                        contextual(LocalDateTimeSerializer)
                    }
                })
            }
        }

        client.post("/visits") {
            contentType(ContentType.Application.Json)
            setBody(VisitRequest(10, DATETIME))
        }

        client.get("/visits").apply {
            val places: List<Visit> = body()
            assertEquals(1, places.size)
            assertEquals(10, places[0].placeId)
            assertEquals(DATETIME, places[0].visitDateTime)
        }
    }

    companion object {
        private val DEFAULT_PLACES = listOf(
            Place(1, "Mottisfont Abbey", Location(51.041430, -1.5349589), PlaceType.NATION_TRUST),
            Place(2, "Tintagel Castle", Location(50.662774, -4.7506144), PlaceType.ENGLISH_HERITAGE),
            Place(3, "Roman Baths", Location(51.3809, -2.3595), PlaceType.OTHER)
        )

        private val DATETIME = LocalDateTime.of(2024, 3, 25, 8, 30, 0)
    }
}
