package com.marina.olimp.data.mapper

import com.marina.olimp.data.remote.dto.ServiceDto
import com.marina.olimp.domain.entity.Service
import org.junit.Assert
import org.junit.Test

class ServiceDtoMapperTest {
    private val services = listOf(
        ServiceDto(
            name = "Qwerty",
            description = "Qwerty description",
            iconUrl = "image url",
            serviceUrl = "link to site"
        )
    )
    private val expected = listOf(
        Service(
            name = "Qwerty",
            description = "Qwerty description",
            imageURL = "image url",
            serviceLink = "link to site"
        )
    )
    private val mapper = ServiceDtoMapper()

    @Test
    fun `Convert data service entity to domain`() {
        val testResult = services.map { mapper.convert(it) }
        Assert.assertEquals(expected, testResult)
    }
}