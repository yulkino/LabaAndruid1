package com.example.domain.usecases.getTariffs

import com.example.domain.models.Tariff
import com.example.domain.repository.ITariffRepository
import javax.inject.Inject

class GetTariffsUseCaseImpl @Inject constructor(
    private val tariffRepository: ITariffRepository
): IGetTariffsUseCase {
    override suspend fun getTariffs(): List<Tariff> = tariffRepository.getTariffs()
}