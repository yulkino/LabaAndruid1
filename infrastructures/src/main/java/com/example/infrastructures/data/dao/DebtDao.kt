package com.example.infrastructures.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.infrastructures.data.models.DebtEntity

@Dao
interface DebtDao {
    @Query("Select * from debtentity")
    fun getDebts(): List<DebtEntity>

    @Insert
    fun addDebts(vararg debts: DebtEntity)

    @Delete
    fun deleteDebt(debtEntity: DebtEntity)
}