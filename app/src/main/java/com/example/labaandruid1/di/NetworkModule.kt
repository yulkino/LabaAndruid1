package com.example.labaandruid1.di

import com.example.infrastructures.retrofit.IApi
import com.example.infrastructures.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun provideApi(client: RetrofitClient): IApi = client.getClient()
}