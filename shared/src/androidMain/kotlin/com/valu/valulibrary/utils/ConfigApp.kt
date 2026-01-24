package com.valu.valulibrary.utils

import ValuLibrary.shared.BuildConfig


actual fun isDebugBuild(): Boolean {
    return BuildConfig.DEBUG
}