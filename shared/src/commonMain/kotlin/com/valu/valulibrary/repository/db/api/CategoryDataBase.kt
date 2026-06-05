package com.valu.valulibrary.repository.db.api

import com.valu.valulibrary.model.Category
import com.valu.valulibrary.repository.db.dao.CategoryDao
import com.valu.valulibrary.repository.db.entity.CategoryEntity
import com.valu.valulibrary.usecases.db.ICategoryDataBase

class CategoryDataBase(private val categoryDao: CategoryDao): ICategoryDataBase {


    override suspend fun getCategory(): List<Category> {
        return CategoryEntity.toListModel(categoryDao.getCategoryAll())
    }

    override suspend fun deleteCategoryAll() {
        categoryDao.deleteProductAll()
    }

    override suspend fun insertCategory(data: List<Category>): Boolean {
        categoryDao.insertAll(CategoryEntity.toListEntity(data))
        return true
    }

}