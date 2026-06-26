package com.valu.valulibrary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel( ): ViewModel() {

    fun execute(loading: Boolean = true, func: suspend BaseViewModel.() -> Unit) {
        viewModelScope.launch {
            try {
                func()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    suspend fun <T> io(block: suspend () -> T): T = withContext(Dispatchers.IO) {
        block()
    }
}