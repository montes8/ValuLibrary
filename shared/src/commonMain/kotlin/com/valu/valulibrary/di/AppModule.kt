package com.valu.valulibrary.di

import com.valu.valulibrary.repository.db.api.CategoryDataBase
import com.valu.valulibrary.repository.db.api.ParamDataBase
import com.valu.valulibrary.repository.db.api.ProductDataBase
import com.valu.valulibrary.repository.network.TayService
import com.valu.valulibrary.repository.network.api.DataNetwork
import com.valu.valulibrary.usecases.DataUseCase
import com.valu.valulibrary.usecases.db.ICategoryDataBase
import com.valu.valulibrary.usecases.db.IParamDataBase
import com.valu.valulibrary.usecases.db.IProductDataBase
import com.valu.valulibrary.usecases.network.IDataNetwork
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::TayService)
    singleOf(::DataNetwork) { bind<IDataNetwork>() }
    singleOf(::ProductDataBase) { bind<IProductDataBase>() }
    singleOf(::ParamDataBase) { bind<IParamDataBase>() }
    singleOf(::CategoryDataBase) { bind<ICategoryDataBase>() }
    factoryOf(::DataUseCase)

}