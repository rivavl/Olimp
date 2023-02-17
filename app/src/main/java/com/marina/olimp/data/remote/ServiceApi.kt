package com.marina.olimp.data.remote

import com.marina.olimp.data.remote.dto.ServiceDto
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {
    @GET("semi-final-data.json")
    suspend fun getServices(): Response<List<ServiceDto>>
}