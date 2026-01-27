package com.valu.valulibrary.usecases

import com.valu.valulibrary.model.Product
import com.valu.valulibrary.usecases.db.IProductDataBase
import com.valu.valulibrary.usecases.network.IDataNetwork


class DataUseCase(private val iDataNetwork : IDataNetwork,
                  private val iProductDataBase : IProductDataBase)  {

    @Suppress("SuspiciousIndentation")
    suspend fun loadData(): Boolean{
        val dataDb =  getProduct()
        return if (dataDb.isEmpty()){
            getProductService()
        }else{
            dataDb.isNotEmpty()
        }
    }

    suspend fun updateData(){
          getProductService()
    }

    suspend fun getProductService():Boolean{
        val response =  iDataNetwork.loadData()
        deleteProduct()
        return insertProduct(response)
    }
    suspend fun insertProduct(data : List<Product>) = iProductDataBase.insertContact(data)

    suspend fun deleteProduct() = iProductDataBase.deleteContactAll()

    suspend fun getProduct() = iProductDataBase.getProductAll()

    suspend fun getProductAllCategory(id: String) = iProductDataBase.getProductAllCategory(id)


}