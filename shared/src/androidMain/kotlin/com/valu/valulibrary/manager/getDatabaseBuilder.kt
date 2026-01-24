package com.valu.valulibrary.manager

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<TayDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("appLibraryVale.db")

    return Room.databaseBuilder<TayDatabase>(
        context = appContext,
        name = dbFile.absolutePath,
    )
}

actual fun configModuleDB(): Module = module {
    single<TayDatabase> {
        val builder = getDatabaseBuilder(context = get())
        getAppDatabase(builder)
    }
}