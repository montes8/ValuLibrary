package com.valu.valulibrary.manager

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import org.koin.core.module.Module
import org.koin.dsl.module

lateinit var myApplicationContext : Application

actual fun createDataStoreBasic(): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create(
        produceFile = {
            myApplicationContext.preferencesDataStoreFile(dataStoreFileName)
        }
    )
}

actual fun platformModuleDataStore(): Module = module {
    single { createDataStoreBasic()}
}
