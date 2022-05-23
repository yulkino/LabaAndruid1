package com.example.domain.usecases.getTariffs

import com.example.domain.models.Tariff

interface IDeleteTariffUseCase {
    suspend operator fun invoke(tariff: Tariff): List<Tariff>
}