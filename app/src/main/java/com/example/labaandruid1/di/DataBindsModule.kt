package com.example.labaandruid1.di

import com.example.domain.repository.IBalanceRepository
import com.example.domain.repository.ITariffRepository
import com.example.domain.repository.IUserInfoRepository
import com.example.infrastructures.data.BalanceRepository
import com.example.infrastructures.data.TariffRepository
import com.example.infrastructures.data.UserInfoRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataBindsModule {
    @Binds
    abstract fun bindBalanceRepo(balanceRepository: BalanceRepository): IBalanceRepository

    @Binds
    abstract fun bindUserRepo(userInfoRepository: UserInfoRepository): IUserInfoRepository

    @Binds
    abstract fun bindTariffsRepo(tariffsRepository: TariffRepository): ITariffRepository
}
