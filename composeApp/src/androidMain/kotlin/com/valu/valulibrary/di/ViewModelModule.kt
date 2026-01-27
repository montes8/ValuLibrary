package com.valu.valulibrary.di

import com.valu.valulibrary.ui.AppViewModel
import com.valu.valulibrary.ui.BaseViewModel
import com.valu.valulibrary.ui.detail.DetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AppViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { BaseViewModel() }
}