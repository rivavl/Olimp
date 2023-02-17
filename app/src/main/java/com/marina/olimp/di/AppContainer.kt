package com.marina.olimp.di

import com.marina.olimp.data.mapper.ServiceDtoMapper
import com.marina.olimp.data.remote.ServiceApi
import com.marina.olimp.data.repository.ServiceRepositoryImpl
import retrofit2.Retrofit

class AppContainer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://mobile-olympiad-trajectory.hb.bizmrg.com/")
        .build()
        .create(ServiceApi::class.java)

    private val mapperDto = ServiceDtoMapper()

    val serviceRepository = ServiceRepositoryImpl(retrofit, mapperDto)
}