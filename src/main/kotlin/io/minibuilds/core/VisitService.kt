package io.minibuilds.core

import java.time.LocalDateTime

class VisitService {
    private val visits = mutableListOf<Visit>()

    fun addVisit(placeId: Int, visitDateTime: LocalDateTime) =
        Visit(visits.size, placeId, visitDateTime).let { visits.add(it) }

    fun getVisits() = visits
}
