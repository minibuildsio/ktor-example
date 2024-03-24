package io.minibuilds.core

import kotlinx.serialization.Serializable

@Serializable
data class Place(
    val id: Int,
    val name: String,
    val location: Location,
    val type: PlaceType
)

@Serializable
data class Location(
    val lat: Double,
    val lng: Double
)

enum class PlaceType {
    NATION_TRUST,
    ENGLISH_HERITAGE,
    OTHER
}
