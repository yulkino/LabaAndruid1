package com.example.infrastructures.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userInfo")
data class UserInfoEntity(
    val firstName: String,
    val lastName: String,
    val address: String,
    @PrimaryKey val id: String,
)
