package com.valu.valulibrary.repository.db.api

import com.valu.valulibrary.model.Category
import com.valu.valulibrary.model.Param
import com.valu.valulibrary.model.Product
import com.valu.valulibrary.repository.db.dao.ProductDao
import com.valu.valulibrary.repository.db.entity.ProductEntity
import com.valu.valulibrary.usecases.db.IProductDataBase

class ProductDataBase(private val productDao: ProductDao): IProductDataBase {

    override suspend fun insertContact(data: List<Product>): Boolean {
        productDao.insertAll(ProductEntity.toListEntity(data))
        return true
    }

    override suspend fun getProductAll(): List<Product> {
        return ProductEntity.toListModel(productDao.getProductsPrincipal())
    }

    override suspend fun getProductAllCategory(id: String): List<Product> {
        return ProductEntity.toListModel(productDao.getProductsByCategory(id))
    }

    override suspend fun deleteContactAll() {
        productDao.deleteProductAll()
    }

}