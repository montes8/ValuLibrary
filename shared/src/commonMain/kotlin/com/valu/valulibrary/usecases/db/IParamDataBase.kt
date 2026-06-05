package com.valu.valulibrary.usecases.db

import com.valu.valulibrary.model.Param


interface IParamDataBase {

    suspend fun getParam(): Param
    suspend fun deleteParamAll()
    suspend fun insertParam(data: Param) :Boolean
}