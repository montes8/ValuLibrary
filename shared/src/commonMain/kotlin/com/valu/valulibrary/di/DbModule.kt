package com.valu.valulibrary.di

import com.valu.valulibrary.manager.TayDatabase
import com.valu.valulibrary.repository.db.dao.ProductDao
import org.koin.core.module.Module
import org.koin.dsl.module

fun dbModule(): Module = module {
    single<ProductDao> { get<TayDatabase>().getProductDao() }
}