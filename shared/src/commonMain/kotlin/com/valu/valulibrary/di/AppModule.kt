package com.valu.valulibrary.di

import com.valu.valulibrary.repository.TayService
import com.valu.valulibrary.repository.api.DataNetwork
import com.valu.valulibrary.usecases.DataUseCase
import com.valu.valulibrary.usecases.IDataNetwork
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::TayService)
    singleOf(::DataNetwork) { bind<IDataNetwork>() }
    factoryOf(::DataUseCase)

}