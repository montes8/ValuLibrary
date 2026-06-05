package com.valu.valulibrary.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valu.valulibrary.repository.db.entity.ParamEntity

@Dao
interface ParamDao {

    @Query("SELECT * FROM ParamEntity")
    suspend fun getParam(): ParamEntity

    @Query("SELECT * FROM ParamEntity")
    suspend fun getParamAll(): List<ParamEntity>

    @Insert
    suspend fun insertParam(model: ParamEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ParamEntity>)

    @Query("DELETE FROM ParamEntity")
    suspend fun deleteProductAll()
}