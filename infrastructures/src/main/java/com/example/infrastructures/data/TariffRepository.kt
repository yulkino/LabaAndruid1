package com.example.infrastructures.data

import com.example.domain.models.Tariff
import com.example.domain.repository.ITariffRepository
import com.example.infrastructures.data.dao.TariffDao
import com.example.infrastructures.data.models.TariffEntity
import com.example.infrastructures.retrofit.IApi
import javax.inject.Inject

class TariffRepository @Inject constructor(
    private val api: IApi,
    private val tariffDao: TariffDao,
): ITariffRepository {
    override suspend fun getTariffs(): List<Tariff> {
        val fromDb = tariffDao.getTariffs()
        return if (fromDb.isNotEmpty()) {
            fromDb.map { Tariff(it.title, it.desc, it.cost, it.id) }
        } else {
            val fromApi = api.getTariffs()
            val mapped = fromApi.map { TariffEntity(it.title, it.desc, it.cost, it.id) }
            tariffDao.saveTariffs(*mapped.toTypedArray())
            fromApi
        }
    }

    override suspend fun deleteTariff(tariff: Tariff): List<Tariff> {
        tariffDao.deleteTariff(TariffEntity(tariff.title, tariff.desc, tariff.cost, tariff.id))
        return tariffDao.getTariffs().map { Tariff(it.title, it.desc, it.cost, it.id) }
    }
}