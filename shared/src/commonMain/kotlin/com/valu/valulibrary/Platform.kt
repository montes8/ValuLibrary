package com.valu.valulibrary

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform