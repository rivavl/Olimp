package com.marina.olimp.presentation.mapper

import com.marina.olimp.domain.entity.Service
import com.marina.olimp.presentation.entity.ServiceUI

class ServiceUIMapper {
    fun convert(from: Service): ServiceUI =
        ServiceUI(
            name = from.name,
            description = from.description,
            imageURL = from.imageURL,
            serviceLink = from.serviceLink
        )
}