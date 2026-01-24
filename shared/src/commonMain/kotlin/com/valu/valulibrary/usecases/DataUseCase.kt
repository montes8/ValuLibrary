package com.valu.valulibrary.usecases

import com.valu.valulibrary.model.Product
import com.valu.valulibrary.usecases.db.IProductDataBase
import com.valu.valulibrary.usecases.network.IDataNetwork


class DataUseCase(private val iDataNetwork : IDataNetwork,
                  private val iProductDataBase : IProductDataBase)  {

    suspend fun loadData(): Boolean{
      val response =  iDataNetwork.loadData()
        val data = insertProduct(response)
        return data

    }
    suspend fun insertProduct(data : List<Product>) = iProductDataBase.insertContact(data)

    suspend fun getProduct() = iProductDataBase.getProductAll()

    suspend fun saveLocations() = iDataNetwork.saveLocation()

}