package com.gb.tonometer.di.modules

import com.gb.tonometer.data.TonometerRepositoryImpl
import com.gb.tonometer.data.date_provider.DateAndTimeProvider
import com.gb.tonometer.data.date_provider.DateAndTimeProviderImpl
import com.gb.tonometer.domain.TonometerRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {
    @Singleton
    @Binds
    fun tonometerRepository(tonometerRepository: TonometerRepositoryImpl): TonometerRepository

    @Singleton
    @Binds
    fun dateProvider(dateAndTimeProvider: DateAndTimeProviderImpl): DateAndTimeProvider

}