package com.example.labaandruid1.viewModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.getBalance.IGetBalanceUseCase
import com.example.domain.usecases.getTariffs.IGetTariffsUseCase
import com.example.domain.usecases.getUserInfo.IGetUserInfoUseCase

class ViewModelFactory(
    private val tariffUseCase: IGetTariffsUseCase,
    private val userInfoUseCase: IGetUserInfoUseCase,
    private  val balanceUseCase: IGetBalanceUseCase
):ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModule(tariffUseCase, userInfoUseCase, balanceUseCase) as T
    }
}