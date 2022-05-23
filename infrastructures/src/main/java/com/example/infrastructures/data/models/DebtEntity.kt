package com.example.infrastructures.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DebtEntity(
    val accNum: Int,
    val debtSum: Double,
    val debtDate: String, //new field
    @PrimaryKey val id: String,
)