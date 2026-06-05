package com.valu.valulibrary.usecases.network

import com.valu.valulibrary.model.Category
import com.valu.valulibrary.model.Param
import com.valu.valulibrary.model.Product

interface IDataNetwork {
    suspend fun loadData( ): List<Product>

    suspend fun loadParam( ): Param

    suspend fun loadCategory( ): List<Category>

    suspend fun saveLocation( ): Product

}