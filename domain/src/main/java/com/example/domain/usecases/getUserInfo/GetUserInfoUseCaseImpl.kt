package com.example.domain.usecases.getUserInfo

import com.example.domain.models.UserInfo
import com.example.domain.repository.IUserInfoRepository

class GetUserInfoUseCaseImpl(
    private val userRepository: IUserInfoRepository
): IGetUserInfoUseCase {
    override suspend fun getUserInfo(): UserInfo = userRepository.getUserInfo()
}