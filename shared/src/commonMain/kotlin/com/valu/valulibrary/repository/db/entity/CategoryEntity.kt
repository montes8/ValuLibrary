package com.valu.valulibrary.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.valu.valulibrary.model.Category
import com.valu.valulibrary.model.Param
import com.valu.valulibrary.model.Product
import kotlin.String

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var uid : String,
    var name : String,
    var url : String,
    var identifier : String,
    var state : Boolean
){

    companion object{
        fun  toListEntity(data : List<Category>) = data.map { it ->
            CategoryEntity(
                 uid = it.uid?:"",
                name = it.name?:"",
                url = it.url?:"",
                identifier = it.identifier?:"",
                state = it.state?:false
            )
        }

        fun  toListModel(data : List<CategoryEntity>) = data.map { it ->
            Category(
                uid = it.uid,
                name = it.name,
                url = it.url,
                identifier = it.identifier,
                state = it.state
            )
        }
    }
}