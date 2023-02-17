package com.marina.olimp.data.mapper

import com.marina.olimp.data.remote.dto.ServiceDto
import com.marina.olimp.domain.entity.Service

class ServiceDtoMapper {
    fun convert(from: ServiceDto): Service =
        Service(
            name = from.name,
            description = from.description,
            imageURL = from.iconUrl,
            serviceLink = from.serviceUrl
        )
}