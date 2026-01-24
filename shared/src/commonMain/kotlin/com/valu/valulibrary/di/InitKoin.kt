package com.valu.valulibrary.di

import com.valu.valulibrary.manager.configModuleDB
import com.valu.valulibrary.manager.platformModuleDataStore
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            appModule,networkModule,dbModule(),configModuleDB(),platformModuleDataStore()
        )
    }