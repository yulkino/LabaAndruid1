package com.example.labaandruid1.network.retrofit

import com.example.labaandruid1.network.models.Balance
import com.example.labaandruid1.network.models.Tariff
import com.example.labaandruid1.network.models.UserInfo
import retrofit2.Call
import retrofit2.http.GET

interface IApi {
    @GET("userInfo")
    suspend fun getUserInfo(): List<UserInfo>

    @GET("tariffs")
    suspend fun getTariffs(): List<Tariff>

    @GET("balance")
    suspend fun getBalance(): List<Balance>
}