package com.valu.valulibrary.usecases.network

import com.valu.valulibrary.model.Product

interface IDataNetwork {
    suspend fun loadData( ): List<Product>

    suspend fun saveLocation( ): Product

}