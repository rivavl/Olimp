package com.marina.olimp.di

import com.marina.olimp.BuildConfig
import com.marina.olimp.data.mapper.ServiceDtoMapper
import com.marina.olimp.data.remote.ServiceApi
import com.marina.olimp.data.repository.ServiceRepositoryImpl
import com.marina.olimp.domain.usecase.GetServicesUseCase
import com.marina.olimp.presentation.ListViewModelFactory
import com.marina.olimp.presentation.detail.viewmodel.DetailViewModel
import com.marina.olimp.presentation.list.viewmodel.ListViewModel
import com.marina.olimp.presentation.mapper.ServiceUIMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer {
    val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(ServiceApi::class.java)

    private val mapperDto = ServiceDtoMapper()
    private val mapperUI = ServiceUIMapper()

    private val serviceRepository = ServiceRepositoryImpl(retrofit, mapperDto)
    private val useCase = GetServicesUseCase(serviceRepository)

    //    val detailViewModel = DetailViewModelFactory()
    val listViewModel = ListViewModelFactory(useCase, mapperUI).create(ListViewModel::class.java)
    val detailViewModel =
        ListViewModelFactory(useCase, mapperUI).create(DetailViewModel::class.java)
}