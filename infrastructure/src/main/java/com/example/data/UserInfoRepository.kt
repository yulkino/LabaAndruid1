package com.example.data

import com.example.domain.models.UserInfo
import com.example.domain.repository.IUserInfoRepository
import com.example.network.retrofit.IApi

class UserInfoRepository(
    private val api: IApi
):IUserInfoRepository {
    override suspend fun getUserInfo(): UserInfo = api.getUserInfo()[0]
}