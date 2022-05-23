package com.example.labaandruid1

import android.app.Application
import com.example.labaandruid1.di.AppModule
import com.example.labaandruid1.di.IAppComponent
import com.example.labaandruid1.di.DaggerIAppComponent

class App: Application() {
    lateinit var appComponent: IAppComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    fun initDagger(){
        appComponent = DaggerIAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}