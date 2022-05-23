package com.example.infrastructures.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.infrastructures.data.models.UserInfoEntity

@Dao
interface UserInfoDao {
    @Insert
    suspend fun saveUsersInfo(vararg usersInfo: UserInfoEntity)

    @Query("select * from userInfo")
    suspend fun getUsersInfo() : List<UserInfoEntity>
}