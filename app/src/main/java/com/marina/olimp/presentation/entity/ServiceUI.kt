package com.marina.olimp.presentation.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceUI(
    val name: String,
    val description: String,
    val imageURL: String,
    val serviceLink: String
) : Parcelable