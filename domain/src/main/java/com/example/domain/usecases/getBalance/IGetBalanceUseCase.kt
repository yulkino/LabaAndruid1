package com.example.domain.usecases.getBalance

import com.example.domain.models.Balance

interface IGetBalanceUseCase {
    suspend fun getBalance() : Balance
}