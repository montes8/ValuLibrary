package com.valu.valulibrary.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("uid")
    var uid : String? = "",
    @SerialName("name")
    var name : String?= "",
    @SerialName("url")
    var url : String?= "",
    @SerialName("identifier")
    var identifier : String?= "",
    @SerialName("state")
    var state : Boolean?= false
)