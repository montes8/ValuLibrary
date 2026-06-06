package com.valu.valulibrary.ui

import androidx.compose.runtime.mutableStateListOf
import com.valu.valulibrary.manager.TayDatabase
import com.valu.valulibrary.model.Product
import com.valu.valulibrary.model.TaySessionData
import com.valu.valulibrary.ui.splash.InitUiEvent
import com.valu.valulibrary.usecases.DataUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AppViewModel(private val dataUseCase: DataUseCase):BaseViewModel() {

    private val _eventFlow = MutableSharedFlow<InitUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    val listProducts = mutableStateListOf<Product>()
    fun loadValidateLogin(){
        execute {
            delay(2000)
            val response = dataUseCase.loadData()
            TaySessionData.categories = dataUseCase.getCategory()
            TaySessionData.param = dataUseCase.getParam()
            _eventFlow.emit(InitUiEvent.NavigateToNext())
        }
    }

    fun loadProduct(){
        execute {
            val response = dataUseCase.getProduct()
            listProducts.addAll(response)

        }
    }

    fun updateProduct(){
        execute {
             dataUseCase.updateData()
        }
    }


}