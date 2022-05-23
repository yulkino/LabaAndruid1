package com.example.labaandruid1.di

import android.content.Context
import androidx.room.Room
import com.example.infrastructures.Database
import com.example.infrastructures.data.dao.BalanceDao
import com.example.infrastructures.data.dao.TariffDao
import com.example.infrastructures.data.dao.UserInfoDao
import com.example.infrastructures.migrations.InitMigration_1_2
import dagger.Module
import dagger.Provides

@Module(includes = [DataBindsModule::class])
class DataModule {
    @Provides
    fun provideDatabase(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "db").addMigrations(InitMigration_1_2).build()

    @Provides
    fun provideTariffsDao(database: Database): TariffDao = database.getTariffDao()

    @Provides
    fun provideUserDao(database: Database): UserInfoDao = database.getUserInfoDao()

    @Provides
    fun provideBalanceDao(database: Database): BalanceDao = database.getBalanceDao()
}