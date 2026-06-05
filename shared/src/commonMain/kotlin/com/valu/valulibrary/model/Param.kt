package com.valu.valulibrary.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Param(
    @SerialName("uid")
    var uid : String? = "",
    @SerialName("title")
    var title : String?= "",
    @SerialName("description")
    var description : String?= "",
    @SerialName("idMovie")
    var idMovie : String?= "",
    @SerialName("enableCategory")
    var enableCategory : Boolean?= false,
    @SerialName("stateImport")
    var stateImport : Boolean?= false,
    @SerialName("stateUtil")
    var stateUtil : Boolean?= false,
    @SerialName("phone")
    var phone : String?= "",
    @SerialName("style")
    var style : String?= ""

)