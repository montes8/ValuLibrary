package com.valu.valulibrary.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.valu.valulibrary.model.Product
import kotlin.String

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var uid : String,
    var name : String,
    var description : String,
    var category : String,
    var price : String,
    var priceTwo : String,
    var state : Boolean,
    var url : String,
    var phone : String,
    var principal : Boolean,
    var admin : Boolean,
    var countryCode : String,
    var district : String,
    var longitude : String,
    var latitude : String,
    var limitDistance : String,
    var banner : Boolean,
    var click : Boolean,
    var sellerClient : String
){

    companion object{
        fun  toListEntity(data : List<Product>) = data.map { it ->
            ProductEntity(
                 uid = it.uid?:"",
             name = it.name?:"",
             description = it.description?:"",
             category = it.category?:"",
             price = it.price?:"",
             priceTwo = it.priceTwo?:"",
             state = it.state?:false,
             url = it.url?:"",
             phone = it.phone?:"",
             principal = it.principal?:false,
             admin = it.admin?:false,
             countryCode = it.countryCode?:"",
             district = it.district?:"",
             longitude = it.longitude?:"",
             latitude = it.latitude?:"",
             limitDistance = it.limitDistance?:"",
             banner = it.banner?:false,
             click = it.click?:false,
             sellerClient = it.sellerClient?:""
            )
        }

        fun  toListModel(data : List<ProductEntity>) = data.map { it ->
            Product(
                uid = it.uid,
                name = it.name,
                description = it.description,
                category = it.category,
                price = it.price,
                priceTwo = it.priceTwo,
                state = it.state,
                url = it.url,
                phone = it.phone,
                principal = it.principal,
                admin = it.admin,
                countryCode = it.countryCode,
                district = it.district,
                longitude = it.longitude,
                latitude = it.latitude,
                limitDistance = it.limitDistance,
                banner = it.banner,
                click = it.click,
                sellerClient = it.sellerClient
            )
        }
    }
}