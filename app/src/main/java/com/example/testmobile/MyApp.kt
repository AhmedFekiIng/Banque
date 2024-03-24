package com.example.testmobile

import android.app.Application
import android.util.Log
import com.example.domain.dataModule
import com.example.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("Loggg MyApp", "onCreate called")
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(dataModule, domainModule, appModule))
        }
    }
}