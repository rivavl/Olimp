package com.marina.olimp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ServiceDto(
    val description: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    val name: String,
    @SerializedName("service_url")
    val serviceUrl: String
)