package com.valu.valulibrary.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valu.valulibrary.repository.db.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM CategoryEntity")
    suspend fun getCategory(): CategoryEntity

    @Query("SELECT * FROM CategoryEntity")
    suspend fun getCategoryAll(): List<CategoryEntity>

    @Insert
    suspend fun insert(model: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<CategoryEntity>)

    @Query("DELETE FROM CategoryEntity")
    suspend fun deleteProductAll()
}