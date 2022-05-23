package com.example.domain.usecases.getBalance

import com.example.domain.models.Balance
import com.example.domain.repository.IBalanceRepository
import javax.inject.Inject

class GetBalanceUseCaseImpl @Inject constructor(
    private val balanceRepository: IBalanceRepository
): IGetBalanceUseCase {
    override suspend fun getBalance(): Balance = balanceRepository.getBalance()
}