package com.valu.valulibrary.utils

import kotlin.experimental.ExperimentalNativeApi


@OptIn(ExperimentalNativeApi::class)
actual fun isDebugBuild(): Boolean {
    return Platform.isDebugBinary
}