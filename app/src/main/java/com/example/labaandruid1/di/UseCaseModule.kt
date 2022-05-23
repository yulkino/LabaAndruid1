package com.example.labaandruid1.di

import com.example.domain.usecases.getBalance.GetBalanceUseCaseImpl
import com.example.domain.usecases.getBalance.IGetBalanceUseCase
import com.example.domain.usecases.getTariffs.DeleteTariffUseCase
import com.example.domain.usecases.getTariffs.GetTariffsUseCaseImpl
import com.example.domain.usecases.getTariffs.IDeleteTariffUseCase
import com.example.domain.usecases.getTariffs.IGetTariffsUseCase
import com.example.domain.usecases.getUserInfo.GetUserInfoUseCaseImpl
import com.example.domain.usecases.getUserInfo.IGetUserInfoUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class UsecaseModule {
    @Binds
    abstract fun bindGetTariffs(getTariffsUseCase: GetTariffsUseCaseImpl): IGetTariffsUseCase

    @Binds
    abstract fun bindGetBalance(getBalanceUseCase: GetBalanceUseCaseImpl): IGetBalanceUseCase

    @Binds
    abstract fun bindGetUser(getTariffsUseCase: GetUserInfoUseCaseImpl): IGetUserInfoUseCase

    @Binds
    abstract fun bindDeleteTariff(deleteTariffUsecase: DeleteTariffUseCase): IDeleteTariffUseCase
}