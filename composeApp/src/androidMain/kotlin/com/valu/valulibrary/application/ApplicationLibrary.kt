package com.valu.valulibrary.application

import android.app.Application
import com.valu.valulibrary.di.initKoin
import com.valu.valulibrary.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger


class ApplicationLibrary: Application()  {
    companion object {
        lateinit  var appContextTaySure: Application
    }
    override fun onCreate() {
        super.onCreate()
        appContextTaySure = this
        initKoin(appDeclaration = {
            androidContext(this@ApplicationLibrary)
            androidLogger()
            modules(viewModelModule)

        })
    }
}