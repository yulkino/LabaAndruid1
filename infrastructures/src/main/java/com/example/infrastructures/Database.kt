package com.example.infrastructures

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.example.infrastructures.data.dao.BalanceDao
import com.example.infrastructures.data.dao.DebtDao
import com.example.infrastructures.data.dao.TariffDao
import com.example.infrastructures.data.dao.UserInfoDao
import com.example.infrastructures.data.models.BalanceEntity
import com.example.infrastructures.data.models.DebtEntity
import com.example.infrastructures.data.models.TariffEntity
import com.example.infrastructures.data.models.UserInfoEntity

@Database(entities = [
    TariffEntity::class,
    BalanceEntity::class,
    UserInfoEntity::class,
    DebtEntity::class,
], version = 2)
abstract class Database: RoomDatabase() {
    abstract fun getBalanceDao(): BalanceDao
    abstract fun getTariffDao(): TariffDao
    abstract fun getUserInfoDao(): UserInfoDao
    abstract fun getDebtDao(): DebtDao
}