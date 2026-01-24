package com.valu.valulibrary.repository.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.valu.valulibrary.model.Product
import com.valu.valulibrary.usecases.datastore.IDataStorePreferences
import com.valu.valulibrary.utils.parseJsonTo
import com.valu.valulibrary.utils.toJsonString
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStorePreference(private val dataStore: DataStore<Preferences>) : IDataStorePreferences {

    override suspend fun saveSession(value: String) {
        try {
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey("PREFERENCES_TOKEN_STORE")] = value
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getSession(): String {
        return dataStore.data.map { preferences ->
            val string = preferences[stringPreferencesKey("PREFERENCES_TOKEN_STORE")] ?: ""
            string
        }.first()

    }
    override suspend fun saveDataSecurity(value: Product) {
        try {
            val jsonString = value.toJsonString()
            println(jsonString)
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey("PREFERENCE_DATA_SECURITY")] = jsonString
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Suppress("SuspiciousIndentation")
    override suspend fun getDataSecurity(): Product? {
    val data   =  dataStore.data.map { preferences ->
        val string = preferences[stringPreferencesKey("PREFERENCE_DATA_SECURITY")] ?: ""
        string
        }.first()
        return data.parseJsonTo<Product>()
    }
}