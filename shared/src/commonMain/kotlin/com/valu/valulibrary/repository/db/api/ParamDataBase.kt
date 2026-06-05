package com.valu.valulibrary.repository.db.api

import com.valu.valulibrary.model.Param
import com.valu.valulibrary.repository.db.dao.ParamDao
import com.valu.valulibrary.repository.db.entity.ParamEntity
import com.valu.valulibrary.usecases.db.IParamDataBase

class ParamDataBase(private val paramDao: ParamDao): IParamDataBase {

    override suspend fun getParam(): Param {
        return ParamEntity.toListModel(paramDao.getParam())
    }

    override suspend fun deleteParamAll() {
        paramDao.deleteProductAll()
    }

    override suspend fun insertParam(data: Param): Boolean {
        paramDao.insertParam(ParamEntity.toEntity(data))
        return true
    }

}