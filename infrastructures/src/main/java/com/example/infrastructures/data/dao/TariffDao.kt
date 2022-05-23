package com.example.infrastructures.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.infrastructures.data.models.TariffEntity

@Dao
interface TariffDao {
    @Insert
    suspend fun saveTariffs(vararg tariffs: TariffEntity)

    @Query("select * from tariff")
    suspend fun getTariffs() : List<TariffEntity>
    
    @Delete
    suspend fun deleteTariff(tariff: TariffEntity)
}