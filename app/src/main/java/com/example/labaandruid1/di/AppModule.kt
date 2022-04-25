package com.example.labaandruid1.di

import com.example.data.BalanceRepository
import com.example.data.TariffRepository
import com.example.data.UserInfoRepository
import com.example.domain.models.UserInfo
import com.example.domain.repository.IBalanceRepository
import com.example.domain.repository.ITariffRepository
import com.example.domain.repository.IUserInfoRepository
import com.example.domain.usecases.getBalance.GetBalanceUseCaseImpl
import com.example.domain.usecases.getBalance.IGetBalanceUseCase
import com.example.domain.usecases.getTariffs.GetTariffsUseCaseImpl
import com.example.domain.usecases.getTariffs.IGetTariffsUseCase
import com.example.domain.usecases.getUserInfo.GetUserInfoUseCaseImpl
import com.example.domain.usecases.getUserInfo.IGetUserInfoUseCase
import com.example.labaandruid1.ui.MainActivity
import com.example.labaandruid1.viewModule.ViewModelFactory
import com.example.network.retrofit.ApiProvider
import com.example.network.retrofit.IApi
import com.example.network.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule{
    @Provides @Singleton
    fun provideViewModelFactory(
        tariffsUseCase: IGetTariffsUseCase,
        balanceUseCase: IGetBalanceUseCase,
        userInfoUseCase: IGetUserInfoUseCase
    ): ViewModelFactory =
        ViewModelFactory(tariffsUseCase, userInfoUseCase, balanceUseCase)

    @Provides @Singleton
    fun provideRetrofitClient(): RetrofitClient =
        RetrofitClient()

    @Provides @Singleton
    fun provideApiProvider(client: RetrofitClient): ApiProvider =
        ApiProvider(client)

    @Provides @Singleton
    fun provideApi(apiProvider: ApiProvider): IApi =
        apiProvider.getApi()

    @Provides @Singleton
    fun provideUserInfoRepo(api: IApi): IUserInfoRepository =
        UserInfoRepository(api)

    @Provides @Singleton
    fun provideBalanceRepo(api: IApi): IBalanceRepository =
        BalanceRepository(api)

    @Provides @Singleton
    fun provideTariffRepo(api: IApi): ITariffRepository =
        TariffRepository(api)

    @Provides @Singleton
    fun provideUserInfoUseCase(repo: IUserInfoRepository): IGetUserInfoUseCase =
        GetUserInfoUseCaseImpl(repo)

    @Provides @Singleton
    fun provideBalanceUseCase(repo: IBalanceRepository): IGetBalanceUseCase =
        GetBalanceUseCaseImpl(repo)

    @Provides @Singleton
    fun provideTariffUseCase(repo: ITariffRepository): IGetTariffsUseCase =
        GetTariffsUseCaseImpl(repo)
}