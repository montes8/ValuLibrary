package com.valu.valulibrary.repository.network.api

import com.valu.valulibrary.model.Product
import com.valu.valulibrary.repository.network.TayService
import com.valu.valulibrary.usecases.network.IDataNetwork
import com.valu.valulibrary.utils.parseJsonTo
import com.valu.valulibrary.utils.parseListProduct

class DataNetwork(private val apiService: TayService) : IDataNetwork {

    override suspend fun loadData() : List<Product> {
        return apiService.loadData().parseListProduct()


    }

    override suspend fun saveLocation(): Product {
        val response =  apiService.postLocation(
            Product())
        return response.parseJsonTo<Product>()
    }
}