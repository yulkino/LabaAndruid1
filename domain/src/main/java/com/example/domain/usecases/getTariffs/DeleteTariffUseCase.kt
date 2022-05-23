package com.example.domain.usecases.getTariffs

import com.example.domain.models.Tariff
import com.example.domain.repository.ITariffRepository
import javax.inject.Inject

class DeleteTariffUseCase @Inject constructor(
    private val repo: ITariffRepository
): IDeleteTariffUseCase {
    override suspend fun invoke(tariff: Tariff): List<Tariff> {
        return repo.deleteTariff(tariff)
    }
}