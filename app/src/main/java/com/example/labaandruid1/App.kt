package com.example.labaandruid1

import android.app.Application
import com.example.labaandruid1.di.IAppComponent
import com.example.labaandruid1.di.DaggerIAppComponent

class App: Application() {
    companion object {
        lateinit var appComponent: IAppComponent
    }

    private lateinit var _appComponent: IAppComponent

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
        _appComponent = DaggerIAppComponent.create()
        appComponent = _appComponent
    }
}