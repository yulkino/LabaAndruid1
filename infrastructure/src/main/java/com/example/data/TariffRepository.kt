package com.example.data

import com.example.domain.models.Tariff
import com.example.domain.repository.ITariffRepository
import com.example.network.retrofit.IApi

class TariffRepository(
    private val api: IApi
): ITariffRepository {
    override suspend fun getTariffs(): List<Tariff> = api.getTariffs()
}