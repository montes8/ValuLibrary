package com.valu.valulibrary.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.valu.valulibrary.model.Param
import com.valu.valulibrary.model.Product
import kotlin.String

@Entity
data class ParamEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var uid : String,
    var title : String,
    var description : String,
    var idMovie : String,
    var enableCategory : Boolean,
    var stateImport : Boolean,
    var stateUtil : Boolean,
    var phone : String,
    var style : String
){

    companion object{
        fun  toEntity(data : Param) =
            ParamEntity(
                 uid = data.uid?:"",
                title = data.title?:"",
                description = data.description?:"",
                idMovie = data.idMovie?:"",
                enableCategory = data.enableCategory?:false,
                stateImport = data.stateImport?:false,
                stateUtil = data.stateUtil?:true,
                phone = data.phone?:"",
                style = data.style?:""
            )


        fun  toListModel(data : ParamEntity) =
            Param(
                uid = data.uid,
                title = data.title?:"",
                description = data.description,
                idMovie = data.idMovie?:"",
                enableCategory = data.enableCategory,
                stateImport = data.stateImport,
                stateUtil = data.stateUtil,
                phone = data.phone,
                style = data.style
            )

    }
}