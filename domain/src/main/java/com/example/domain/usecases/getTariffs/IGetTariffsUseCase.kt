package com.example.domain.usecases.getTariffs

import com.example.domain.models.Tariff

interface IGetTariffsUseCase {
    suspend fun getTariffs(): List<Tariff>
}