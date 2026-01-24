package com.valu.valulibrary.repository.api

import com.valu.valulibrary.model.Product
import com.valu.valulibrary.repository.TayService
import com.valu.valulibrary.usecases.IDataNetwork
import com.valu.valulibrary.utils.parseJsonTo
import com.valu.valulibrary.utils.parseListProduct

class DataNetwork(private val apiService: TayService) : IDataNetwork {

    override suspend fun loadLocation() : List<Product> {
        return apiService.getLocation().parseListProduct()


    }

    override suspend fun saveLocation(): Product {
        val response =  apiService.postLocation(
            Product())
        return response.parseJsonTo<Product>()
    }
}