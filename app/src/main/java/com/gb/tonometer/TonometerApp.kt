package com.gb.tonometer

import android.app.Application
import com.gb.tonometer.di.AppComponent
import com.gb.tonometer.di.DaggerAppComponent

class TonometerApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = createAppComponent()
    }

    private fun createAppComponent() =
        DaggerAppComponent.builder()
            .applicationContext(this)
            .build()

    companion object {
        lateinit var instance: TonometerApp
    }
}