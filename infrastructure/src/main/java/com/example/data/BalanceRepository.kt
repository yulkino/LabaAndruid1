package com.example.data

import com.example.domain.models.Balance
import com.example.domain.repository.IBalanceRepository
import com.example.network.retrofit.IApi

class BalanceRepository(
    private val api: IApi
): IBalanceRepository {
    override suspend fun getBalance(): Balance = api.getBalance()[0]
}