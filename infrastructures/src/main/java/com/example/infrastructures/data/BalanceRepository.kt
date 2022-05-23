package com.example.infrastructures.data

import com.example.domain.models.Balance
import com.example.domain.repository.IBalanceRepository
import com.example.infrastructures.data.dao.BalanceDao
import com.example.infrastructures.data.models.BalanceEntity
import com.example.infrastructures.retrofit.IApi
import javax.inject.Inject

class BalanceRepository @Inject constructor(
    private val api: IApi,
    private val balanceDao: BalanceDao,
): IBalanceRepository {
    override suspend fun getBalance(): Balance {
        val balanceDb = balanceDao.getBalances()
        return if(balanceDb.isNotEmpty()){
            val entity = balanceDb[0]
            Balance(entity.accNum, entity.balance, entity.nextPay, entity.id)
        } else {
            val balanceFromApi = api.getBalance()[0]
            val mapping = BalanceEntity(balanceFromApi.accNum, balanceFromApi.balance, balanceFromApi.nextPay, balanceFromApi.id)
            balanceDao.saveBalances(mapping)
            balanceFromApi
        }
    }
}