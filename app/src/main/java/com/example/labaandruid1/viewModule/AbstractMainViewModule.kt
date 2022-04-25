package com.example.labaandruid1.viewModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Balance
import com.example.domain.models.Tariff
import com.example.domain.models.UserInfo

abstract class AbstractMainViewModule: ViewModel() {
    abstract val tariff: LiveData<List<Tariff>>
    abstract val userInfo: LiveData<UserInfo>
    abstract val balance: LiveData<Balance>

    abstract val isLoading: LiveData<Boolean>

    abstract fun refreshData()
}