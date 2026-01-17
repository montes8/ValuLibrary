package com.valu.valulibrary.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.valu.valulibrary.ui.splash.InitUiEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AppViewModel():BaseViewModel() {

    private val _eventFlow = MutableSharedFlow<InitUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var visibleToolbar by mutableStateOf(false)

    fun loadValidateLogin(){
        execute {
            delay(6000)
            _eventFlow.emit(InitUiEvent.NavigateToNext())
        }
    }

}