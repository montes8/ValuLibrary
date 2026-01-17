package com.valu.valulibrary.di

import com.valu.valulibrary.ui.AppViewModel
import com.valu.valulibrary.ui.BaseViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AppViewModel() }
    viewModel { BaseViewModel() }
}