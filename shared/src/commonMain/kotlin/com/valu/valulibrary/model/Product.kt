package com.valu.valulibrary.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class Product(
    @SerialName("uid")
    var uid : String? = "",
    @SerialName("name")
    var name : String?= "",
    @SerialName("description")
    var description : String?= "",
    @SerialName("category")
    var category : String?= "",
    @SerialName("price")
    var price : String?= "",
    @SerialName("priceTwo")
    var priceTwo : String?= "",
    @SerialName("state")
    var state : Boolean?= false,
    @SerialName("url")
    var url : String?= "",
    @SerialName("phone")
    var phone : String?= "",
    @SerialName("principal")
    var principal : Boolean?= false,
    @SerialName("admin")
    var admin : Boolean? = false,
    @SerialName("countryCode")
    var countryCode : String?= "",
    @SerialName("district")
    var district : String?= "",
    @SerialName("longitude")
    var longitude : String?= "",
    @SerialName("latitude")
    var latitude : String?= "",
    @SerialName("limitDistance")
    var limitDistance : String?= "",
    @SerialName("banner")
    var banner : Boolean?= false,
    @SerialName("click")
    var click : Boolean?= true,
    @SerialName("sellerClient")
    var sellerClient : String?= ""
)