package com.example.labaandruid1.viewModule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Balance
import com.example.domain.models.Tariff
import com.example.domain.models.UserInfo
import com.example.domain.usecases.getBalance.IGetBalanceUseCase
import com.example.domain.usecases.getTariffs.DeleteTariffUseCase
import com.example.domain.usecases.getTariffs.IGetTariffsUseCase
import com.example.domain.usecases.getUserInfo.IGetUserInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModule @Inject constructor(
    private val tariffUseCase: IGetTariffsUseCase,
    private val userInfoUseCase: IGetUserInfoUseCase,
    private  val balanceUseCase: IGetBalanceUseCase,
    private val deleteTariffUseCase: DeleteTariffUseCase,
): AbstractMainViewModule() {
    override val tariffData = MutableLiveData<List<Tariff>>()
    override val userInfoData = MutableLiveData<UserInfo>()
    override val balanceData = MutableLiveData<Balance>()
    
    override val isLoading = MutableLiveData(false)

    override fun refreshData() {
        viewModelScope.launch {
            isLoading.value = true

            tariffData.value = tariffUseCase.getTariffs()
            userInfoData.value = userInfoUseCase.getUserInfo()
            balanceData.value = balanceUseCase.getBalance()
            
            isLoading.value = false
        }
    }

    override fun delete(tariff: Tariff) {
        viewModelScope.launch {
            val newTariffs = deleteTariffUseCase(tariff)
            tariffData.value = newTariffs
            isLoading.value = false
        }
    }
}