package com.valu.valulibrary.model

object TaySessionData {
    var categories : List<Category> = ArrayList()
    var param : Param = Param()


    fun validFullItems(): Boolean{
        return param.stateUtil?:false && param.stateImport?:false
    }

    fun enableImports(): Boolean{
        return param.stateImport?:false
    }

    fun enableUtils(): Boolean{
        return param.stateUtil?:false
    }
}