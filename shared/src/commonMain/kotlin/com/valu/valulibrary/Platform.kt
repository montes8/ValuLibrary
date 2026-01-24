package com.valu.valulibrary

import io.ktor.client.plugins.logging.Logger

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect val requestLogger: Logger