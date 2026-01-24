package com.valu.valulibrary.usecases

import com.valu.valulibrary.model.Product

interface IDataNetwork {
    suspend fun loadLocation( ): List<Product>

    suspend fun saveLocation( ): Product

}