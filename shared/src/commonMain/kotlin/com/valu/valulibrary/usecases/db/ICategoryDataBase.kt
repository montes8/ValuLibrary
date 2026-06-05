package com.valu.valulibrary.usecases.db

import com.valu.valulibrary.model.Category
import com.valu.valulibrary.model.Param
import com.valu.valulibrary.model.Product


interface ICategoryDataBase {

    suspend fun getCategory(): List<Category>
    suspend fun deleteCategoryAll()
    suspend fun insertCategory(data: List<Category>) :Boolean
}