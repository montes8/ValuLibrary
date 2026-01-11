package com.valu.valulibrary.ui.splash

sealed class InitUiEvent {
    class NavigateToNext() : InitUiEvent()
}