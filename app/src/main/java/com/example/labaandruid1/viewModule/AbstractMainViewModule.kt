package com.example.labaandruid1.viewModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Balance
import com.example.domain.models.Tariff
import com.example.domain.models.UserInfo

abstract class AbstractMainViewModule: ViewModel() {
    abstract val tariffData: LiveData<List<Tariff>>
    abstract val userInfoData: LiveData<UserInfo>
    abstract val balanceData: LiveData<Balance>

    abstract val isLoading: LiveData<Boolean>

    abstract fun refreshData()

    abstract fun delete(tariff: Tariff)
}