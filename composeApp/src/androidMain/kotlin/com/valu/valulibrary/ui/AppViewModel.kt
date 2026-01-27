package com.valu.valulibrary.ui

import androidx.compose.runtime.mutableStateListOf
import com.valu.valulibrary.model.Product
import com.valu.valulibrary.ui.splash.InitUiEvent
import com.valu.valulibrary.usecases.DataUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel(private val dataUseCase: DataUseCase):BaseViewModel() {

    private val _eventFlow = MutableSharedFlow<InitUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    val listProducts = mutableStateListOf<Product>()
    fun loadValidateLogin(){
        execute {
            val response = dataUseCase.loadData()
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