package com.example.miformacionctma

import android.app.Application
import com.example.miformacionctma.data.AppContainer
import com.example.miformacionctma.data.AppDataContainer

class MiFormacionApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}