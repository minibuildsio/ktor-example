package io.minibuilds

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.minibuilds.core.Location
import io.minibuilds.core.Place
import io.minibuilds.core.PlaceType
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `get places returns places from the places service`() = testApplication {
        client.get("/places") {
            headers {
                contentType(ContentType.Application.Json)
            }
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(DEFAULT_PLACES, Json.decodeFromString(bodyAsText()))
        }
    }

    @Test
    fun `get places passes query params to services`() = testApplication {
        client.get("/places?name=tin") {
            headers {
                contentType(ContentType.Application.Json)
            }
        }.apply {
            val places: List<Place> = Json.decodeFromString(bodyAsText())
            assertEquals(1, places.size)
            assertEquals("Tintagel Castle", places[0].name)
        }
    }

    companion object {
        private val DEFAULT_PLACES = listOf(
            Place(1, "Mottisfont Abbey", Location(51.041430 , -1.5349589), PlaceType.NATION_TRUST),
            Place(2, "Tintagel Castle", Location(50.662774 , -4.7506144), PlaceType.ENGLISH_HERITAGE),
            Place(3, "Roman Baths", Location(51.3809, -2.3595), PlaceType.OTHER)
        )
    }
}
