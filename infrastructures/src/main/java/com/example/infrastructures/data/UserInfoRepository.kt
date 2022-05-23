package com.example.infrastructures.data

import com.example.domain.models.UserInfo
import com.example.domain.repository.IUserInfoRepository
import com.example.infrastructures.data.dao.UserInfoDao
import com.example.infrastructures.data.models.UserInfoEntity
import com.example.infrastructures.retrofit.IApi
import javax.inject.Inject

class UserInfoRepository @Inject constructor(
    private val api: IApi,
    private val userInfoDao: UserInfoDao
):IUserInfoRepository {
    override suspend fun getUserInfo(): UserInfo {
        val fromDb = userInfoDao.getUsersInfo()
        return if (fromDb.isNotEmpty()) {
            val entity = fromDb[0]
            UserInfo(entity.firstName, entity.lastName, entity.address, entity.id)
        } else {
            val userInfoFromApi = api.getUserInfo()[0]
            val mapping = UserInfoEntity(userInfoFromApi.firstName, userInfoFromApi.lastName, userInfoFromApi.address, userInfoFromApi.id)
            userInfoDao.saveUsersInfo(mapping)
            userInfoFromApi
        }
    }
}