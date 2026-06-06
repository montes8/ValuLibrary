package com.valu.valulibrary.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valu.valulibrary.repository.db.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
    suspend fun getProduct(): ProductEntity

    @Query("SELECT * FROM ProductEntity")
    suspend fun getProductAll(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE category = :category")
    suspend fun getProductsByCategory(category: String): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE type = :type")
    suspend fun getProductsByType(type: String): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE principal = true")
    suspend fun getProductsPrincipal(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE category LIKE '%' || :search || '%'")
    suspend fun searchByCategory(search: String): List<ProductEntity>

    @Insert
    suspend fun insertProduct(model: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("DELETE FROM ProductEntity")
    suspend fun deleteProductAll()
}