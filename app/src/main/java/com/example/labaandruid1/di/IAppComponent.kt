package com.example.labaandruid1.di

import android.content.Context
import com.example.labaandruid1.App
import com.example.labaandruid1.viewModule.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = [
    AppModule::class,
    DataModule::class,
    NetworkModule::class,
    UsecaseModule::class,
    ViewModelModule::class,
])
interface IAppComponent {
    fun factory(): ViewModelFactory
}

val Context.factory get() = (applicationContext as App).appComponent.factory()
