package com.example.labaandruid1.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.labaandruid1.viewModule.AbstractMainViewModule
import com.example.labaandruid1.viewModule.MainViewModule
import com.example.labaandruid1.viewModule.ViewModelFactory
import com.example.labaandruid1.viewModule.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AbstractMainViewModule::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModule): ViewModel
}