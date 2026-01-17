package com.valu.valulibrary.model

data class ProductModel(
    var uiId : String = "",
    var name : String = "default",
    var description : String = "default",
    var category : String = "0",
    var price : String = "0.00",
    var priceTwo : String = "0.00",
    var state : Boolean = true,
    var url : String = "",
    var phone : String = "000000000",
    var principal : Boolean = true,
    var admin : Boolean = true,
    var countryCode : String = "PE",
    var district : String = "Huacho chico",
    var longitude : String = "",
    var latitude : String = "",
    var limitDistance : String = "10",
    var banner : Boolean = false,
    var click : Boolean = true,
    var sellerClient : String = "Libreria valu"
)