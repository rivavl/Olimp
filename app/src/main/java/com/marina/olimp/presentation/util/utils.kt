package com.marina.olimp.presentation.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(url: String, context: Context) {
    Glide.with(context)
        .load(url)
        .into(this)
}