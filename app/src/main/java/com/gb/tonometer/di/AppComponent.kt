package com.gb.tonometer.di

import android.content.Context
import com.gb.tonometer.TonometerApp
import com.gb.tonometer.di.modules.ActivityModule
import com.gb.tonometer.di.modules.DataModule
import com.gb.tonometer.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ActivityModule::class,
    DataModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): AppComponent
    }

    fun inject(app: TonometerApp)
    fun inject (mainActivity:MainActivity)
}