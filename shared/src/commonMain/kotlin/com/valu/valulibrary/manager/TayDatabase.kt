package com.valu.valulibrary.manager

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.valu.valulibrary.repository.db.dao.CategoryDao
import com.valu.valulibrary.repository.db.dao.ParamDao
import com.valu.valulibrary.repository.db.dao.ProductDao
import com.valu.valulibrary.repository.db.entity.CategoryEntity
import com.valu.valulibrary.repository.db.entity.ParamEntity
import com.valu.valulibrary.repository.db.entity.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module

expect fun configModuleDB(): Module

@Database(entities = [ProductEntity::class, ParamEntity::class, CategoryEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class TayDatabase: RoomDatabase() {
    abstract fun getProductDao(): ProductDao

    abstract fun getParamDao(): ParamDao

    abstract fun getCategoryDao(): CategoryDao
}

// Room compiler generates the `actual` implementations
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<TayDatabase> {
    override fun initialize(): TayDatabase
}

fun getAppDatabase(builder: RoomDatabase.Builder<TayDatabase>): TayDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}