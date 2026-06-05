package com.valu.valulibrary.repository.network.api

import com.valu.valulibrary.model.Category
import com.valu.valulibrary.model.Param
import com.valu.valulibrary.model.Product
import com.valu.valulibrary.repository.network.TayService
import com.valu.valulibrary.usecases.network.IDataNetwork
import com.valu.valulibrary.utils.parseJsonTo
import com.valu.valulibrary.utils.parseListCategory
import com.valu.valulibrary.utils.parseListProduct

class DataNetwork(private val apiService: TayService) : IDataNetwork {

    override suspend fun loadData() : List<Product> {
        return apiService.loadData().parseListProduct()


    }

    override suspend fun loadParam(): Param {
        val response =  apiService.loadParam()
        return  response.parseJsonTo<Param>()
    }

    override suspend fun loadCategory(): List<Category> {
        return apiService.loadCategory().parseListCategory()
    }

    override suspend fun saveLocation(): Product {
        val response =  apiService.postLocation(
            Product())
        return response.parseJsonTo<Product>()
    }
}