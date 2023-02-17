package com.marina.olimp.app

import android.app.Application
import com.marina.olimp.di.AppContainer

class App : Application() {
    val container = AppContainer()
}