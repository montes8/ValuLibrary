package com.valu.valulibrary

import android.os.Build
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.Logger

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()


actual val requestLogger: Logger
    get() = Logger.ANDROID