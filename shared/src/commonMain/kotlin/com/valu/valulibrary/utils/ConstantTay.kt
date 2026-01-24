package com.valu.valulibrary.utils

import ValuLibrary.shared.BuildConfig

fun getUrlAppTay() = if(isDebugBuild() ) BuildConfig.BASE_URL_SERVICE_DEV else BuildConfig.BASE_URL_SERVICE
