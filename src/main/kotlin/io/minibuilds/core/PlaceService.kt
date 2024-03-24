package io.minibuilds.core

class PlaceService {
    private val places = listOf(
        Place(1, "Mottisfont Abbey", Location(51.041430, -1.5349589), PlaceType.NATION_TRUST),
        Place(2, "Tintagel Castle", Location(50.662774, -4.7506144), PlaceType.ENGLISH_HERITAGE),
        Place(3, "Roman Baths", Location(51.3809, -2.3595), PlaceType.OTHER)
    )

    fun getPlaces(name: String?): List<Place> =
        places.filter { place ->
            name?.let { it in place.name.lowercase() } ?: true
        }
}
