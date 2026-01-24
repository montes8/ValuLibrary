package com.valu.valulibrary.usecases.datastore


import com.valu.valulibrary.model.Product

interface IDataStorePreferences{

    suspend fun saveSession(value : String)

    suspend fun getSession(): String

    suspend fun saveDataSecurity(value : Product )

    suspend fun getDataSecurity(): Product?
}