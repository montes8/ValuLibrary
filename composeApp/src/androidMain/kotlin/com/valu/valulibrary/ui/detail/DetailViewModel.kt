package com.valu.valulibrary.ui.detail

import androidx.compose.runtime.mutableStateListOf
import com.valu.valulibrary.model.Product
import com.valu.valulibrary.ui.BaseViewModel
import com.valu.valulibrary.usecases.DataUseCase

class DetailViewModel(private val dataUseCase: DataUseCase): BaseViewModel() {

    val listProductDetail = mutableStateListOf<Product>()

    fun loadProductDetail(id: String){
        execute {
            val response = dataUseCase.getProductAllCategory(id)
            listProductDetail.addAll(response)

        }
    }
}