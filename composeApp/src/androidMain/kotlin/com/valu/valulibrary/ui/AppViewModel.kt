package com.valu.valulibrary.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.valu.valulibrary.ui.splash.InitUiEvent
import com.valu.valulibrary.usecases.DataUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AppViewModel(private val dataUseCase: DataUseCase,):BaseViewModel() {

    private val _eventFlow = MutableSharedFlow<InitUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var visibleToolbar by mutableStateOf(false)

    fun loadValidateLogin(){
        execute {
            Log.d("TAGVALU","loadValidateLogin")
            var resposne = dataUseCase.loadData()
            _eventFlow.emit(InitUiEvent.NavigateToNext())
        }
    }

    fun loadProduct(){
        execute {
            Log.d("TAGVALU","loadProduct")
            var resposne = dataUseCase.getProduct()
            Log.d("TAGVALU",resposne.toString())

        }
    }


}