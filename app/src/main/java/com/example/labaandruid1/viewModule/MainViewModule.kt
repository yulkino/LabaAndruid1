package com.example.labaandruid1.viewModule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Balance
import com.example.domain.models.Tariff
import com.example.domain.models.UserInfo
import com.example.domain.usecases.getBalance.IGetBalanceUseCase
import com.example.domain.usecases.getTariffs.IGetTariffsUseCase
import com.example.domain.usecases.getUserInfo.IGetUserInfoUseCase
import kotlinx.coroutines.launch

class MainViewModule(
    private val tariffUseCase: IGetTariffsUseCase,
    private val userInfoUseCase: IGetUserInfoUseCase,
    private  val balanceUseCase: IGetBalanceUseCase
): AbstractMainViewModule() {
    override val tariff = MutableLiveData<List<Tariff>>()
    override val userInfo = MutableLiveData<UserInfo>()
    override val balance = MutableLiveData<Balance>()
    
    override val isLoading = MutableLiveData(false)

    override fun refreshData() {
        viewModelScope.launch {
            isLoading.value = true

            tariff.value = tariffUseCase.getTariffs()
            userInfo.value = userInfoUseCase.getUserInfo()
            balance.value = balanceUseCase.getBalance()
            
            isLoading.value = false
        }
    }
}