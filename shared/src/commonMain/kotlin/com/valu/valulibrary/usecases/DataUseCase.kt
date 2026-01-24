package com.valu.valulibrary.usecases



class DataUseCase(private val iDataNetwork : IDataNetwork)  {

    suspend fun loadLocations() = iDataNetwork.loadLocation()

    suspend fun saveLocations() = iDataNetwork.saveLocation()

}