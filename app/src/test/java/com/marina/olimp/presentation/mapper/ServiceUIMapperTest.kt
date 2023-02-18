package com.marina.olimp.presentation.mapper

import com.marina.olimp.domain.entity.Service
import com.marina.olimp.presentation.entity.ServiceUI
import org.junit.Assert
import org.junit.Test

class ServiceUIMapperTest {
    private val services = listOf(
        Service(
            name = "Qwerty",
            description = "Qwerty description",
            imageURL = "image url",
            serviceLink = "link to site"
        )
    )
    private val expected = listOf(
        ServiceUI(
            name = "Qwerty",
            description = "Qwerty description",
            imageURL = "image url",
            serviceLink = "link to site"
        )
    )
    private val mapper = ServiceUIMapper()

    @Test
    fun `Convert domain service entity to UI`() {
        val testResult = services.map { mapper.convert(it) }
        Assert.assertEquals(expected, testResult)
    }
}