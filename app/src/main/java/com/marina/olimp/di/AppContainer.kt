package com.marina.olimp.di

import com.marina.olimp.data.mapper.ServiceDtoMapper
import com.marina.olimp.data.remote.ServiceApi
import com.marina.olimp.data.repository.ServiceRepositoryImpl
import com.marina.olimp.domain.usecase.GetServicesUseCase
import com.marina.olimp.presentation.ListViewModelFactory
import com.marina.olimp.presentation.detail.viewmodel.DetailViewModel
import com.marina.olimp.presentation.mapper.ServiceUIMapper
import retrofit2.Retrofit

class AppContainer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://mobile-olympiad-trajectory.hb.bizmrg.com/")
        .build()
        .create(ServiceApi::class.java)

    private val mapperDto = ServiceDtoMapper()
    private val mapperUI = ServiceUIMapper()

    private val serviceRepository = ServiceRepositoryImpl(retrofit, mapperDto)
    private val useCase = GetServicesUseCase(serviceRepository)

    val detailViewModel = DetailViewModel()
    val listViewModel = ListViewModelFactory(useCase, mapperUI)
}