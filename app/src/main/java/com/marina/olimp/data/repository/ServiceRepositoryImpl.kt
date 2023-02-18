package com.marina.olimp.data.repository

import com.marina.olimp.data.mapper.ServiceDtoMapper
import com.marina.olimp.data.remote.ServiceApi
import com.marina.olimp.domain.entity.Service
import com.marina.olimp.domain.repository.ServiceRepository

class ServiceRepositoryImpl(
    private val api: ServiceApi,
    private val mapper: ServiceDtoMapper
) : ServiceRepository {
    override suspend fun getAll(): List<Service> {
        val response = api.getServices()
        if (response.isSuccessful) {
            return response.body()?.items?.map { mapper.convert(it) }!!
        } else {
            throw Exception()
        }
    }
}