package com.example.infrastructures.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.infrastructures.data.models.BalanceEntity
import kotlinx.coroutines.selects.select

@Dao
interface BalanceDao {
    @Insert
    suspend fun saveBalances(vararg balances: BalanceEntity)

    @Query("select * from balance")
    suspend fun getBalances() : List<BalanceEntity>
}