package com.marina.olimp.domain.usecase

import com.marina.olimp.domain.entity.Service
import com.marina.olimp.domain.repository.ServiceRepository

class GetServicesUseCase(
    private val repository: ServiceRepository
) {
    suspend operator fun invoke(): List<Service> {
        return repository.getAll()
    }
}