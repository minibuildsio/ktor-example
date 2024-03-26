package io.minibuilds

import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.minibuilds.core.PlaceService
import io.minibuilds.core.VisitService
import io.minibuilds.ui.configureRouting
import io.minibuilds.ui.config.configureSerialization

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting(PlaceService(), VisitService())
}
