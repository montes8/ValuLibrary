package com.valu.valulibrary.usecases.db

import com.valu.valulibrary.model.Product


interface IProductDataBase {
    suspend fun insertContact(data: List<Product>) :Boolean
    suspend fun getProductAll(): List<Product>
    suspend fun deleteContactAll()
}