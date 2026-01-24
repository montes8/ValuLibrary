package com.valu.valulibrary.di

import com.valu.valulibrary.manager.configModuleDB
import com.valu.valulibrary.model.Product
import com.valu.valulibrary.usecases.DataUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class KoinHelper : KoinComponent {
    private val dataUseCase : DataUseCase by inject()

    suspend fun loadData() : Boolean = dataUseCase.loadData()

    suspend fun loadProductDD() : List<Product> = dataUseCase.getProduct()

    //suspend fun getUrlIos() = getUrlAppTay()
}

fun initKoinIos() = initKoin(appDeclaration = {
    modules(appModule,networkModule,dbModule(), configModuleDB())
})