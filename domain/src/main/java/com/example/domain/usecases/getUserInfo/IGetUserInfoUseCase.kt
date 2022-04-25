package com.example.domain.usecases.getUserInfo

import com.example.domain.models.UserInfo

interface IGetUserInfoUseCase {
    suspend fun getUserInfo(): UserInfo
}