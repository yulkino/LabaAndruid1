package com.example.labaandruid1.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(
    private val context: Context
) {
    @Provides
    fun context() = context
}