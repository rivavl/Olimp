package com.marina.olimp.domain.repository

import com.marina.olimp.domain.entity.Service

interface ServiceRepository {
    suspend fun getAll(): List<Service>
}